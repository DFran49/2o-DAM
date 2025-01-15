package dam.moviles.proyecto08.modelo

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

// el objetivo de esta clase es transportar
// un objeto que implementa ActoresApi y trabajar con él
class ActoresRepository {
    val actoresApi:ActoresApi
    init {
        // este bloque es una continuación del constructor
        actoresApi = Retrofit.Builder()
            .baseUrl("http://10.0.2.2/APIRest_BBDDActoresV9/crud/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build() // hasta aquí creaos un objeto retrofit
            .create() // el objeto Retrofit crea un ActoresApi
    }

    suspend fun consultarTodosActores():List<Actor> =
        actoresApi.consultarTodosActores()

    suspend fun consultarActor(idActor:Int):Actor =
        actoresApi.consultarActor(idActor)

    // si nombre es null, quiero que me devuelva todos los actores
    // si nombre no es null, quiero que me devuelva los actores que contienen
    // ese nombre
    suspend fun consultarActores(nombre:String?):List<Actor> =
        if (nombre == null) {
            actoresApi.consultarTodosActores()
        } else {
            actoresApi.consultarTodosActores()
                .filter { actor -> actor.nombre.contains(nombre, true) }
        }

    suspend fun insertarActor(
        nombre:String,
        edad:Int,
        vivo:Int,
        fotoUrl:String,
        fotoCodificada:String
    ):Boolean {
        val actor = Actor(
            id = -1, // autoincrementado
            nombre,edad,vivo,fotoUrl,fotoCodificada
        )
        val respuesta = actoresApi.insertarActor(actor)
        return respuesta.mensaje.contains("Creado!")
    }

    suspend fun actualizarActor(
        id:Int,
        nombre:String,
        edad:Int,
        vivo:Int,
        fotoUrl:String,
        fotoCodificada:String
    ): Boolean {
        val actor = Actor(id,nombre,edad,vivo,fotoUrl,fotoCodificada)
        val respuesta = actoresApi.actualizarActor(actor)
        return respuesta.mensaje.contains("actualizado")
    }

    suspend fun borrarActor(
        id:Int,
    ): Boolean {
        val I = Id(id)
        val respuesta = actoresApi.borrarActor(I)
        return respuesta.mensaje.contains("borrado con éxito")
    }
}