package com.fitness.athome.storage

import android.content.Context
import android.content.SharedPreferences
import com.fitness.athome.R
import javax.inject.Inject


class PreferencesHelper @Inject constructor(var sharedPreferences: SharedPreferences, var context: Context) {
    /*private lateinit var sharedPreferences: SharedPreferences

    private lateinit var context: Context

    @Inject
    fun PreferencesHelper(sharedPreferences: SharedPreferences, context: Context) {
        this.sharedPreferences = sharedPreferences
        this.context = context
    }*/

    private val KEY_LANGUAGE = "key_language"

    private val IS_USER_DATA_ENTER = "is_user_data_enter"

    fun getLanguage(): String? {
        return sharedPreferences.getString(KEY_LANGUAGE, context.getString(R.string.default_language))
    }

    fun setLanguage(language: String) {
        sharedPreferences.edit().putString(KEY_LANGUAGE, language).apply()
    }

    fun isUserDataEnter(): Boolean? {
        return sharedPreferences.getBoolean(IS_USER_DATA_ENTER, false)
    }

    fun setUserDataEnter(isUserDataEnter: Boolean) {
        sharedPreferences.edit().putBoolean(IS_USER_DATA_ENTER, isUserDataEnter).apply()
    }
}