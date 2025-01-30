package dam.moviles.proyecto07

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dam.moviles.proyecto07.databinding.FragmentAyudaBinding

class AyudaFragment : Fragment() {
    private var  _binding: FragmentAyudaBinding? = null
    private val binding: FragmentAyudaBinding
        get() = checkNotNull(_binding) {"uso incorrecto del objeto binding"}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater, container)
        inicializarTexto()
        return binding.root
    }

    private fun inicializarTexto() {
        val texto:String = getString(R.string.ayuda)
        binding.txtAyuda.text = Html.fromHtml(texto,Html.FROM_HTML_MODE_COMPACT)
    }

    private fun inicializarBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentAyudaBinding.inflate(inflater,container,false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}