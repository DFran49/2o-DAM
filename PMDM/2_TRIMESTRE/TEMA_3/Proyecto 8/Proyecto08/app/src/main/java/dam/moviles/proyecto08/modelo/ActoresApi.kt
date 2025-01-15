package dam.moviles.proyecto08.modelo

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ActoresApi {
    @GET("http://192.168.40.197/APIRest_BBDDActoresV9/crud/leer.php")
    suspend fun consultarTodosActores():List<Actor>

    @GET("http://192.168.40.197/APIRest_BBDDActoresV9/crud/leer.php")
    suspend fun consultarActor(
        @Query("id") idActorConsultado:Int
    ):Actor

    @POST("http://192.168.40.197/APIRest_BBDDActoresV9/crud/insertar.php")
    suspend fun insertarActor(
        @Body actor:Actor
    ):RespuestaServidor

    @POST("http://192.168.40.197/APIRest_BBDDActoresV9/crud/actualizar.php")
    suspend fun actualizarActor(
        @Body actor:Actor
    ):RespuestaServidor

    @POST("http://192.168.40.197/APIRest_BBDDActoresV9/crud/borrar.php")
    suspend fun borrarActor(
        @Body i:Id
    ):RespuestaServidor
}