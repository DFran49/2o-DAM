package dam.moviles.examentema3.vista

import androidx.recyclerview.widget.RecyclerView
import dam.moviles.examentema3.databinding.CursoBinding
import dam.moviles.examentema3.modelo.Curso
import dam.moviles.examentema3.modelo.EntradaCurso

class CursoHolder(val binding:CursoBinding) : RecyclerView.ViewHolder(binding.root) {
    lateinit var curso:EntradaCurso

    fun mostrarCurso(c:EntradaCurso) {
        curso = c
        val libres = curso.plazas-curso.ocupadas
        binding.txtLibres.setText("Quedan ${libres} plazas")
        if (libres == 0) binding.txtLibres.setText("Plazas agotadas")

        binding.txtPlazas.setText("Plazas: ${curso.plazas}")
        binding.txtNombre.setText(curso.nombre)
    }
}