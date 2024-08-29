package com.example.trabalho_taep

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InicioJokenpo : AppCompatActivity() {
    private var playerScore = 0
    private var pcScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_jokenpo)

        val playerScoreTextView: TextView = findViewById(R.id.player_score)
        val pcScoreTextView: TextView = findViewById(R.id.pc_score)
        val startGameButton: Button = findViewById(R.id.start_game_button)
        val resetScoreButton: Button = findViewById(R.id.reset_score_button)
        val voltarMenuButton: Button = findViewById(R.id.voltar_menu)


        loadScores(playerScoreTextView, pcScoreTextView)


        startGameButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivityForResult(intent, 1)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }


        resetScoreButton.setOnClickListener {
            playerScore = 0
            pcScore = 0
            updateScores(playerScoreTextView, pcScoreTextView)
            saveScores()
        }

        voltarMenuButton.setOnClickListener {
            val intent = Intent(this, MenuJogos::class.java)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
    }

    private fun updateScores(playerScoreTextView: TextView, pcScoreTextView: TextView) {
        playerScoreTextView.text = "Jogador: $playerScore"
        pcScoreTextView.text = "PC: $pcScore"
    }

    private fun saveScores() {
        val sharedPreferences = getSharedPreferences("JokenpoScores", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("playerScore", playerScore)
        editor.putInt("pcScore", pcScore)
        editor.apply()
    }

    private fun loadScores(playerScoreTextView: TextView, pcScoreTextView: TextView) {
        val sharedPreferences = getSharedPreferences("JokenpoScores", MODE_PRIVATE)
        playerScore = sharedPreferences.getInt("playerScore", 0)
        pcScore = sharedPreferences.getInt("pcScore", 0)
        updateScores(playerScoreTextView, pcScoreTextView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val gameResult = data?.getStringExtra("gameResult")
            if (gameResult != null) {
                updateScoresBasedOnResult(gameResult)
            }
        }
    }

    private fun updateScoresBasedOnResult(result: String) {
        when (result) {
            "Jogador" -> playerScore++
            "PC" -> pcScore++
        }
        val playerScoreTextView: TextView = findViewById(R.id.player_score)
        val pcScoreTextView: TextView = findViewById(R.id.pc_score)
        updateScores(playerScoreTextView, pcScoreTextView)
        saveScores()
    }
}