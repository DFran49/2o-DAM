package dam.moviles.proyecto08.vista

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dam.moviles.proyecto08.R
import dam.moviles.proyecto08.databinding.FragmentActoresBinding
import dam.moviles.proyecto08.viewModel.ActoresFragmentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ActoresFragment : Fragment() {

    lateinit var binding : FragmentActoresBinding
    lateinit var viewModel : ActoresFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater,container)
        inicializarViewModel()
        inicializarInterfaz()
        // Inflate the layout for this fragment
        return binding.root
    }

    fun inicializarInterfaz() {
        if (viewModel.mostrarPantallaCarga) {
            iniciarPantallaCarga()
        } else {
            iniciarInterfazPrincipal()
        }
    }

    fun iniciarPantallaCarga() {
        val mainActivity = activity as MainActivity
        mainActivity.binding.materialToolbar.visibility = View.GONE

        lifecycleScope.launch {
            delay(1000)
            viewModel.cargarListaActores(
                lambdaExito = { iniciarInterfazPrincipal()},
                lambdaError = { mensaje -> navegarError(mensaje) }
            )
        }
    }

    fun navegarError(mensajeError:String) {
        val nc = findNavController()
        val flecha = ActoresFragmentDirections
            .actionActoresFragmentToErrorFragment(mensajeError)
        nc.navigate(flecha)
    }

    fun iniciarInterfazPrincipal() {
        binding.capa1.visibility = View.GONE
        binding.capa2.visibility = View.VISIBLE

        val mainActivity = activity as MainActivity
        mainActivity
            .binding.materialToolbar.visibility = View.GONE
        viewModel.mostrarPantallaCarga = false
    }

    private fun inicializarBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentActoresBinding.inflate(inflater,container,false)
    }

    private fun inicializarViewModel() {
        viewModel = ViewModelProvider(this).get(
            ActoresFragmentViewModel::class.java
        )
    }
}