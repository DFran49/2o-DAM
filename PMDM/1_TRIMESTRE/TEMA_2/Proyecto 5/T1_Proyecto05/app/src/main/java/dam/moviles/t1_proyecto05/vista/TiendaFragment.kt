package dam.moviles.t1_proyecto05.vista

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dam.moviles.t1_proyecto05.MainActivity
import dam.moviles.t1_proyecto05.R
import dam.moviles.t1_proyecto05.databinding.FragmentTiendaBinding
import dam.moviles.t1_proyecto05.viewmodel.TiendaFragmentViewModel


class TiendaFragment : Fragment() {
    private lateinit var binding:FragmentTiendaBinding
    private lateinit var viewModel: TiendaFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater, container)
        inicializarViewModel()
        inicializarToolbar()
        inicializarRecyclerView()
        return binding.root
    }

    private fun inicializarBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentTiendaBinding.inflate(inflater,container,false)
    }

    private fun inicializarViewModel() {
        viewModel = ViewModelProvider(this).get(TiendaFragmentViewModel::class.java)
        viewModel.cargarProductos(requireContext())
    }

    private fun inicializarToolbar() {
        val a = activity as MainActivity
        a.setSupportActionBar(binding.materialToolbar)
        a.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_toolbar,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        })
    }

    private fun inicializarRecyclerView() {
        Log.d("Lista",viewModel.productos.get(0).nombre)
        val adapter = ProductoAdapter(viewModel.productos) { holder ->
            Toast.makeText(
                requireContext(),
                "Seleccionado ${holder.producto.nombre}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}