package dam.moviles.ejercicio1

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dam.moviles.ejercicio1.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var controles: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controles = ActivityMainBinding.inflate(layoutInflater)
        setContentView(controles.root)
        inicializarTexto()
    }

    fun inicializarTexto() {
        val (colorName, colorValue) = elegirColor()
        controles.txtColor.setText("Este texto está escrito en color " + colorName)
        controles.txtColor.setTextColor(colorValue)
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