package dam.moviles.examentema3.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil3.load
import dam.moviles.examentema3.R
import dam.moviles.examentema3.databinding.FragmentDetalleCursoBinding
import dam.moviles.examentema3.databinding.FragmentSelectorBinding
import dam.moviles.examentema3.modelo.Curso
import dam.moviles.examentema3.modelo.Cursos
import dam.moviles.examentema3.modelo.EntradaCurso
import dam.moviles.examentema3.modelo.EnvoltorioCurso

class DetalleCursoFragment : Fragment() {
    var _binding:FragmentDetalleCursoBinding? = null
    val binding:FragmentDetalleCursoBinding
        get() = checkNotNull(_binding)
    lateinit var envoltorio:EnvoltorioCurso
    lateinit var curso:Curso
    lateinit var entrada:EntradaCurso

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater,container)
        cargarInfo()
        inicializarInfo()
        return binding.root
    }

    fun cargarInfo() {
        envoltorio= DetalleCursoFragmentArgs.fromBundle(requireArguments()).curso
        curso = envoltorio.curso
        entrada = envoltorio.entrada
    }

    fun inicializarInfo() {
        binding.txtNombreCurso.setText(curso.nombre)
        binding.imgFotoCurso.load("http://10.0.2.2/academia/${curso.foto}")
        binding.txtDescripcion.setText(curso.descripcion)
        inicializarBoton()
    }

    fun inicializarBoton() {
        val nc = findNavController()
        val flecha = DetalleCursoFragmentDirections.actionDetalleCursoFragmentToMatricularAlumnoFragment(envoltorio)
        binding.btnMatricular.setOnClickListener { nc.navigate(flecha) }
        if (entrada.plazas <= entrada.ocupadas) binding.btnMatricular.isEnabled = false
    }

    fun inicializarBinding(inflater: LayoutInflater,container: ViewGroup?) {
        _binding= FragmentDetalleCursoBinding.inflate(inflater,container,false)
    }
}