package dam.moviles.proyecto10.modelo

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class FirebaseLogin : LoginManager {
    override fun crearUsuario(
        correo: String,
        clave: String,
        nombre: String,
        direccion: String,
        ciudad: String,
        lambdaExito: (Usuario) -> Unit,
        lambdaError: (String) -> Unit
    ) {
        Firebase.auth
            .createUserWithEmailAndPassword(correo,clave)
            .addOnSuccessListener {
                val usuarioFB = checkNotNull(Firebase.auth.currentUser)
                usuarioFB
                    .sendEmailVerification()
                    .addOnSuccessListener {
                        val u = Usuario(usuarioFB.uid,correo,clave,nombre, direccion, ciudad)
                        lambdaExito(u)
                    }.addOnFailureListener {
                        lambdaError("No se ha podido enviar un correo de verificación")
                    }
            }.addOnFailureListener { excepcion ->
                lambdaError(excepcion.message.toString())
            }
    }

    override fun login(
        correo: String,
        clave: String,
        lambdaExito: (Usuario) -> Unit,
        lambdaError: (String) -> Unit
    ) {
        try {
            Firebase.auth
                .signInWithEmailAndPassword(correo,clave)
                .addOnSuccessListener {
                    val usuarioFB = Firebase.auth.currentUser
                    if (usuarioFB != null) {
                        if (usuarioFB.isEmailVerified) {
                            val u = Usuario("",correo,clave,"","")
                            lambdaExito(u)
                        } else {
                            lambdaError("El correo del usuario no está validado")
                        }
                    } else {
                        lambdaError("No se pudo identificar al usuario")
                    }
                }.addOnFailureListener { excepcion ->
                    lambdaError(excepcion.message.toString())
                }
        } catch (e:Exception) {
            lambdaError(e.message.toString())
        }
    }

    override fun logout() {
        Firebase.auth.signOut()
    }

}