package dam.moviles.t1_proyecto01_6

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dam.moviles.t1_proyecto01_6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var controles: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controles = ActivityMainBinding.inflate(layoutInflater)
        setContentView(controles.root)
        this.inicializarBotones()
    }

    fun inicializarBotones() {
        this.inicializarHola()
        this.inicializarAdios()
    }

    fun inicializarHola() {
        controles.btnHola.setOnClickListener() {
            Toast.makeText(
                this,
                getString(R.string.saludo),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun inicializarAdios() {
        controles.btnAdios.setOnClickListener() {
            Toast.makeText(
                this,
                getString(R.string.despedida),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}