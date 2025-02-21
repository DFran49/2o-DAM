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
import dam.moviles.proyecto10.modelo.FirebaseLogin
import dam.moviles.proyecto10.modelo.NavegadorError
import dam.moviles.proyecto10.modelo.getLoginManager

class NuevoUsuarioFragment : Fragment(), NavegadorError {
    private var _binding: FragmentNuevoUsuarioBinding? = null
    val binding: FragmentNuevoUsuarioBinding
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
        binding.btnNuevoUsuario.setOnClickListener {
            getLoginManager().crearUsuario(
                binding.txtUsuario.text.toString(),
                binding.txtClave.text.toString(),
                binding.txtNombreCompleto.text.toString(),
                binding.txtDireccion.text.toString(),
                binding.txtCiudad.text.toString(),
                lambdaExito = {
                    val nc = findNavController()
                    val flecha = NuevoUsuarioFragmentDirections
                                    .actionNuevoUsuarioFragmentToCorreoEnviadoFragment()
                    nc.navigate(flecha)
                },
                lambdaError = { m -> navegarPantallaError(m) }
            )
        }
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