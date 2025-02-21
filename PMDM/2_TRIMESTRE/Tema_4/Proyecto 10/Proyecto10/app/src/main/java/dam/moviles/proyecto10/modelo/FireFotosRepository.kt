package dam.moviles.proyecto10.modelo

interface FireFotosRepository {
    fun crearUsuario (
        u:Usuario,
        lambdaExito: () -> Unit,
        lambdaError: (String) -> Unit
    )

    fun getUsuario(
        id:String,
        lambdaExito: (Usuario) -> Unit,
        lambdaError: (String) -> Unit
    )

    suspend fun crearAlbum(u:Usuario,titulo:String,descripcion:String):Album
    suspend fun getAlbumes(u:Usuario):List<Album>
    suspend fun getFotos(a:Album):List<Foto>
    suspend fun crearFoto(a:Album,titulo: String,fecha:String,hora:String,urlFoto:String):Foto
    suspend fun actualizarFoto(f:Foto)
    suspend fun borrarFoto(f:Foto)
}