package dam.moviles.proyecto10.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dam.moviles.proyecto10.R
import dam.moviles.proyecto10.databinding.FragmentCorreoEnviadoBinding
import dam.moviles.proyecto10.databinding.FragmentErrorBinding

class ErrorFragment : Fragment() {
    private var _binding: FragmentErrorBinding? = null
    val binding: FragmentErrorBinding
        get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater,container)
        inicializarMensajeError()
        inicializarBoton()
        return binding.root
    }

    fun inicializarBoton() {
        binding.btnAtras.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun inicializarMensajeError() {
        val mensaje = ErrorFragmentArgs.fromBundle(requireArguments()).mensajeError
        binding.txtMensajeError.text = mensaje
    }

    private fun inicializarBinding(inflater: LayoutInflater,container: ViewGroup?) {
        _binding = FragmentErrorBinding.inflate(inflater,container,false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}