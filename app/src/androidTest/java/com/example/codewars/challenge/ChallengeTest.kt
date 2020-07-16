package com.example.codewars.challenge

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.codewars.R
import com.example.codewars.ui.Challenges.ChallengesActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChallengeTest {

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<ChallengesActivity?>? =
        ActivityTestRule(ChallengesActivity::class.java, false, true)

    @Test
    fun testBottomNavigationVisibility(){
        onView(withId(R.id.bottom_navigation))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testGoToCompletedChallenge(){
        onView(withId(R.id.complete_challenges_item_bottom)).perform(click())
        onView(withId(R.id.fragment_completed_challenge)).check(matches(isDisplayed()))
    }


    @Test
    fun testInCompletedChallengeProgressBarInvisible(){
        onView(withId(R.id.complete_challenges_item_bottom)).perform(click())
        onView(withId(R.id.progress_bar_challenge_fragment)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testInCompletedChallengeTextViewInvisible(){
        onView(withId(R.id.complete_challenges_item_bottom)).perform(click())
        onView(withId(R.id.text_view_completed_challenge)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testInCompletedChallengeRecycleViewInvisible(){
        onView(withId(R.id.complete_challenges_item_bottom)).perform(click())
        onView(withId(R.id.recycle_view_challenge_fragment)).check(matches(not(isDisplayed())))
    }


    @Test
    fun testGoToAuthoredChallenge(){
        onView(withId(R.id.authored_challenges_item_bottom)).perform(click())
        onView(withId(R.id.fragment_authored_challenge)).check(matches(isDisplayed()))
    }

    @Test
    fun testInAuthoredChallengeProgressBarInvisible(){
        onView(withId(R.id.authored_challenges_item_bottom)).perform(click())
        onView(withId(R.id.progress_bar_authored_challenge_fragment)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testInAuthoredChallengeTextViewInvisible(){
        onView(withId(R.id.authored_challenges_item_bottom)).perform(click())
        onView(withId(R.id.text_view_authored_challenge)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testInAuthoredChallengeRecycleViewInvisible(){
        onView(withId(R.id.authored_challenges_item_bottom)).perform(click())
        onView(withId(R.id.recycle_view_authored_challenge_fragment)).check(matches(not(isDisplayed())))
    }

}
