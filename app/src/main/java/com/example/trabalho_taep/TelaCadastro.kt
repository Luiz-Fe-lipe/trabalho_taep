package com.example.trabalho_taep

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth


class TelaCadastro : AppCompatActivity() {


    data class Item(
        var login: String? = null,
        var senha: String? = null,
        var email: String? = null
    )

        var btCadastrar = findViewById<Button>(R.id.bttCadastrarse)
        var btVoltar = findViewById<Button>(R.id.bttVoltar)

        lateinit var auth: FirebaseAuth
        //auth = FirebaseAuth.getInstace()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.tela_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.telacadastro)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btCadastrar.setOnClickListener{

            Toast.makeText(this, "Cadastrar-se Apertado", Toast.LENGTH_SHORT).show()

        }

        btVoltar.setOnClickListener{
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}