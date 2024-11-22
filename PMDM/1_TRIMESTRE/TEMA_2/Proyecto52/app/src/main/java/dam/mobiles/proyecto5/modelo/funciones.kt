package dam.mobiles.proyecto5.modelo

import android.R.raw
import android.content.Context
import com.squareup.moshi.Moshi
import dam.moviles.practica_5.R

/*


Cuando estamos en Main Activity, MainActivity es el context (this)
Cuando estamos en un Fragment, el context se obitene
con el metodo requireContext()

 */
/*fun cargarDatosJson(context:Context):String {
    val carpetaRes = context.resources
    val  archivo:InputStream= carpetaRes.openRaw Resources( R.raw.datos)
    val bufferedReader = archivo.bufferedReader
    val texto= bufferedReader.readText
}*/
fun cargarDatosJson(context: Context):String=
    context //Context
        .resources      //carpeta res
        .openRawResource(R.raw.datos)   //InputStream
        .bufferedReader()       //BufferedReader
        .readText()     //leer el archivo
fun convertirJsonTienda(json:String):Tienda{
    val moshi:Moshi = Moshi.Builder().build()
    val adaptador =moshi.adapter(Tienda::class.java)
    val tienda:Tienda?=adaptador.fromJson(json)
    return  checkNotNull(tienda)
}