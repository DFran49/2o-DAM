package dam.moviles.examentema3.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil3.load
import dam.moviles.examentema3.R
import dam.moviles.examentema3.databinding.FragmentDetalleCursoBinding
import dam.moviles.examentema3.databinding.FragmentMatricularAlumnoBinding
import dam.moviles.examentema3.modelo.AcademiaRepository
import dam.moviles.examentema3.modelo.Curso
import dam.moviles.examentema3.modelo.EntradaCurso
import dam.moviles.examentema3.modelo.EnvoltorioCurso
import dam.moviles.examentema3.modelo.Respuesta
import kotlinx.coroutines.launch

class MatricularAlumnoFragment : Fragment() {
    var _binding: FragmentMatricularAlumnoBinding? = null
    val binding: FragmentMatricularAlumnoBinding
        get() = checkNotNull(_binding)
    lateinit var envoltorio: EnvoltorioCurso
    lateinit var curso: Curso
    lateinit var entrada: EntradaCurso

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
        envoltorio= MatricularAlumnoFragmentArgs.fromBundle(requireArguments()).envoltorio
        curso = envoltorio.curso
        entrada = envoltorio.entrada
    }

    fun inicializarInfo() {
        binding.txtCurso.setText(curso.nombre)
        inicializarBoton()
    }

    fun inicializarBoton() {
        binding.btnMatricularAlumno.setOnClickListener {
            val nombre = binding.txtNombreNuevoAlumno.text.toString()
            val edad = binding.txtEdadAlumno.text.toString().toInt()
            lifecycleScope.launch {

                var respuesta:Respuesta = AcademiaRepository().matricular(nombre,edad,entrada.codigo)
                if (respuesta.codigo == 0) {
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(
                        requireContext(),
                        respuesta.mensaje,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun inicializarBinding(inflater: LayoutInflater,container: ViewGroup?) {
        _binding= FragmentMatricularAlumnoBinding.inflate(inflater,container,false)
    }
}