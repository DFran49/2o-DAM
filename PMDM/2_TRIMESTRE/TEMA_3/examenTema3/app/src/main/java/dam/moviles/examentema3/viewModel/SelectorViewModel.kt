package dam.moviles.examentema3.viewModel

import androidx.lifecycle.ViewModel
import dam.moviles.examentema3.modelo.Curso
import dam.moviles.examentema3.modelo.Cursos
import dam.moviles.examentema3.modelo.EntradaCurso

class SelectorViewModel :ViewModel() {
    var cursos: Cursos = Cursos(listOf(EntradaCurso("","",0,0,"")))
}