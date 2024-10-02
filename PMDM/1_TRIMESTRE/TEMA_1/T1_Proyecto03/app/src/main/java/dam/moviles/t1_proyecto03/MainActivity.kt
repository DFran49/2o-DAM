package dam.moviles.t1_proyecto03

import android.os.Bundle
import android.os.SystemClock
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dam.moviles.t1_proyecto03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mochila:ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicializarMochila()
        inicializarBotones()

    }

    private fun inicializarBotones() {
        mochila.apply {
            btnInicio.setOnClickListener { iniciar() }
            btnStop.setOnClickListener { detener() }
            btnPausa.setOnClickListener { pausa() }
            btnReiniciar.setOnClickListener { reiniciar() }
        }

    }

    private fun iniciar() {
        habilitarBotonStart(false)
        when(situacion) {
            Situacion.PAUSA -> reiniciarManteniendoTiempo()
            Situacion.PARADO -> reiniciar()
            Situacion.FUNCIONANDO -> {}
        }
        /*if (situacion == Situacion.PAUSA) {
            reiniciarManteniendoTiempo()
        } else {
            reiniciar()
        }*/

        mochila.chrReloj.start()
        situacion = Situacion.FUNCIONANDO
    }

    private fun reiniciarManteniendoTiempo() {
        base = SystemClock.elapsedRealtime() - tiempoTranscurrido
        mochila.chrReloj.base = base
    }

    private fun detener() {
        habilitarBotonStart(true)
        mochila.chrReloj.stop()
        reiniciar()
        situacion = Situacion.PARADO
    }

    private fun pausa() {
        habilitarBotonStart(true)
        mochila.chrReloj.stop()
        tiempoTranscurrido = SystemClock.elapsedRealtime() - base
        situacion = Situacion.PAUSA
    }

    private fun reiniciar() {
        base = SystemClock.elapsedRealtime()
        mochila.chrReloj.base = base
    }

    private fun inicializarMochila() {
        mochila = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mochila.root)
    }

    private fun habilitarBotonStart(habilitado:Boolean) {
        mochila.apply {
            btnInicio.isEnabled = habilitado
            btnStop.isEnabled = !habilitado
            btnPausa.isEnabled = !habilitado
            btnReiniciar.isEnabled = !habilitado
        }
        /*mochila.btnInicio.isEnabled = habilitado
            mochila.btnStop.isEnabled = !habilitado
            mochila.btnPausa.isEnabled = !habilitado
            mochila.btnReiniciar.isEnabled = !habilitado*/
    }
}