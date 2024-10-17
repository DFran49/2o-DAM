package dam.moviles.ejercicio2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import dam.moviles.ejercicio2.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicializarViewModel()
        inicializarViewBinding()
        inicializarVidas()
        inicializarBoton()
    }

    private fun inicializarViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private fun inicializarViewBinding() {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    private fun inicializarVidas() {
        viewBinding.txtVidas.setText("Las vidas restantes son: " + viewModel.vidas)
    }

    private fun inicializarBoton() {
        viewBinding.btnAdivinar.setOnClickListener() {
            if (viewBinding.txtInput.text.toString().toInt() == viewModel.numero) {
                mostrarTostada("Enhorabuena! El número secreto era " + viewModel.numero)
            } else if (viewModel.vidas == 0) {
                mostrarTostada("Lo siento, se le han acabado las vidas. El número era " + viewModel.numero)
            } else {
                comprobarNum()
            }
        }
    }

    private fun comprobarNum() {
        if (viewBinding.txtInput.text.toString().toInt() < viewModel.numero) {
            mostrarTostada("El número es mayor")
        } else {
            mostrarTostada("El número es menor")
        }
        viewModel.vidas -= 1
        inicializarVidas()
    }

    private fun mostrarTostada(mensaje: String) {
        Toast.makeText(
            this,
            mensaje,
            Toast.LENGTH_SHORT
        ).show()
    }
}