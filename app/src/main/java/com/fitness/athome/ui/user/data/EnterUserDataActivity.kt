package com.fitness.athome.ui.user.data

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fitness.athome.App
import com.fitness.athome.R
import com.fitness.athome.model.user_data.UserData
import com.fitness.athome.repository.FirestoreRepository
import com.fitness.athome.ui.BaseActivity
import com.fitness.athome.ui.main.MainActivity
import com.google.android.gms.tasks.Task
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class EnterUserDataActivity : BaseActivity() {

    lateinit var toolbar: Toolbar
    lateinit var btnDone: Button
    lateinit var textInputEditTextName: TextInputEditText
    lateinit var chipGroupGoal: ChipGroup
    lateinit var chipGroupActivity: ChipGroup

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var firestoreRepository: FirestoreRepository

    lateinit var enterUserDataViewModel: EnterUserDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_user_data)

        App.appComponent.injectsEnterUserDataActivity(this)

        toolbar = findViewById(R.id.toolbar)
        setToolbar()

        btnDone = findViewById(R.id.btnDone)
        textInputEditTextName = findViewById(R.id.textInputEditTextName)
        chipGroupGoal = findViewById(R.id.chipGroupGoal)
        chipGroupActivity = findViewById(R.id.chipGroupActivity)

        enterUserDataViewModel = ViewModelProviders.of(this, EnterUserDataViewModelFactory(firestoreRepository)).get(EnterUserDataViewModel::class.java)

        enterUserDataViewModel.docTask.observe(this, Observer<Task<Void>> {
            if (it.isSuccessful) {
                hideProgressDialog()
                launchMainActivity()
            }
        })

        btnDone.setOnClickListener {
            insertData()
        }
    }

    fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.enter_user_data_title)
    }

    fun insertData() {
        val name = textInputEditTextName.text.toString()
        val idGoal = chipGroupGoal.checkedChipId
        val idActivity = chipGroupActivity.checkedChipId

        if (TextUtils.isEmpty(name)) {
            showToast(getString(R.string.enter_user_data_error_enter_name))
        } else if (idGoal == -1) {
            showToast(getString(R.string.enter_user_data_error_choose_goal))
        } else if (idActivity == -1) {
            showToast(getString(R.string.enter_user_data_error_choose_activity))
        } else {
            firebaseAuth.currentUser.let {
                it?.let {
                    showProgressDialog()
                    enterUserDataViewModel.insertUserData(it.uid, UserData(name, idGoal, idActivity))
                }
            }
        }
    }

    fun launchMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}