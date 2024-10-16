package dam.moviles.t1_proyecto02_8

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dam.moviles.t1_proyecto02_8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var controles: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controles = ActivityMainBinding.inflate(layoutInflater)
        setContentView(controles.root)
    }
}