package dam.mobiles.proyecto5.vista

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dam.mobiles.proyecto5.ViewModel.TiendaFragmentViewModel
import dam.mobiles.proyecto5.modelo.Producto
import dam.mobiles.proyecto5.modelo.cargarDatosJson
import dam.mobiles.proyecto5.modelo.convertirJsonTienda
import dam.moviles.practica_5.R
import dam.moviles.practica_5.databinding.FragmentTiendaBinding

@Suppress("UNREACHABLE_CODE")
class TiendaFragment : Fragment() {
    private lateinit var binding: FragmentTiendaBinding
    private lateinit var viewModel: TiendaFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        inicializaarViewMOdel()
        inicializarBinding(inflater,container)
        return inflater.inflate(R.layout.fragment_tienda, container, false)
        /*val json= cargarDatosJson(requireContext())       //Esto es una comprobacion
        val tienda= convertirJsonTienda(json)
        Log.d("JSON TIENDA",tienda.toString())*/
        inicializarRecyclerView()
        return binding.root
    }
    fun inicializaarViewMOdel(){
        viewModel= ViewModelProvider(this).get(TiendaFragmentViewModel::class.java)
        viewModel.cargarProductos(requireContext())
    }
    fun inicializarBinding(inflater: LayoutInflater,container: ViewGroup?){
        binding= FragmentTiendaBinding.inflate(inflater,container,false)
    }

    fun inicializarRecyclerView() {
        binding.lstProductos.adapter = ProductoAdapter(viewModel.productos){holder ->
            Toast.makeText(requireContext(),
                "Ha pulsado en ${holder.producto.nombre}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}