package dam.moviles.pruebacorrutinas

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pruebaCorrutina2()
        setContentView(R.layout.activity_main)
    }

    fun pruebaCorrutina2() {
        // Dispatchers.IO es el almacén de
        // infinitos hilos
        lifecycleScope.launch(Dispatchers.IO) {
            launch {
                Log.d("PruebaCorrutinaLog1","Hilo ${Thread.currentThread().name}")
                delay(2000)
                Log.d("PruebaCorrutinaLog1","Hilo ${Thread.currentThread().name}")
            }
            launch {
                Log.d("PruebaCorrutinaLog1","Hilo ${Thread.currentThread().name}")
                delay(1000)
                Log.d("PruebaCorrutinaLog1","Hilo ${Thread.currentThread().name}")
            }
        }

    }

    fun pruebaCorrutina1() {
        // por defecto el almacén de hilos
        // solo tiene un hilo (el hilo main)
        lifecycleScope.launch {
            // todo esto ya es una corrutina
            // (podemos llamar a puntos de suspensión)
            Log.d("PruebaCorrutinaLog1","Hilo ${Thread.currentThread().name}")
            delay(1000)
            Log.d("PruebaCorrutinaLog1","Hilo ${Thread.currentThread().name}")
        }

    }
}