package dam.moviles.proyecto07

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dam.moviles.proyecto07.databinding.FragmentBandejaEntradaBinding

class BandejaEntradaFragment : Fragment() {
    private var  _binding: FragmentBandejaEntradaBinding? = null
    private val binding: FragmentBandejaEntradaBinding
        get() = checkNotNull(_binding) {"uso incorrecto del objeto binding"}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater, container)
        return binding.root
    }

    private fun inicializarBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentBandejaEntradaBinding.inflate(inflater,container,false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}