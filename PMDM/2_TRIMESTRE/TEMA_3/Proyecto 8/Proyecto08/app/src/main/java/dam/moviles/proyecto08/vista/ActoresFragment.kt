package dam.moviles.proyecto08.vista

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dam.moviles.proyecto08.R
import dam.moviles.proyecto08.databinding.FragmentActoresBinding
import dam.moviles.proyecto08.modelo.ActoresRepository
import dam.moviles.proyecto08.viewModel.ActoresFragmentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ActoresFragment : Fragment() {

    lateinit var binding : FragmentActoresBinding
    lateinit var viewModel : ActoresFragmentViewModel
    val menuProvider : MenuProvider = object:MenuProvider{
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            // este método vincula el xml del menú con la toolbar
            menuInflater.inflate(R.menu.menu_toolbar,menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            // recibe un botón de la toolbar y ejecuta el código correspondiente
            when(menuItem.itemId) {
                R.id.btnInsertarActor -> navegarInsertarActor()
                R.id.btnRefrescar -> refrescar()
            }
            return true
        }
    }

    fun navegarInsertarActor() {
        val nc = findNavController()
        val flecha = ActoresFragmentDirections
            .actionActoresFragmentToDetalleActorFragment(null)
        nc.navigate(flecha)
    }

    fun refrescar() {
        lifecycleScope.launch {
            viewModel.cargarListaActores(
                lambdaExito = { iniciarInterfazPrincipal() }, // muestra capa 2
                lambdaError = { mensaje -> navegarError(mensaje) }
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater,container)
        inicializarViewModel()
        ponerMenuToolbar()
        inicializarInterfaz()
        // Inflate the layout for this fragment
        return binding.root
    }

    fun ponerMenuToolbar() {
        val mainActivity = activity as MainActivity
        mainActivity.addMenuProvider(menuProvider)
    }

    fun quitarMenuToolbar() {
        val mainActivity = activity as MainActivity
        mainActivity.removeMenuProvider(menuProvider)
    }

    // este método se llama cuando el fragment
    // deja de ser visible (Abandonamos el fragment)
    override fun onDestroyView() {
        super.onDestroyView()
        quitarMenuToolbar()
    }

    fun inicializarInterfaz() {
        if (viewModel.mostrarPantallaCarga) {
            iniciarPantallaCarga() // muestra capa 1
        } else {
            binding.capa1.visibility = View.GONE
            binding.capa2.visibility = View.VISIBLE
            refrescar()
        }
    }

    fun iniciarPantallaCarga() {
        val mainActivity = activity as MainActivity
        mainActivity.binding.materialToolbar.visibility = View.GONE

        lifecycleScope.launch {
            delay(1000)
            viewModel.cargarListaActores(
                lambdaExito = { iniciarInterfazPrincipal()},
                lambdaError = { mensaje -> navegarError(mensaje) }
            )
        }
    }

    fun navegarError(mensajeError:String) {
        val nc = findNavController()
        val flecha = ActoresFragmentDirections
            .actionActoresFragmentToErrorFragment(mensajeError)
        nc.navigate(flecha)
    }

    fun iniciarInterfazPrincipal() {
        binding.capa1.visibility = View.GONE
        binding.capa2.visibility = View.VISIBLE

        val mainActivity = activity as MainActivity
        mainActivity
            .binding.materialToolbar.visibility = View.GONE
        inicializarRecycerView()
        viewModel.mostrarPantallaCarga = false
    }

    fun inicializarRecycerView() {
        binding.lstActores.adapter = ActorAdapter(viewModel.listaActores) { holder ->
            val nc = findNavController()
            val flecha = ActoresFragmentDirections.actionActoresFragmentToDetalleActorFragment(holder.actor)
            nc.navigate(flecha)
        }
    }

    private fun inicializarBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentActoresBinding.inflate(inflater,container,false)
    }

    private fun inicializarViewModel() {
        viewModel = ViewModelProvider(this).get(
            ActoresFragmentViewModel::class.java
        )
    }
}