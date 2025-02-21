package dam.moviles.proyecto10.modelo

interface LoginManager {
    fun crearUsuario(
        correo:String,
        clave:String,
        nombre:String,
        direccion:String,
        ciudad:String,
        lambdaExito: (Usuario) -> Unit,
        lambdaError: (String) -> Unit
    )

    fun login(
        correo:String,
        clave:String,
        lambdaExito: (Usuario) -> Unit,
        lambdaError: (String) -> Unit
    )

    fun logout()


}

fun getLoginManager():LoginManager = FirebaseLogin()