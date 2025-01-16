package dam.moviles.proyecto08.vista

import androidx.recyclerview.widget.RecyclerView
import dam.moviles.proyecto08.databinding.ActorBinding
import dam.moviles.proyecto08.modelo.Actor
import dam.moviles.proyecto08.modelo.ponerFotoActor

// el objetivo de esta clase es transportar la mochila binding de un actor.xml
class ActorHolder(val binding:ActorBinding) : RecyclerView.ViewHolder(binding.root) {
    lateinit var actor:Actor

    fun mostrarActor(a:Actor) {
        actor = a
        // vamos a poner la foto del actor en binding.imgActor (EXTENSION FUNCTION)
        binding.imgActor.ponerFotoActor(a)
    }
}