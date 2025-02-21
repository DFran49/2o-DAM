package dam.moviles.proyecto10.vista

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import dam.moviles.proyecto10.R
import dam.moviles.proyecto10.databinding.FragmentLoginBinding
import dam.moviles.proyecto10.modelo.NavegadorError
import dam.moviles.proyecto10.modelo.Sesion
import dam.moviles.proyecto10.modelo.getGestorCredenciales

class LoginFragment : Fragment(),NavegadorError {
    private var _binding:FragmentLoginBinding? = null
    val binding:FragmentLoginBinding
        get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater,container)
        inicializarInterfaz()
        inicializarBotones()
        return binding.root
    }

    private fun inicializarBotones() {
        binding.txtNuevoUsuario.setOnClickListener {
            val nc = findNavController()
            val flecha = LoginFragmentDirections.actionLoginFragmentToNuevoUsuarioFragment()
            nc.navigate(flecha)
        }

        binding.btnLogin.setOnClickListener {
            hacerLogIn(
                binding.txtUsuario.text.toString(),
                binding.txtClave.text.toString()
            )
        }
    }

    private fun hacerLogIn(nombre: String, clave: String) {
        Sesion.getInstancia().iniciarSesion(
            nombre,
            clave,
            lambdaExito = { sesion ->
                if (binding.chkRecordar.isChecked) {
                    getGestorCredenciales(requireActivity()).guardarCredenciales(nombre,clave)
                }

                val nc = findNavController()
                val flecha = LoginFragmentDirections.actionLoginFragmentToPrincipalFragment()
                nc.navigate(flecha)
                Log.d("hola","Entra")
            },
            lambdaError = {error ->
                navegarPantallaError(error)
                Log.d("hola","no entra")
            }
        )
    }

    private fun inicializarInterfaz() {
        if (Sesion.getInstancia().sesionIniciada()) {
            activity?.finish()
            System.exit(0)
        } else {
            val credenciales = getGestorCredenciales(requireActivity()).getCredencialesGuardadas()
            if (credenciales == null) {
                mostrarCapaLigIn()
            } else {
                hacerLogIn(credenciales.usuario,credenciales.clave)
            }

        }
    }

    fun mostrarCapaLigIn() {
        binding.capa1.visibility = View.GONE
        binding.capa2.visibility = View.VISIBLE
    }

    override fun getNavController(): NavController = findNavController()

    override fun getFlechaNavegacionPantallaError(m: String): NavDirections
        = LoginFragmentDirections.actionLoginFragmentToErrorFragment(m)

    private fun inicializarBinding(inflater: LayoutInflater,container: ViewGroup?) {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}