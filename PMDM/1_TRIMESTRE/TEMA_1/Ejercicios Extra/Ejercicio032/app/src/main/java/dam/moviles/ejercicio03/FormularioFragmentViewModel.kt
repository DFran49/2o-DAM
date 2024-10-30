package dam.moviles.ejercicio03

import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class FormularioFragmentViewModel : ViewModel() {
    var numero: Int = -1
    var nivel: Int = 1
    var vidas: Int = 10
    var intentos: Int = 0
    var puntos: Int = 0
    var suposicion: Int = -1

    fun inicializarBoton() {
        binding.btnAdivinar.setOnClickListener() {
            suposicion = binding.txtNumero.text.toString().toInt()
            if (suposicion == numero) {
                intentoCorrecto()
            } else if (suposicion < numero) {
                intentoFallido("Te has quedado corto")
            } else {
                intentoFallido("Te has pasado")
            }
            binding.txtMensaje.isVisible = true
        }
    }

    private fun intentoCorrecto() {
        asignarPuntos((nivel*10) - intentos)
        asignarVidas(10)
        asignarNivel(nivel+1)
        asignarIntentos(0)
        generarNumero()
    }

    private fun intentoFallido(string: String) {
        averiguarColores()
        if (vidas == 0) {
            intentos ++
            asignarIntentos(intentos)
            Toast.makeText(
                binding.root.context,
                "El número secreto era: " + numero,
                Toast.LENGTH_SHORT).show()
            Thread.sleep(5000)
            reiniciar()
        } else {
            binding.txtMensaje.setText(string)
            vidas --
            intentos ++
            asignarVidas(vidas)
            asignarIntentos(intentos)
        }
    }

    fun generarNumero() {
        numero = Random.nextInt(1,100)
        Log.d("MOSTRAR", ""+numero)
    }

    private fun asignarPuntos(int: Int) {
        puntos = int
        binding.txtPuntos.setText("Puntos: " + puntos)
    }

    private fun asignarVidas(int: Int) {
        vidas = int
        binding.txtVidas.setText("Vidas: " + vidas)
    }

    private fun asignarIntentos(int: Int) {
        intentos = int
        binding.txtIntentos.setText("Intentos: " + intentos)
    }

    private fun asignarNivel(int: Int) {
        nivel = int
        binding.txtNivel.setText("Nivel: " + nivel)
    }

    private fun averiguarColores() {
        var color: Int = 0;
        if (Math.abs(suposicion-numero) < 20) {
            color = Color.RED
        } else if(Math.abs(suposicion-numero) < 40) {
            color = Color.rgb(244,127,38)
        } else if(Math.abs(suposicion-numero) < 60) {
            color = Color.YELLOW
        } else if(Math.abs(suposicion-numero) < 80) {
            color = Color.parseColor("#2271b3")
        } else {
            color = Color.parseColor("#252850")
        }
        Log.d("MOSTRAR 2", ""+Math.abs(suposicion-numero))
        binding.txtMensaje.setTextColor(color)
    }

    private fun reiniciar() {
        asignarVidas(10)
        asignarIntentos(0)
        asignarNivel(1)
        asignarPuntos(0)
        generarNumero()
        binding.txtMensaje.isVisible = false
    }
}