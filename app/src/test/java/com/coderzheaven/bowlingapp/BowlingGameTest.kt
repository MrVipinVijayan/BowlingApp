package com.coderzheaven.bowlingapp

import com.coderzheaven.bowlingapp.game.BowlingGame
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class BowlingGameTest {

    @Test
    fun testNotStarted() {
        val bowlingGame = BowlingGame()
        assertThat(bowlingGame.score, equalTo(0))
    }

    @Test
    fun testNoHits() {
        val bowlingGame = BowlingGame()
        for (i in 1..10) {
            bowlingGame.roll(0)
            bowlingGame.roll(0)
        }
        assertThat(bowlingGame.score, equalTo(0))
    }

    @Test
    fun testScoresAllOnes() {
        val bowlingGame = BowlingGame()
        for (i in 1..10) {
            bowlingGame.roll(1)
            bowlingGame.roll(1)
        }
        assertThat(bowlingGame.score, equalTo(20))
    }

    @Test
    fun testScoresOneStrike() {
        val bowlingGame = BowlingGame()
        for (i in 1..10) {
            if (i == 1) {
                bowlingGame.roll(10) // strike
                continue
            }
            bowlingGame.roll(1)
            bowlingGame.roll(1)
        }
        assertThat(bowlingGame.score, equalTo(30))
    }

    @Test
    fun testScoresOneSpare() {
        val bowlingGame = BowlingGame()
        for (i in 1..10) {
            if (i == 1) {
                bowlingGame.roll(5)
                bowlingGame.roll(5) // spare
                continue
            }
            bowlingGame.roll(1)
            bowlingGame.roll(1)
        }
        assertThat(bowlingGame.score, equalTo(29))
    }

    @Test
    fun testScoresTwoSpare() {
        val bowlingGame = BowlingGame()
        for (i in 1..10) {
            if (i == 1) {
                bowlingGame.roll(5)
                bowlingGame.roll(5) // spare
                continue
            }
            if (i == 2) {
                bowlingGame.roll(5)
                bowlingGame.roll(5) // spare
                continue
            }
            bowlingGame.roll(1)
            bowlingGame.roll(1)
        }
        assertThat(bowlingGame.score, equalTo(42))
    }

    @Test
    fun testPerfectGame() {
        val bowlingGame = BowlingGame()
        for (i in 1..12) {
            bowlingGame.roll(10) // strike
        }
        assertThat(bowlingGame.score, equalTo(300))
    }

}