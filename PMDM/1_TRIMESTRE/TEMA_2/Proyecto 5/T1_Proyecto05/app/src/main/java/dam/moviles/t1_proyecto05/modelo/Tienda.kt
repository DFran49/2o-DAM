package dam.moviles.t1_proyecto05.modelo

import android.content.Context
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import dam.moviles.t1_proyecto05.R

@JsonClass(generateAdapter = true)
data class Tienda(@Json(name = "productos") val stock:List<Producto>) {
    companion object {
        fun inicializar(context: Context):Tienda =
            crearTienda(cargarJson(context))
    }
}

fun cargarJson(context: Context) =
context                                 // Objeto que representa la app
        .resources                      // Objeto que representa la carpeta res
        .openRawResource(R.raw.datos)   // obtiene un InputStream para datos.json
        .bufferedReader()               // obtiene un bufferedReader para el InputStream
        .readText()                     // lee en un String todo el contenido del BufferedReader

fun crearTienda(json:String):Tienda {
    val moshi = Moshi.Builder().build()                         // obtenemos la librería Moshi
    val adapter = moshi.adapter(Tienda::class.java)             // obtenemos un conversor de json a objetos
    val tienda:Tienda = checkNotNull(adapter.fromJson(json))    // convierte el String en un objeto
    return tienda
}