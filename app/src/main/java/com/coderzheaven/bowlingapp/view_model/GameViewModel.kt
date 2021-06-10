package com.coderzheaven.bowlingapp.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coderzheaven.bowlingapp.game.BowlingGame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {

    var score: MutableLiveData<Int> = MutableLiveData()
    var error: MutableLiveData<Boolean> = MutableLiveData()
    var inProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun getScore(gameData: String) {
        inProgress.value = true
        viewModelScope.launch(Dispatchers.IO) {
            score.postValue(processGame(gameData))
            inProgress.postValue(false)
        }
    }

    private fun processGame(gameData: String): Int? {
        return try {
            val bowlingGame = BowlingGame()
            var scores = gameData.split(",")
            for (scoreHit in scores) {
                bowlingGame.roll(scoreHit.trim().toInt())
            }
            bowlingGame.score
        } catch (e: Exception) {
            error.postValue(true)
            0
        }
    }
}