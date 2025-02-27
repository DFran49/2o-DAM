package dam.moviles.examentema3.modelo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Respuesta(
    @Json(name = "cod_error") val codigo:Int,
    @Json(name = "desc_error") val mensaje:String
):Serializable
