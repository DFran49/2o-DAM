package dam.moviles.t1_proyecto06.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dam.moviles.t1_proyecto06.R
import dam.moviles.t1_proyecto06.databinding.FragmentBienvenidaBinding

class BienvenidaFragment : Fragment() {
    lateinit var binding: FragmentBienvenidaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater, container)
        inicializarEventos()
        return binding.root
    }

    fun inicializarBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentBienvenidaBinding.inflate(inflater,container,false)
    }

    fun inicializarEventos() {
        binding.btnComenzar.setOnClickListener {
            val navController = findNavController()
            val accion = BienvenidaFragmentDirections.actionBienvenidaFragmentToMensajeFragment()
            navController.navigate(accion)
        }
    }
}