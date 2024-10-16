package dam.moviles.t1_proyecto02_5

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dam.moviles.t1_proyecto02_5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var controles:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controles = ActivityMainBinding.inflate(layoutInflater)
        setContentView(controles.root)
        this.inicializarBotones()
    }

    fun inicializarBotones() {
        controles.btnEnviar.setOnClickListener() {
            val texto:String = "Para: " + controles.txtPara.text.toString() + "\n Mensaje: " + controles.txtMensaje.text.toString()
            Toast.makeText(
                this,
                texto,
                Toast.LENGTH_LONG
            ).show()
            Log.d("MENSAJITO",texto)
        }
    }
}