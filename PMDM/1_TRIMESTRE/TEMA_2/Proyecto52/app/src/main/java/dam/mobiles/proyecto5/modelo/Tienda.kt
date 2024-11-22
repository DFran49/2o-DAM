package dam.mobiles.proyecto5.modelo

import android.content.Context
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tienda(
   @Json(name ="productos") val stock:List<Producto>
){
   companion object{
      fun cargarDatos(context: Context):Tienda =
         convertirJsonTienda(cargarDatosJson(context))
   }
}
