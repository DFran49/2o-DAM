package dam.moviles.t1_proyecto03

import android.os.Bundle
import android.os.SystemClock
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import dam.moviles.t1_proyecto03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mochila:ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicializarViewModel()
        inicializarMochila()
        inicializarBotones()

    }

    private fun inicializarViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private fun inicializarBotones() {
        mochila.apply {
            btnInicio.setOnClickListener { iniciar() }
            btnStop.setOnClickListener { detener() }
            btnPausa.setOnClickListener { pausa() }
            btnReiniciar.setOnClickListener { viewModel.reiniciar(mochila.chrReloj) }
        }

    }

    private fun iniciar() {
        habilitarBotonStart(false)
        when(viewModel.situacion) {
            Situacion.PAUSA -> viewModel.reiniciarManteniendoTiempo(mochila.chrReloj)
            Situacion.PARADO -> viewModel.reiniciar(mochila.chrReloj)
            Situacion.FUNCIONANDO -> {}
        }
        /*if (situacion == Situacion.PAUSA) {
            reiniciarManteniendoTiempo()
        } else {
            reiniciar()
        }*/

        mochila.chrReloj.start()
        viewModel.situacion = Situacion.FUNCIONANDO
    }

    override fun onStart() {
        super.onStart()
        when(viewModel.situacion) {
            Situacion.PARADO -> habilitarBotonStart(true)
            Situacion.FUNCIONANDO -> {
                mochila.chrReloj.base = viewModel.base
                mochila.chrReloj.start()
                habilitarBotonStart(false)
            }
            Situacion.PAUSA -> {
                habilitarBotonStart(true)
                viewModel.reiniciarManteniendoTiempo(mochila.chrReloj)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if (viewModel.situacion == Situacion.FUNCIONANDO) {
            viewModel.actualizarTiempoTranscurrido()
            mochila.chrReloj.stop()
        }
    }

    override fun onRestart() {
        super.onRestart()
        if (viewModel.situacion == Situacion.FUNCIONANDO) {
            viewModel.reiniciarManteniendoTiempo(mochila.chrReloj)
            mochila.chrReloj.start()
        }
    }

    private fun detener() {
        habilitarBotonStart(true)
        mochila.chrReloj.stop()
        viewModel.reiniciar(mochila.chrReloj)
        viewModel.situacion = Situacion.PARADO
    }

    private fun pausa() {
        habilitarBotonStart(true)
        mochila.chrReloj.stop()
        viewModel.actualizarTiempoTranscurrido()
        viewModel.situacion = Situacion.PAUSA
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