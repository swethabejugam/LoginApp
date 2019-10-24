
package com.example.android.dagger

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.swetha.loginapp.main.MainActivity
import org.junit.Test

class ApplicationTest {

    @Test
    fun runApp() {
        ActivityScenario.launch(MainActivity::class.java)

        // Should be in Registration/EnterDetails because the user is not registered
        onView(withText("Registration")).check(matches(isDisplayed()))

        onView(withId(R.id.username)).perform(typeText("user"), closeSoftKeyboard())
        onView(withId(R.id.password)).perform(typeText("pass"), closeSoftKeyboard())
        onView(withId(R.id.register)).perform(click())
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard())

        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard())

        onView(withId(R.id.username)).perform(typeText("username"), closeSoftKeyboard())
        onView(withId(R.id.password)).perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.register)).perform(click())


        // Main
        onView(withText("Hello World!")).check(matches(isDisplayed()))
        onView(withText("LOGOUT")).perform(click())


        //Login screen
        onView(withId(R.id.password)).perform(typeText("passwo"), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())
        onView(withId(R.id.password)).perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())



    }
}
