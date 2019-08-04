package com.fitness.athome

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    val FAKE_STRING = "string"

    @Mock
    private lateinit var context: Context

    @Test fun readStringFromContext_LocalizedString() {
        MockitoAnnotations.initMocks(this);
        // Given a Context object retrieved from Robolectric...

        // ...when the string is returned from the object under test...
        val result: String = "Open"

        // ...then the result should be the expected one.
        assertThat(result, equalTo(context.getString(R.string.navigation_drawer_open)));
        //assertThat(result, equalTo("Open"))
    }
}
