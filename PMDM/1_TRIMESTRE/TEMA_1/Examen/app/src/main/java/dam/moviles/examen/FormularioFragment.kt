package dam.moviles.examen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dam.moviles.examen.databinding.FragmentFormularioBinding


class FormularioFragment : Fragment() {
    private lateinit var binding: FragmentFormularioBinding
    private lateinit var viewModel: FormularioFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater, container)
        inicializarViewModel()
        inicializarBotones()
        if (viewModel.partidas != 0) {
            escribirVictorias()
        }

        if (viewModel.cartaJ1 != 0) {
            asignarImagen(viewModel.cartaJ1, binding.imgJugador1)
            asignarImagen(viewModel.cartaJ2, binding.imgJugador2)
            binding.btnJugarJugador.isEnabled = false
            binding.btnJugarAndroid.isEnabled = true
            binding.btnOtra.isEnabled = false
        }

        if (viewModel.cartaA1 != 0) {
            asignarImagen(viewModel.cartaA1, binding.imgAndroid1)
            asignarImagen(viewModel.cartaA2, binding.imgAndroid2)
            escribirPuntos()
            binding.btnJugarJugador.isEnabled = false
            binding.btnJugarAndroid.isEnabled = false
            binding.btnOtra.isEnabled = true
        }

        return binding.root
    }

    private fun inicializarBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentFormularioBinding.inflate(inflater, container, false)
    }

    private fun inicializarViewModel() {
        viewModel = ViewModelProvider(this).get(FormularioFragmentViewModel::class.java)
    }

    private fun inicializarBotones() {
        inicializarJugador()
        inicializarAndroid()
        inicializarOtra()
    }

    private fun inicializarJugador() {
        binding.btnJugarJugador.setOnClickListener {
            viewModel.generarCartasJugador()
            binding.btnJugarJugador.isEnabled = false
            binding.btnJugarAndroid.isEnabled = true
            asignarImagen(viewModel.cartaJ1, binding.imgJugador1)
            asignarImagen(viewModel.cartaJ2, binding.imgJugador2)
        }
    }

    private fun inicializarAndroid() {
        binding.btnJugarAndroid.setOnClickListener {
            viewModel.generarCartasAndroid()
            asignarImagen(viewModel.cartaA1, binding.imgAndroid1)
            asignarImagen(viewModel.cartaA2, binding.imgAndroid2)
            viewModel.calcularPuntos()
            escribirPuntos()
            binding.btnJugarAndroid.isEnabled = false
            binding.btnOtra.isEnabled = true
        }
    }

    private fun inicializarOtra() {
        binding.btnOtra.setOnClickListener {
            mostrarMensaje()
            reiniciar()
            viewModel.reiniciarCartas()
            binding.btnJugarJugador.isEnabled = true
            binding.btnJugarAndroid.isEnabled = false
            binding.btnOtra.isEnabled = false
        }
    }

    private fun escribirPuntos() {
        binding.txtJugador.setText("Jugador: " + viewModel.puntosJ)
        binding.txtAndroid.setText("Android: " + viewModel.puntosA)
    }

    private fun reiniciar() {
        asignarImagen(0,binding.imgJugador1)
        asignarImagen(0,binding.imgJugador2)
        asignarImagen(0,binding.imgAndroid1)
        asignarImagen(0,binding.imgAndroid2)
        escribirVictorias()
    }

    private fun escribirVictorias() {
        binding.txtPartidasTotales.setText("Partidas totales: " + viewModel.partidas)
        binding.txtVictorias.setText("Partidas ganadas: " + viewModel.victorias)
        binding.txtDerrotas.setText("Partidas totales: " + viewModel.derrotas)
        binding.txtJugador.setText("Jugador")
        binding.txtAndroid.setText("Android")
    }

    private fun mostrarMensaje() {
        var mensaje: String = ""
        var victoria: Boolean? = viewModel.calcularVictorias()
        if (victoria == null) {
            mensaje = "Empate"
        } else if (victoria == true) {
            mensaje = "Gana el jugador"
        } else {
            mensaje = "Gana el móvil"
        }
        Toast.makeText(binding.root.context,
            mensaje,
            Toast.LENGTH_SHORT).show()
    }

    private fun asignarImagen(carta: Int, imagen:ImageView) {
        when(carta) {
            0 -> imagen.setImageResource(R.drawable.reverso)
            1 -> imagen.setImageResource(R.drawable.carta_1)
            2 -> imagen.setImageResource(R.drawable.carta_2)
            3 -> imagen.setImageResource(R.drawable.carta_3)
            4 -> imagen.setImageResource(R.drawable.carta_4)
            5 -> imagen.setImageResource(R.drawable.carta_5)
            6 -> imagen.setImageResource(R.drawable.carta_6)
            7 -> imagen.setImageResource(R.drawable.carta_7)
            10 -> imagen.setImageResource(R.drawable.carta_10)
            11 -> imagen.setImageResource(R.drawable.carta_11)
            12 -> imagen.setImageResource(R.drawable.carta_12)
        }
    }
}