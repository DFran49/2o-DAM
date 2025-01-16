package dam.moviles.proyecto08.modelo

import android.widget.ImageView
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import dam.moviles.proyecto08.R

// EXTENSION FUNCTION: añadimos a la clase ImageView un método llamado ponerFotoActor()
fun ImageView.ponerFotoActor(actor: Actor):Unit {
    this.load(actor.fotoUrl) { // expresion lambda que configura el proceso de carga
        this.placeholder(R.drawable.loading) // pone una imagen de carga
        this.error(R.drawable.error) // pone una imagen de error en caso de fallo
        this.crossfade(true) // pone una animación de transición entre la carga y la foto cargada
    }
}