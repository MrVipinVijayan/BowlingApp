package com.coderzheaven.bowlingapp.game

class Game2 {
    private var roll: Int = 0;
    private var rolls = IntArray(21)

    fun roll(pinsDown: Int) {
        rolls[roll++] = pinsDown
    }

    fun score(): Int {
        var score = 0
        var cursor = 0

        for (i in 0..10) {
            if (rolls[cursor] == 10) { // strike
                score += 10 + rolls[cursor + 1] + rolls[cursor + 2]
                cursor++
            } else if (rolls[cursor] + rolls[cursor + 1] == 10) { // spare
                score += 10 + rolls[cursor + 2]
                cursor += 2
            } else {
                score += rolls[cursor] + rolls[cursor + 1]
                cursor += 2
            }
        }
        return score
    }
}