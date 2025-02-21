package dam.moviles.proyecto10.modelo

import android.content.SharedPreferences

class GestorCredencialesSharedPreferences(
    val sharedPreferences: SharedPreferences
) : GestorCredenciales {
    override fun guardarCredenciales(nombre: String, clave: String): Boolean {
        var r = true
        try {
            sharedPreferences.edit()
                .putString("usuario", nombre)
                .putString("clave", clave)
                .commit()
        } catch (e:Exception) {
            r = false
        }
        return r
    }

    override fun borrarCredenciales(): Boolean = this.guardarCredenciales("","")

    override fun getCredencialesGuardadas(): Credenciales? {
        var c = Credenciales(
            checkNotNull(sharedPreferences.getString("usuario","")),
            checkNotNull(sharedPreferences.getString("clave",""))
        )
        return if(c.usuario.isEmpty()) null else c
    }

}