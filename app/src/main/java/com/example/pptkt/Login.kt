package com.example.pptkt

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class Login : AppCompatActivity() {

    private var TIET_nombreJugador: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        TIET_nombreJugador = findViewById<TextInputEditText>(R.id.nombreJugador)
    }

    fun onClick(view: View?) {
        val nombreJugador = TIET_nombreJugador?.text.toString().trim()

        if (!nombreJugador.isNullOrEmpty()) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("jugador", nombreJugador)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Primero debes escribir tu nombre", Toast.LENGTH_SHORT).show()
            TIET_nombreJugador?.requestFocus()
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(TIET_nombreJugador, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}
