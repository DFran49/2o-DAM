package dam.moviles.proyecto08.viewModel

import androidx.lifecycle.ViewModel
import dam.moviles.proyecto08.modelo.Actor
import dam.moviles.proyecto08.modelo.ActoresRepository

class ActoresFragmentViewModel():ViewModel() {
    var listaActores : List<Actor> = emptyList()
    var mostrarPantallaCarga:Boolean = true

    suspend fun cargarListaActores(
        lambdaExito: () -> Unit,
        lambdaError: (String) -> Unit
    ) {
        try {
            listaActores = ActoresRepository().consultarTodosActores()
            lambdaExito()
        } catch (e:Exception) {
            lambdaError(e.message.toString())
        }
    }
}