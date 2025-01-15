package dam.moviles.proyecto08.modelo

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Id(
    val id:Int
)
