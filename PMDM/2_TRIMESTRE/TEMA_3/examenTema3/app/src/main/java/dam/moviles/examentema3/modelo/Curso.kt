package dam.moviles.examentema3.modelo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Curso(
    val nombre:String,
    val descripcion:String,
    val foto:String
):Serializable
