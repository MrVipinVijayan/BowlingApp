package com.coderzheaven.bowlingapp

import com.coderzheaven.bowlingapp.game.GameFrame
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class GameFrameTest {

    @Test
    fun testZeroHitScore() {
        val gameFrame = GameFrame()
        gameFrame.roll(0)
        gameFrame.roll(0)
        MatcherAssert.assertThat(gameFrame.score, CoreMatchers.equalTo(0))
    }

    @Test
    fun testOneHitScore() {
        val gameFrame = GameFrame()
        gameFrame.roll(1)
        gameFrame.roll(0)
        MatcherAssert.assertThat(gameFrame.score, CoreMatchers.equalTo(1))
    }

    @Test
    fun testRollHits() {
        val gameFrame = GameFrame()
        gameFrame.roll(1)
        gameFrame.roll(0)
        MatcherAssert.assertThat(gameFrame.getRollHits(1), CoreMatchers.equalTo(1))
    }

}