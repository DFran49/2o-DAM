package dam.moviles.t1_proyecto05.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import dam.moviles.t1_proyecto05.modelo.Producto
import dam.moviles.t1_proyecto05.modelo.Tienda

class TiendaFragmentViewModel(): ViewModel() {
    var productos: List<Producto> = emptyList()

    fun cargarProductos(context: Context) {
        if (productos.isEmpty()) {
            productos = Tienda.inicializar(context).stock
        }
    }
}