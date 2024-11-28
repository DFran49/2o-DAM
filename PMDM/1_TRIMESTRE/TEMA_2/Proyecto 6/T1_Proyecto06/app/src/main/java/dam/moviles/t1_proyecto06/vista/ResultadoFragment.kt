package dam.moviles.t1_proyecto06.vista

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dam.moviles.t1_proyecto06.R
import dam.moviles.t1_proyecto06.databinding.FragmentBienvenidaBinding
import dam.moviles.t1_proyecto06.databinding.FragmentResultadoBinding

class ResultadoFragment : Fragment() {
    lateinit var binding: FragmentResultadoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater, container)

        //TEST comprobamos los datos recibidos
        val mensaje = ResultadoFragmentArgs.fromBundle(requireArguments()).mensaje
        val cifrados = ResultadoFragmentArgs.fromBundle(requireArguments()).cifrados

        Log.d("MOSTRAR", mensaje)
        Log.d("MOSTRAR", cifrados.toString())


        return binding.root
    }

    fun inicializarBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentResultadoBinding.inflate(inflater,container,false)
    }

}