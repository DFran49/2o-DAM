package dam.moviles.proyecto10.modelo

import android.app.Activity
import android.content.Context
import dam.moviles.proyecto10.vista.MainActivity

interface GestorCredenciales {
    fun guardarCredenciales(nombre:String,clave:String):Boolean
    fun borrarCredenciales():Boolean
    fun getCredencialesGuardadas():Credenciales?

}
fun getGestorCredenciales(activity: Activity):GestorCredenciales {
    val preferencias = activity.getSharedPreferences("firefotos", Context.MODE_PRIVATE)
    return GestorCredencialesSharedPreferences(preferencias)
}