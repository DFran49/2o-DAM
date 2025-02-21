package dam.moviles.proyecto10.modelo

class Sesion private constructor() {
    private var _usuario:Usuario? = null
    val usuario:Usuario
        get() = checkNotNull(usuario) {"No hay ningún usuario en la sesión"}

    companion object {
        private var INSTANCIA:Sesion? = null
        fun getInstancia():Sesion {
            if (INSTANCIA == null) {
                INSTANCIA = Sesion()
            }
            return checkNotNull(INSTANCIA)
        }
    }

    fun iniciarSesion(
        correo: String,
        clave: String,
        lambdaExito: (Sesion) -> Unit,
        lambdaError: (String) -> Unit
    ) {
        getLoginManager().login(
            correo,
            clave,
            lambdaExito = { usuario ->
                _usuario = usuario
                lambdaExito(this)
            },
            lambdaError = { m -> lambdaError(m)}
        )
    }

    fun cerrarSesion() {
        _usuario = null
        getLoginManager().logout()
    }

    fun sesionIniciada() = _usuario!=null
}