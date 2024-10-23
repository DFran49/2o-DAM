package dam.moviles.ejercicio01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dam.moviles.ejercicio01.databinding.FragmentFormularioBinding

class FormularioFragment : Fragment() {
    private var _binding : FragmentFormularioBinding? = null
    private val binding : FragmentFormularioBinding
        get() = checkNotNull(_binding) {"Debes usar Binding entre onCreateView y onDestroyView"}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater, container)
        inicializarBotones()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun inicializarBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentFormularioBinding.inflate(inflater, container, false)
    }

    private fun inicializarBotones() {
        suma()
        resta()
        multiplicacion()
        division()
    }

    private fun obtenerNumeros() : Pair<Double, Double> {
        val primero:Double = binding.txtDecimal1.text.toString().toDouble()
        val segundo:Double = binding.txtDecimal2.text.toString().toDouble()
        return Pair(primero,segundo)
    }

    private fun suma() {
        binding.btnSuma.setOnClickListener() {
            val (primero,segundo) = obtenerNumeros()
            binding.txtResultado.setText((primero+segundo).toString())
        }
    }

    private fun resta() {
        binding.btnResta.setOnClickListener() {
            val (primero,segundo) = obtenerNumeros()
            binding.txtResultado.setText((primero-segundo).toString())
        }
    }

    private fun multiplicacion() {
        binding.btnMulti.setOnClickListener() {
            val (primero,segundo) = obtenerNumeros()
            binding.txtResultado.setText((primero*segundo).toString())
        }
    }

    private fun division() {
        binding.btnDiv.setOnClickListener() {
            val (primero,segundo) = obtenerNumeros()
            binding.txtResultado.setText((primero/segundo).toString())
        }
    }
}