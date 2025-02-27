package dam.moviles.examentema3.modelo

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.Query

class AcademiaRepository {
    val academiaApi: AcademiaApi
    init {
        academiaApi = Retrofit.Builder()
            .baseUrl("http://10.0.2.2/academia/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    suspend fun consultarTodosCursos():Cursos = academiaApi.consultarTodosCursos()
    suspend fun consultarCurso(codigo:String):Curso = academiaApi.consultarCurso(codigo)
    suspend fun matricular(nombre:String,edad:Int,codigo:String):Respuesta = academiaApi.matricular(Matricula(nombre,edad,codigo))
}