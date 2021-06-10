package com.coderzheaven.bowlingapp

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.coderzheaven.bowlingapp.utils.Constants.Companion.INVALID_SEQ
import com.coderzheaven.bowlingapp.utils.Constants.Companion.PERFECT_GAME_SEQ
import com.coderzheaven.bowlingapp.view_screens.GameHomeActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class GameHomeScreenActivityTest {

    private lateinit var gameStringToBeTyped: String
    private lateinit var invalidGameInput: String
    private var decorView: View? = null

    @get:Rule
    var activityRule: ActivityScenarioRule<GameHomeActivity> =
        ActivityScenarioRule(GameHomeActivity::class.java)

    @Before
    fun initTest() {
        gameStringToBeTyped = PERFECT_GAME_SEQ
        invalidGameInput = INVALID_SEQ
        activityRule.scenario.onActivity { activity: GameHomeActivity ->
            decorView = activity.window.decorView
        }
    }

    @Test
    fun testUIComponentsArePresent() {
        onView(withId(R.id.imgLogo)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.edtScores)).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.btnScore), withText(R.string.get_score_btn_text)))
        onView(withId(R.id.tvFinalScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tvProgress)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tvStatusMessage)).check(matches(not(isDisplayed())))
    }

    @Test
    fun perfectGameUiTest() {
        onView(withId(R.id.edtScores))
            .perform(typeText(gameStringToBeTyped), closeSoftKeyboard())
        onView(withId(R.id.btnScore)).perform(click())
        onView(allOf(withId(R.id.tvStatusMessage), withText(R.string.perfect_game_message))).check(matches(isCompletelyDisplayed()))
        Thread.sleep(2000);
    }

    @Test
    fun testInvalidInput() {
        onView(withId(R.id.edtScores))
            .perform(typeText(invalidGameInput), closeSoftKeyboard())
        onView(withId(R.id.btnScore)).perform(click())
        onView(allOf(withId(R.id.tvStatusMessage), withText(R.string.invalid_input))).check(matches(isCompletelyDisplayed()))
        Thread.sleep(2000);
    }

}