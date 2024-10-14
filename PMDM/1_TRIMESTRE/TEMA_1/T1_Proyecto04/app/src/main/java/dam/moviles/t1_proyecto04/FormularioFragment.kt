package dam.moviles.t1_proyecto04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dam.moviles.t1_proyecto04.databinding.FragmentFormularioBinding


class FormularioFragment : Fragment() {

    private lateinit var mochilaBinding: FragmentFormularioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarMochilaBinding(inflater,container)
        inicializarMaterialToolbar()
        return mochilaBinding.root
    }

    fun inicializarMochilaBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mochilaBinding = FragmentFormularioBinding.inflate(inflater,container,false)
    }

    fun inicializarMaterialToolbar() {
        (activity as MainActivity).setSupportActionBar(mochilaBinding.materialToolbar)
    }

}