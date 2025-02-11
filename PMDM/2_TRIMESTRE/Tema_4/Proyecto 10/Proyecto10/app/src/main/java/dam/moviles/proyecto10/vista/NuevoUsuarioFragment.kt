package dam.moviles.proyecto10.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import dam.moviles.proyecto10.R
import dam.moviles.proyecto10.databinding.FragmentErrorBinding
import dam.moviles.proyecto10.databinding.FragmentNuevoUsuarioBinding
import dam.moviles.proyecto10.modelo.NavegadorError

class NuevoUsuarioFragment : Fragment(), NavegadorError {
    private var _binding: FragmentNuevoUsuarioBinding? = null
    val binding: FragmentNuevoUsuarioBinding
        get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater,container)
        return binding.root
    }

    override fun getNavController(): NavController = findNavController()

    override fun getFlechaNavegacionPantallaError(m: String): NavDirections
            = NuevoUsuarioFragmentDirections.actionNuevoUsuarioFragmentToErrorFragment(m)

    private fun inicializarBinding(inflater: LayoutInflater,container: ViewGroup?) {
        _binding = FragmentNuevoUsuarioBinding.inflate(inflater,container,false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}