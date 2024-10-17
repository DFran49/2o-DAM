package dam.moviles.ejercicio1

import android.graphics.Color
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var colorName:String = ""
    var colorValue = 0

    fun setColores() {
        val (nombre, valor) = elegirColor()
        colorName = nombre
        colorValue = valor
    }

    fun elegirColor() : Pair<String, Int> {
        val colores = mapOf(
            "Rojo" to Color.RED,
            "Amarillo" to Color.YELLOW,
            "Verde" to Color.GREEN,
            "Azul" to Color.BLUE,
            "Negro" to Color.BLACK,
            "Naranja" to Color.parseColor("#FFA500")
        )
        return colores.entries.random().toPair()
    }
}