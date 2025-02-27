package dam.moviles.examentema3.modelo

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Cursos(
    val cursos:List<EntradaCurso>
):Serializable
