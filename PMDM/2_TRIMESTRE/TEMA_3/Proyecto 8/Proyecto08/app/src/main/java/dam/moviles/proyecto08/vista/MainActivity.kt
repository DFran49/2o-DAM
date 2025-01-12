package dam.moviles.proyecto08.vista

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import dam.moviles.proyecto08.R
import dam.moviles.proyecto08.databinding.ActivityMainBinding
import dam.moviles.proyecto08.modelo.ActoresRepository
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicializarBinding()
        inicializarToolbar()
        test3()
        setContentView(binding.root)
    }

    fun test() {
        lifecycleScope.launch {
            try {
                val lista = ActoresRepository().consultarTodosActores()
                lista.forEach { actor ->
                    Log.d("actores",actor.toString())
                }
            } catch (e:Exception) {
                Log.d("actores","Error: ${e.message}")
            }
        }
    }

    fun test2() {
        lifecycleScope.launch {
            val actor = ActoresRepository().consultarActor(1)
            Log.d("actores",actor.toString())
        }
    }

    fun test3() {
        lifecycleScope.launch {
            val funciona = ActoresRepository().insertarActor("Guillermo",78,1,"","")
            Log.d("actores",funciona.toString())
        }
    }

    private fun inicializarBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    fun inicializarToolbar(){
        setSupportActionBar(binding.materialToolbar)
    }
}