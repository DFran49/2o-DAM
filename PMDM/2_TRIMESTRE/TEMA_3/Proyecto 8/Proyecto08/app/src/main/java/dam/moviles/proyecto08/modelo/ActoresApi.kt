package dam.moviles.proyecto08.modelo

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

const val API ="http://192.168.40.62/APIRest_BBDDActoresV9/crud/"

interface ActoresApi {
    @GET("${API}leer.php")
    suspend fun consultarTodosActores():List<Actor>

    @GET("${API}leer.php")
    suspend fun consultarActor(
        @Query("id") idActorConsultado:Int
    ):Actor

    @POST("${API}insertar.php")
    suspend fun insertarActor(
        @Body actor:Actor
    ):RespuestaServidor

    @POST("${API}actualizar.php")
    suspend fun actualizarActor(
        @Body actor:Actor
    ):RespuestaServidor

    @POST("${API}borrar.php")
    suspend fun borrarActor(
        @Body i:Id
    ):RespuestaServidor
}