package dam.moviles.proyecto10.modelo

import java.io.Serializable

data class Foto(
    val id:String = "",
    val idAlbum:String = "",
    val idUsuario:String = "",
    val titulo:String = "",
    val fecha:String = "",
    val hora:String = "",
    val urlFoto:String = ""
) : Serializable
