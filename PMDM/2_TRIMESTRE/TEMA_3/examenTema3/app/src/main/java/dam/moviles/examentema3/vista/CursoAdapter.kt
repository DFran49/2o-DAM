package dam.moviles.examentema3.vista

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dam.moviles.examentema3.databinding.CursoBinding
import dam.moviles.examentema3.modelo.Cursos

class CursoAdapter(
    var cursos: Cursos,
    val lambda:(CursoHolder) -> Unit
) : RecyclerView.Adapter<CursoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CursoBinding.inflate(inflater,parent,false)
        return CursoHolder(binding)
    }

    override fun onBindViewHolder(holder: CursoHolder, position: Int) {
        val curso = cursos.cursos.get(position)
        holder.mostrarCurso(curso)
        holder.binding.root.setOnClickListener{
            lambda(holder)
        }
    }

    override fun getItemCount(): Int = cursos.cursos.size

}