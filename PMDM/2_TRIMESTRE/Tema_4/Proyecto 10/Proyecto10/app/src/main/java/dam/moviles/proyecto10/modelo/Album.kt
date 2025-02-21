package dam.moviles.proyecto10.modelo

import java.io.Serializable

data class Album(
    val id:String = "",
    val idUsuario:String = "",
    val titulo:String = "",
    val descripcion:String = "",
) : Serializable
