package com.coderzheaven.bowlingapp.game

import java.util.stream.IntStream

class GameFrame constructor(lastFrame: Boolean = false) {
    var isLastFrame: Boolean = lastFrame
    private val rolls: IntArray = if (lastFrame) IntArray(3) else IntArray(2)
    private var pinsHitIndex = 0
    var isSpare = false
    var isStrike = false
    var isDone = false

    fun roll(pinsHit: Int) {
        rolls[pinsHitIndex] = pinsHit
        val firstRoll = pinsHitIndex == 0
        val lastRoll = pinsHitIndex == rolls.size - 1
        isStrike = firstRoll && score == 10
        isSpare = lastRoll && score == 10
        isDone = if (isLastFrame) {
            lastRoll
        } else {
            isStrike || isSpare || lastRoll
        }
        pinsHitIndex++
    }

    val score: Int get() = IntStream.of(*rolls).sum()

    fun getRollHits(rolls: Int): Int {
        return IntStream.of(*this.rolls).limit(rolls.toLong()).sum()
    }

}