package com.coderzheaven.bowlingapp.view_screens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.View.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.coderzheaven.bowlingapp.R
import com.coderzheaven.bowlingapp.utils.Constants.Companion.PERFECT_SCORE
import com.coderzheaven.bowlingapp.utils.Utils.Companion.hideSoftKeyboard
import com.coderzheaven.bowlingapp.view_model.GameViewModel

class GameHomeActivity : AppCompatActivity(), OnClickListener {

    // UI
    private lateinit var btnScore: Button
    private lateinit var edtScores: EditText
    private lateinit var tvFinalScore: TextView
    private lateinit var tvProgress: TextView
    private lateinit var tvStatusMessage: TextView

    // View Model
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        findViews()
        initObservers()
    }

    private fun findViews() {
        btnScore = findViewById(R.id.btnScore)
        edtScores = findViewById(R.id.edtScores)
        tvFinalScore = findViewById(R.id.tvFinalScore)
        tvProgress = findViewById(R.id.tvProgress)
        tvStatusMessage = findViewById(R.id.tvStatusMessage)
        btnScore.setOnClickListener(this)
    }

    private fun initObservers() {
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        viewModel.score.observe(this, onScoreCalculated())
        viewModel.error.observe(this, onErrorReceived())
        viewModel.inProgress.observe(this, calculationInProgress())
    }

    private fun calculationInProgress(): Observer<Boolean> = Observer {
        showProgressInUI(it)
    }

    private fun onErrorReceived(): Observer<Boolean> = Observer {
        showMessageInUI(getString(R.string.invalid_input))
    }

    private fun onScoreCalculated(): Observer<Int> = Observer { score ->
        showScoreInUI("$score")
        if (score >= PERFECT_SCORE) {
            showMessageInUI(getString(R.string.perfect_game_message))
        }
    }

    private fun showProgressInUI(show: Boolean) {
        tvProgress.visibility = if (show) VISIBLE else INVISIBLE
    }

    private fun showMessageInUI(message: String?) {
        tvStatusMessage.text = message
        tvStatusMessage.visibility = if (!message.isNullOrBlank()) VISIBLE else GONE
    }

    private fun getScore() {

        if (viewModel.inProgress.value == true) {
            return
        }

        val scoreHits = edtScores.text.toString().trim()

        if (scoreHits.isNullOrBlank()) {
            showMessageInUI(getString(R.string.invalid_input))
            return
        }

        viewModel.getScore(scoreHits)
    }

    private fun showScoreInUI(score: String) {
        tvFinalScore.text = score
    }

    override fun onClick(p0: View?) {
        val handler = Handler(Looper.getMainLooper()) {
            showScoreInUI("0")
            showMessageInUI(null)
            getScore()
            true
        }
        hideSoftKeyboard(this, edtScores, handler)
    }

}