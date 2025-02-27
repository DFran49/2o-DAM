package dam.moviles.examentema3.vista

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dam.moviles.examentema3.R
import dam.moviles.examentema3.databinding.FragmentSelectorBinding
import dam.moviles.examentema3.modelo.AcademiaRepository
import dam.moviles.examentema3.modelo.Curso
import dam.moviles.examentema3.modelo.Cursos
import dam.moviles.examentema3.modelo.EnvoltorioCurso
import dam.moviles.examentema3.modelo.Respuesta
import dam.moviles.examentema3.viewModel.SelectorViewModel
import kotlinx.coroutines.launch

class SelectorFragment : Fragment() {
    var _binding:FragmentSelectorBinding? = null
    val binding:FragmentSelectorBinding
        get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater,container)
        inicializarRecyclerView()
        return binding.root
    }

    fun inicializarRecyclerView() {
        lifecycleScope.launch {
            var cursos:Cursos = AcademiaRepository().consultarTodosCursos()

            binding.lstCursos.adapter = CursoAdapter(cursos){ holder ->
                lifecycleScope.launch {
                    var curso:Curso = AcademiaRepository().consultarCurso(holder.curso.codigo)
                    var envoltorio = EnvoltorioCurso(curso,holder.curso)
                    val nc = findNavController()
                    val flecha = SelectorFragmentDirections.actionSelectorFragmentToDetalleCursoFragment(envoltorio)
                    nc.navigate(flecha)
                }
            }
        }
    }

    fun inicializarBinding(inflater: LayoutInflater,container: ViewGroup?) {
        _binding= FragmentSelectorBinding.inflate(inflater,container,false)
    }
}