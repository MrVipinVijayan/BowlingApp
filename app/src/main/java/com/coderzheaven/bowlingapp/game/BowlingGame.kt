package com.coderzheaven.bowlingapp.game

class BowlingGame {

    private val gameFrames: Array<GameFrame> = arrayOf(
        GameFrame(),
        GameFrame(),
        GameFrame(),
        GameFrame(),
        GameFrame(),
        GameFrame(),
        GameFrame(),
        GameFrame(),
        GameFrame(),
        GameFrame(true)
    )

    private var frameIndex = 0

    val score: Int
        get() {
            var score = 0
            for (i in gameFrames.indices) {
                var bonusScore = 0
                if (gameFrames[i].isLastFrame && gameFrames[i - 1].isStrike) {
                    return gameFrames[i].score.let { score += it; score }
                } else if (gameFrames[i].isStrike) {
                    bonusScore = addStrikeScore(i)
                } else if (gameFrames[i].isSpare) {
                    bonusScore = addSpareScore(i)
                }
                score += gameFrames[i].score + bonusScore
            }
            return score
        }

    private fun addSpareScore(currentFrameIndex: Int): Int {
        return gameFrames[currentFrameIndex + 1].getRollHits(1)
    }

    private fun addStrikeScore(currentFrameIndex: Int): Int {
        var bonusScore: Int
        val nextGameFrame: GameFrame = gameFrames[currentFrameIndex + 1]
        when {
            nextGameFrame.isLastFrame -> {
                bonusScore = nextGameFrame.getRollHits(2)
            }
            nextGameFrame.isStrike -> {
                val frameAfterNextGameFrame: GameFrame = gameFrames[currentFrameIndex + 2]
                bonusScore = frameAfterNextGameFrame.getRollHits(1)
                bonusScore += nextGameFrame.score
            }
            else -> {
                bonusScore = nextGameFrame.score
            }
        }
        return bonusScore
    }

    fun roll(pinsHit: Int?) {
        if (gameFrames[frameIndex].isDone) {
            frameIndex++
        }
        if (null != pinsHit) {
            gameFrames[frameIndex].roll(pinsHit)
        }
    }
}