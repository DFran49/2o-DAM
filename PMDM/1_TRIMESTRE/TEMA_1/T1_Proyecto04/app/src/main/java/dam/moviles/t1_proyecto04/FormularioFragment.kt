package dam.moviles.t1_proyecto04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import dam.moviles.t1_proyecto04.databinding.FragmentFormularioBinding


class FormularioFragment : Fragment() {

    private lateinit var mochilaBinding: FragmentFormularioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarMochilaBinding(inflater,container)
/*
        inicializarMaterialToolbar()
*/
        desactivarBotones()
        inicializarEventos()
        inicializarBotonFlotante()
        return mochilaBinding.root
    }

    fun inicializarMochilaBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mochilaBinding = FragmentFormularioBinding.inflate(inflater,container,false)
    }

/*    fun inicializarMaterialToolbar() {
        (activity as MainActivity).setSupportActionBar(mochilaBinding.materialToolbar)
    }*/

    private fun getListaBotones() : List<ImageView> =
        listOf(
            mochilaBinding.btnBebida1,
            mochilaBinding.btnBebida2,
            mochilaBinding.btnBebida3,
            mochilaBinding.btnBebida4,
            mochilaBinding.btnBebida5,
            mochilaBinding.btnBebida6
        )


    private fun desactivarBotones() {
        getListaBotones().forEach(){ boton ->
            boton.alpha = 0.6F
        }
    }

    private fun inicializarEventos() {
        getListaBotones().forEach(){ boton ->
            boton.setOnClickListener(){
                desactivarBotones()
                boton.alpha = 1F
            }
        }
    }

    private fun getBebidaSeleccionada() : Int? {
        val lista = getListaBotones()
            .filter { boton -> boton.alpha == 1F }
        return if (lista.isEmpty())
            null
        else
            when(lista.get(0)) {
                mochilaBinding.btnBebida1 -> 1
                mochilaBinding.btnBebida2 -> 2
                mochilaBinding.btnBebida3 -> 3
                mochilaBinding.btnBebida4 -> 4
                mochilaBinding.btnBebida5 -> 5
                mochilaBinding.btnBebida6 -> 6
                else -> throw Exception("Bebida no valida")
            }
    }

    private fun inicializarBotonFlotante() {
        mochilaBinding.btnEnviar.setOnClickListener(){
            if (!mochilaBinding.chkAceptar.isChecked) {
                Snackbar.make(
                    mochilaBinding.btnEnviar,
                    "Debe aceptar los términos para continuar",
                    Snackbar.LENGTH_SHORT
                ).setAction("Aceptar") {
                    mochilaBinding.chkAceptar.isChecked = true
                }.show()
            } else {
                Toast.makeText(
                    context,
                    "Has elegido la bebida ${getBebidaSeleccionada()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}