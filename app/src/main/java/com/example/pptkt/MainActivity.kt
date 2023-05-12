package com.example.pptkt

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var nombreJugador = ""
    private var datoJugador = 0
    private var datoComputadora = 0
    private lateinit var tvNombreJugador: TextView
    private var puntosJugador = 0
    private var puntosComputadora = 0
    private lateinit var dataJugador: ImageView
    private lateinit var dataComputadora: ImageView
    private lateinit var tvPuntosJugador: TextView
    private lateinit var tvPuntosComputadora: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            // obtener nombre jugador del activity login
            nombreJugador = intent.getStringExtra("jugador").toString()

            tvNombreJugador = findViewById(R.id.NombreJugador)
            tvNombreJugador.text = nombreJugador

            // casteo
            dataJugador = findViewById(R.id.DataJugador)
            dataComputadora = findViewById(R.id.DataComputer)
            tvPuntosJugador = findViewById(R.id.ScoreJugador)
            tvPuntosComputadora = findViewById(R.id.ScoreComputer)
        } catch (e: Exception) {
            Toast.makeText(this, "Error 1 $e", Toast.LENGTH_LONG).show()
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.BtnSalir -> {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
            }
            R.id.Piedra -> {
                datoJugador = 1
                // ejecuto metodo aleatorio de computadora
                aleatorioDataComputadora()
                Fin()
            }
            R.id.Papel -> {
                datoJugador = 2
                // ejecuto metodo aleatorio de computadora
                aleatorioDataComputadora()
                Fin()
            }
            R.id.Tijera -> {
                datoJugador = 3
                // ejecuto metodo aleatorio de computadora
                aleatorioDataComputadora()
                Fin()
            }
        }
    }

    // metodo dato jugador
    private fun resultadoJugador() {
        when (datoJugador) {
            1 -> dataJugador.setImageResource(R.drawable.piedra)
            2 -> dataJugador.setImageResource(R.drawable.papel)
            3 -> dataJugador.setImageResource(R.drawable.tijera)
        }
        resultado()
    }

    private fun aleatorioDataComputadora() {
        // clase Aleatorio
        var numAleatorio = (Math.random() * 3).toInt() + 1
        when (numAleatorio) {
            1 -> {
                dataComputadora.setImageResource(R.drawable.piedra2)
                datoComputadora = 1
            }
            2 -> {
                dataComputadora.setImageResource(R.drawable.papel2)
                datoComputadora = 2
            }
            3 -> {
                dataComputadora.setImageResource(R.drawable.tijera2)
                datoComputadora = 3
            }
        }
        // ejecuto el metodo ResultadoJugador
        resultadoJugador()
    }

    // compara quien gana
    private fun resultado() {
        try {
            when {
                datoJugador == datoComputadora -> {

                }
                datoJugador == 1 && datoComputadora == 2 -> {
// piedra vs papel (gano papel computer
                    puntosComputadora++
                    tvPuntosComputadora.text = puntosComputadora.toString()

                }
                datoJugador == 2 && datoComputadora == 3 -> {
                    // papel vs tijera (gano tijera computer)
                    puntosComputadora++
                    tvPuntosComputadora.text = puntosComputadora.toString()

                }
                datoJugador == 3 && datoComputadora == 1 -> {
                    // tijera vs piedra (gano piedra computer)
                    puntosComputadora++
                    tvPuntosComputadora.text = puntosComputadora.toString()

                }
                else -> {
                    // en cualquier otro caso, gana el jugador
                    puntosJugador++
                    tvPuntosJugador.text = puntosJugador.toString()

                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error 2 $e", Toast.LENGTH_LONG).show()
        }
    }
    private fun Fin() {
        // verifica si algún jugador ha alcanzado los 10 puntos
        if (puntosJugador == 10 || puntosComputadora == 10) {
            // muestra un mensaje según el ganador
            if (puntosJugador > puntosComputadora) {
                Toast.makeText(this, "GANASTE", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "PERDISTE", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}
