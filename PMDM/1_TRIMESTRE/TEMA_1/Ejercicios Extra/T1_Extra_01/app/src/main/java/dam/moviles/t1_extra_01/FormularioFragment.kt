package dam.moviles.t1_extra_01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dam.moviles.t1_extra_01.databinding.FragmentFormularioBinding

private var _binding : FragmentFormularioBinding? = null
public val binding : FragmentFormularioBinding
    get() = checkNotNull(_binding) {"Debes usar Binding entre onCreateView y onDestroyView"}
private lateinit var viewModel: FormularioFragmentViewModel

class FormularioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarViewModel()
        inicializarBinding(inflater, container)
        viewModel.iniciarApp()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun inicializarBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentFormularioBinding.inflate(inflater, container, false)
    }

    private fun inicializarViewModel() {
        viewModel = ViewModelProvider(this).get(FormularioFragmentViewModel::class.java)
    }
}