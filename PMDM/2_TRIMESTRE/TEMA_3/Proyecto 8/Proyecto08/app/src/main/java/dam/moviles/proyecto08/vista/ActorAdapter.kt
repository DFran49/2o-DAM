package dam.moviles.proyecto08.vista

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dam.moviles.proyecto08.databinding.ActorBinding
import dam.moviles.proyecto08.modelo.Actor

class ActorAdapter(var actores:List<Actor>, val lambda: (ActorHolder) -> Unit) : RecyclerView.Adapter<ActorHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActorBinding.inflate(inflater, parent, false)
        return ActorHolder(binding)
    }

    override fun getItemCount(): Int = actores.size

    override fun onBindViewHolder(holder: ActorHolder, position: Int) {
        val actor = actores.get(position)
        holder.mostrarActor(actor)
        holder.binding.root.setOnClickListener{}
        lambda(holder)
    }

}