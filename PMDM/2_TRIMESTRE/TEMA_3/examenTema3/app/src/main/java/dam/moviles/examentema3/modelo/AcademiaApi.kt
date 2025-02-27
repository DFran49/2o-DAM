package dam.moviles.examentema3.modelo

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AcademiaApi {
    @GET("consultarTodos.php")
    suspend fun consultarTodosCursos():Cursos

    @GET("consultarCurso.php")
    suspend fun consultarCurso(
        @Query("codigo") codigo:String
    ):Curso

    @POST("matricular.php")
    suspend fun matricular(
        @Body matricula: Matricula
    ):Respuesta
}