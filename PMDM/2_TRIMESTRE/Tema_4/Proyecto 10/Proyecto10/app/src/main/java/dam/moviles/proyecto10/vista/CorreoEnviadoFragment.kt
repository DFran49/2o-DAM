package dam.moviles.proyecto10.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dam.moviles.proyecto10.databinding.FragmentCorreoEnviadoBinding


class CorreoEnviadoFragment : Fragment() {
    private var _binding: FragmentCorreoEnviadoBinding? = null
    val binding: FragmentCorreoEnviadoBinding
        get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater,container)
        inicializarBoton()
        return binding.root
    }

    private fun inicializarBoton() {
        binding.btnAtras.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun inicializarBinding(inflater: LayoutInflater,container: ViewGroup?) {
        _binding = FragmentCorreoEnviadoBinding.inflate(inflater,container,false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}