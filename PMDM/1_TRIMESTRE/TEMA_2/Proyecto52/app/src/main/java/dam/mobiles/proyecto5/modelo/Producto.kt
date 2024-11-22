package dam.mobiles.proyecto5.modelo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Producto(
    @Json(name = "id")
    val id:Int,
    val nombre:String,
    val precio: Double,
    val disponible:Boolean,
    val detalles: Detalle
)
