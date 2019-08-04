package com.fitness.athome

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.fitness.athome.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeTextBehaviorTest {

    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso"
    }

    @Test
    fun changeText_sameActivity() {
        // Type text and then press the button.
        //onView(withId(R.id.editTextUserInput)).perform(typeText(stringToBetyped), closeSoftKeyboard())
        //onView(withId(R.id.changeTextBt)).perform(click())

        /*// Check that the text was changed.
        onView(withId(R.id.textToBeChanged))
            .check(matches(withText(stringToBetyped)))*/
    }
}