package dam.mobiles.proyecto5.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dam.mobiles.proyecto5.modelo.Producto
import dam.mobiles.proyecto5.modelo.Tienda

class TiendaFragmentViewModel: ViewModel() {
    var productos:List<Producto> =emptyList()

    fun cargarProductos(context: Context){
        productos= Tienda.cargarDatos(context).stock
    }
}