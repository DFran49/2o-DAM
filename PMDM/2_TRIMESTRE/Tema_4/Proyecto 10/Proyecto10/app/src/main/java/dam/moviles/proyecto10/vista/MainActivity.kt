package dam.moviles.proyecto10.vista

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dam.moviles.proyecto10.R
import dam.moviles.proyecto10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicializarBinding()
        inicializarToolbar()
        setContentView(binding.root)
    }

    fun inicializarToolbar() {
        setSupportActionBar(binding.materialToolbar)
    }

    fun inicializarBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
    }
}