package dam.moviles.t1_extra_01

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel

class FormularioFragmentViewModel : ViewModel() {
    private var resultado: String = ""

    fun iniciarApp() {
        if (resultado != "") {
            binding.txtResultado.setText(resultado)
        }
        inicializarBotones()
    }

    private fun obtenerNumeros() : Pair<Double, Double> {
        val primero:Double = binding.txtDecimal1.text.toString().toDouble()
        val segundo:Double = binding.txtDecimal2.text.toString().toDouble()
        return Pair(primero,segundo)
    }

    private fun inicializarBotones() {
        suma()
        resta()
        multiplicacion()
        division()
    }

    private fun suma() {
        binding.btnSuma.setOnClickListener() {
            val (primero,segundo) = obtenerNumeros()
            resultado = (primero+segundo).toString()
            binding.txtResultado.setText(resultado)
        }
    }

    private fun resta() {
        binding.btnResta.setOnClickListener() {
            val (primero,segundo) = obtenerNumeros()
            resultado = (primero-segundo).toString()
            binding.txtResultado.setText(resultado)
        }
    }

    private fun multiplicacion() {
        binding.btnMulti.setOnClickListener() {
            val (primero,segundo) = obtenerNumeros()
            resultado = (primero*segundo).toString()
            binding.txtResultado.setText(resultado)
        }
    }

    private fun division() {
        binding.btnDiv.setOnClickListener() {
            val (primero,segundo) = obtenerNumeros()
            if (segundo.equals(0.0)) {
                alerta("No se puede dividir por 0")
            } else {
                resultado = (primero/segundo).toString()
                binding.txtResultado.setText(resultado)
            }
        }
    }

    private fun alerta(mensaje: String) {
        Toast.makeText(binding.root.context,
            mensaje,
            Toast.LENGTH_SHORT).show()
    }
}