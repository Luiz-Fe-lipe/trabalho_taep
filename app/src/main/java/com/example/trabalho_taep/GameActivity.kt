package com.example.trabalho_taep

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private lateinit var pcImageView: ImageView
    private lateinit var radioGroup: RadioGroup
    private lateinit var resultTextView: TextView
    private val handler = Handler()
    private val images = intArrayOf(R.drawable.pedra, R.drawable.papel, R.drawable.tesoura)
    private var currentImageIndex = 0
    private var isPlaying = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        pcImageView = findViewById(R.id.pc_image_view)
        radioGroup = findViewById(R.id.radio_group)
        resultTextView = findViewById(R.id.result_text_view)
        val jokenpoButton: Button = findViewById(R.id.jokenpo_button)

        // Iniciar a rotação das imagens do PC
        startImageRotation()

        // Clique no botão JOKENPO
        jokenpoButton.setOnClickListener {
            stopImageRotation()
            val result = determineWinner()
            returnToMainActivityWithResult(result)
        }
    }

    private fun startImageRotation() {
        handler.post(object : Runnable {
            override fun run() {
                if (isPlaying) {
                    currentImageIndex = Random.nextInt(3)
                    pcImageView.setImageResource(images[currentImageIndex])
                    handler.postDelayed(this, 100)
                }
            }
        })
    }

    private fun stopImageRotation() {
        isPlaying = false
    }

    private fun determineWinner(): String {
        val selectedOption = when (radioGroup.checkedRadioButtonId) {
            R.id.radio_pedra -> 0
            R.id.radio_papel -> 1
            R.id.radio_tesoura -> 2
            else -> -1
        }

        return when {
            selectedOption == -1 -> {
                resultTextView.text = "Escolha uma opção!"
                "Empate"
            }
            selectedOption == currentImageIndex -> {
                resultTextView.text = "Empate!"
                "Empate"
            }
            (selectedOption == 0 && currentImageIndex == 2) ||
                    (selectedOption == 1 && currentImageIndex == 0) ||
                    (selectedOption == 2 && currentImageIndex == 1) -> {
                resultTextView.text = "Você venceu!"
                "Jogador"
            }
            else -> {
                resultTextView.text = "PC venceu!"
                "PC"
            }
        }
    }

    private fun returnToMainActivityWithResult(result: String) {
        handler.postDelayed({
            val intent = Intent()
            intent.putExtra("gameResult", result)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }, 2000) // 2000 milissegundos = 2 segundos
    }
}