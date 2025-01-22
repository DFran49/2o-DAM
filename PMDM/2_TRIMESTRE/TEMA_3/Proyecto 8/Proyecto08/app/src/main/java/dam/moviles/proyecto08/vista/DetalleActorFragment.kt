package dam.moviles.proyecto08.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dam.moviles.proyecto08.R
import dam.moviles.proyecto08.databinding.FragmentDetalleActorBinding
import dam.moviles.proyecto08.modelo.Actor
import dam.moviles.proyecto08.modelo.ActoresRepository
import dam.moviles.proyecto08.modelo.ponerFotoActor
import kotlinx.coroutines.launch

class DetalleActorFragment : Fragment() {
    lateinit var binding: FragmentDetalleActorBinding
    var idActor:Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater,container)
        inicializarInterfaz()
        ponerBotonAtrasToolbar()
        inicializarBotones()
        return binding.root
    }

    fun ponerBotonAtrasToolbar() {
        val mainActivity = activity as MainActivity
        mainActivity.binding.materialToolbar.apply {
            setNavigationIconTint(android.R.drawable.ic_menu_revert)
            setNavigationOnClickListener {
                val nc = findNavController()
                nc.popBackStack()
            }
        }
    }

    fun quitarBotonAtrasToolbar() {
        val mainActivity = activity as MainActivity
        mainActivity.binding.materialToolbar.navigationIcon = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        quitarBotonAtrasToolbar()
    }

    fun inicializarBotones() {
        inicializarBotonActualizar()
        inicializarBotonBorrar()
        inicializarBotonInsertar()
        inicializarBotonActualizarInterfaz()
        inicializarBotonActualizarFoto()
    }

    private fun inicializarBotonActualizarFoto() {
        binding.btnActualizarFoto.setOnClickListener {
            val actorMentira = Actor(-1,"",0,0,
                binding.txtUrlFoto.toString(),"")
            binding.imgFoto.ponerFotoActor(actorMentira)
        }
    }

    private fun inicializarBotonActualizarInterfaz() {
        TODO("Not yet implemented")
    }

    private fun inicializarBotonInsertar() {
        binding.btnAAdirActor.setOnClickListener {
            val nc = findNavController()
            lifecycleScope.launch {
                if (ActoresRepository().insertarActor(
                        binding.txtNombre.text.toString(),
                        binding.txtEdad.text.toString().toInt(),
                        if (binding.chkVivo.isChecked) 1 else 0,
                        binding.txtUrlFoto.toString(),
                        ""
                    )) {
                    // navegamos a la pantalla previa
                    nc.popBackStack()
                } else {
                    // navegamos a la pantalla de error
                    val flecha = DetalleActorFragmentDirections.actionDetalleActorFragmentToErrorFragment("no se pudo borrar el actor")
                    nc.navigate(flecha)
                }
            }
        }
    }

    private fun inicializarBotonBorrar() {
        binding.btnEliminarActor.setOnClickListener {
            val nc = findNavController()
            lifecycleScope.launch {
                if (ActoresRepository().borrarActor(checkNotNull(idActor))) {
                    // navegamos a la pantalla previa
                    nc.popBackStack()
                } else {
                    // navegamos a la pantalla de error
                    val flecha = DetalleActorFragmentDirections.actionDetalleActorFragmentToErrorFragment("no se pudo borrar el actor")
                    nc.navigate(flecha)
                }
            }
        }
    }

    private fun inicializarBotonActualizar() {
        binding.btnActualizarActor.setOnClickListener {
            val nc = findNavController()
            lifecycleScope.launch {
                if (ActoresRepository().actualizarActor(
                    checkNotNull(idActor),
                    binding.txtNombre.text.toString(),
                    binding.txtEdad.text.toString().toInt(),
                    if (binding.chkVivo.isChecked) 1 else 0,
                    binding.txtUrlFoto.toString(),
                    ""
                )) {
                    // navegamos a la pantalla previa
                    nc.popBackStack()
                } else {
                    // navegamos a la pantalla de error
                    val flecha = DetalleActorFragmentDirections.actionDetalleActorFragmentToErrorFragment("no se pudo borrar el actor")
                    nc.navigate(flecha)
                }
            }
        }
    }

    fun inicializarInterfaz() {
        val actor : Actor? = DetalleActorFragmentArgs.fromBundle(requireArguments()).actor
        if (actor != null) {
            rellenarActor(actor)
        } else {
            binding.apply {
                btnEliminarActor.visibility = View.GONE
                btnActualizarActor.visibility = View.GONE
            }
        }
    }

    fun rellenarActor(actor: Actor) {
        binding.apply {
            txtNombre.setText(actor.nombre)
            txtEdad.setText(actor.edad.toString())
            txtUrlFoto.setText(actor.fotoUrl)
            chkVivo.isChecked = if(actor.vivo==1) true else false
            imgFoto.ponerFotoActor(actor)
            btnAAdirActor.visibility = View.GONE
        }
        idActor = actor.id
    }

    fun inicializarBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentDetalleActorBinding.inflate(inflater,container,false)
    }
}