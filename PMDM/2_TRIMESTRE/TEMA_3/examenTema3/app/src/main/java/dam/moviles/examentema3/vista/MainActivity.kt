package dam.moviles.examentema3.vista

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dam.moviles.examentema3.R
import dam.moviles.examentema3.databinding.ActivityMainBinding

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
        binding= ActivityMainBinding.inflate(layoutInflater)
    }
}