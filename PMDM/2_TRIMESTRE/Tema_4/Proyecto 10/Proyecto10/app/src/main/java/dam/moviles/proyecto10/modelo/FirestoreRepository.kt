package dam.moviles.proyecto10.modelo

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirestoreRepository : FireFotosRepository {
    override fun crearUsuario(u: Usuario, lambdaExito: () -> Unit, lambdaError: (String) -> Unit) {
        Firebase.firestore
            .collection("firefotos")
            .document(u.id)
            .set(u)
            .addOnSuccessListener {
                lambdaExito()
            }.addOnFailureListener {  excepcion ->
                lambdaError(excepcion.message.toString())
            }
    }

    override fun getUsuario(
        id: String,
        lambdaExito: (Usuario) -> Unit,
        lambdaError: (String) -> Unit
    ) {

    }

    override suspend fun crearAlbum(u: Usuario, titulo: String, descripcion: String): Album {
        TODO("Not yet implemented")
    }

    override suspend fun getAlbumes(u: Usuario): List<Album> {
        TODO("Not yet implemented")
    }

    override suspend fun getFotos(a: Album): List<Foto> {
        TODO("Not yet implemented")
    }

    override suspend fun crearFoto(
        a: Album,
        titulo: String,
        fecha: String,
        hora: String,
        urlFoto: String
    ): Foto {
        TODO("Not yet implemented")
    }

    override suspend fun actualizarFoto(f: Foto) {
        TODO("Not yet implemented")
    }

    override suspend fun borrarFoto(f: Foto) {
        TODO("Not yet implemented")
    }

}