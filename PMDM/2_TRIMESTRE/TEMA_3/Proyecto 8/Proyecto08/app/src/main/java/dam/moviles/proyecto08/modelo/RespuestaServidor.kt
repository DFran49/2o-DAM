package dam.moviles.proyecto08.modelo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RespuestaServidor(
    val info:String
)
