package dam.moviles.t1_proyecto05.modelo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Producto(
    val id:Int,
    val nombre:String,
    val precio:Double,
    val disponible:Boolean,
    @Json(name = "detalles") val informacion:Detalle
)
