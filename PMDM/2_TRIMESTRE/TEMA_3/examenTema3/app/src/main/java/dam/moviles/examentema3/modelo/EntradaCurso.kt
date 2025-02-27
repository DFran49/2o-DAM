package dam.moviles.examentema3.modelo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class EntradaCurso(
    val codigo:String,
    val nombre:String,
    @Json(name = "num_plazas") val plazas:Int,
    @Json(name = "plazas_ocupadas") val ocupadas:Int,
    val departamento:String
):Serializable
