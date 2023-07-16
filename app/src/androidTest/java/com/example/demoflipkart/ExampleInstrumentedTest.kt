package com.example.demoflipkart

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.regex.Pattern.matches

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
//    @Test
    fun demoFlipkartTest() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.demoflipkart", appContext.packageName)
//        onView(withId()).perform(click()).check(matches(Matchers))
    }

//    fun demoFlipkartHomeElementAppear() {
//
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.example.demoflipkart", appContext.packageName)
//        onView(withClassName("android.view.ViewGroup")).perform(click()).check(matches(Matchers))
//    }
}