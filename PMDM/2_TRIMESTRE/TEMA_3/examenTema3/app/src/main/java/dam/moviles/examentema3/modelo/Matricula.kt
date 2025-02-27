package dam.moviles.examentema3.modelo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Matricula(
    val nombre:String,
    val edad:Int,
    @Json(name = "cod_curso") val curso:String,
)
