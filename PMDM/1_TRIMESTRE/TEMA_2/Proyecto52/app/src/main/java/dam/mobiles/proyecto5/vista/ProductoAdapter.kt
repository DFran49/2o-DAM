package dam.mobiles.proyecto5.vista

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import dam.mobiles.proyecto5.modelo.Producto
import dam.moviles.practica_5.databinding.ProductoBinding

class ProductoAdapter(
    var productos: List<Producto>,       //Lista de productos
    val lambda: (ProductoHolder) -> Unit      //Código fuente que se activa al pulsar un ProductoHolder
) : RecyclerView.Adapter<ProductoHolder>() {

    //Este método crea un ProductoHolder y lo devuelve
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoHolder {
        // conseguimos un inflater
        val inflater = LayoutInflater.from(parent.context)
        //Inicializa la mochilaBinding de producto.xml
        val binding = ProductoBinding.inflate(inflater, parent, false)

        // crea y devuelve un ProductoHolder con esa mochila binding
        return ProductoHolder(binding)
    }

    // devuelve el número de productos de lista
    override fun getItemCount(): Int = productos.size

    // este método pone el producto que ocupa la posición "position" de la lista de productos
    //en el ProductoHolder recibido como primer parámetro
    override fun onBindViewHolder(holder: ProductoHolder, position: Int) {
        // obtenemos el producto de la posición position
        val producto: Producto = productos.get(position)
        // ponemos ese product en el "holder"
        holder.mostrarProducto(producto)
        // hacemos que al pulsar "holder" se activa la expresión lambda
        holder.binding.root.setOnClickListener {
            lambda(holder)
        }
    }

}