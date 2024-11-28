package dam.moviles.t1_proyecto05.modelo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Detalle(
    val color:String,
    val tallas:List<String>
)
