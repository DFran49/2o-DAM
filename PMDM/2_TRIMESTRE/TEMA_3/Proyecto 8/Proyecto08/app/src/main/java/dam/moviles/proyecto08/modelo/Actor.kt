package dam.moviles.proyecto08.modelo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Actor (
    val id:Int,
    val nombre:String,
    val edad:Int,
    @Json(name="activo") val vivo:Int,
    @Json(name="fotourl") val fotoUrl:String,
    @Json(name="fotocodif") val fotoCodif:String
):Serializable