package com.example.codewars.user

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.codewars.R
import com.example.codewars.ui.User.UserActivity
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UserActivityTest {

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<UserActivity?>? = ActivityTestRule(UserActivity::class.java, false, true)

    @Test
    fun testSearchViewIsVisible(){
        onView(withId(R.id.search_view_user_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun testTextViewIsVisible(){
        onView(withId(R.id.text_view_empty_state )).check(matches(isDisplayed()))
    }
    @Test
    fun testProgressBarIsInvisible(){
        onView(withId(R.id.progress_bar_user_fragment )).check(matches(not(isDisplayed())))

    }
    @Test
    fun testTextViewIsInvisible(){
        onView(withId(R.id.text_view_latest_finding )).check(matches(not(isDisplayed())))
    }
    @Test
    fun testRecycleViewIsInvisible(){
        onView(withId(R.id.recycle_view_user_fragment )).check(matches(not(isDisplayed())))

    }



}