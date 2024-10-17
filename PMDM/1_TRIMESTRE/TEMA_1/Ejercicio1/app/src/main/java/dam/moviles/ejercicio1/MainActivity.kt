package dam.moviles.ejercicio1

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import dam.moviles.ejercicio1.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var controles: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicializarViewModel()
        inicializarBinding()
        inicializarTexto()
    }

    fun inicializarViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    fun inicializarBinding() {
        controles = ActivityMainBinding.inflate(layoutInflater)
        setContentView(controles.root)
    }

    fun inicializarTexto() {
        if (viewModel.colorName.equals("")) {
            viewModel.setColores()
        }
        controles.txtColor.setText("Este texto está escrito en color " + viewModel.colorName)
        controles.txtColor.setTextColor(viewModel.colorValue)
    }


}