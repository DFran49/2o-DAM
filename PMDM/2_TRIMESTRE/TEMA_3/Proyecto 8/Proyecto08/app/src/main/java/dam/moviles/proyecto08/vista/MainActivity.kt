package dam.moviles.proyecto08.vista

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dam.moviles.proyecto08.R
import dam.moviles.proyecto08.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicializarBinding()
        setContentView(binding.root)
    }

    private fun inicializarBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    fun inicializarToolbar(){
        setSupportActionBar(binding.materialToolbar)
    }
}