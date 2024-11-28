package dam.moviles.t1_proyecto05.vista

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dam.moviles.t1_proyecto05.databinding.ItemProductoBinding
import dam.moviles.t1_proyecto05.modelo.Producto

class ProductoAdapter(
    var productos:List<Producto>,
    var lambda: (ProductoHolder) -> Unit // código ue se ejecuta al pulsar un item de la lista
) : RecyclerView.Adapter<ProductoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoHolder {
        // obtenemos un Inflater, que sabe crear objetos a partir del xml
        val inflater = LayoutInflater.from(parent.context)
        // obtenemos el objeto binding para item_producto.xml
        val binding = ItemProductoBinding.inflate(inflater,parent,false)
        // devolvemos un ProductoHolder que porta el objeto binding
        return ProductoHolder(binding)
    }

    override fun getItemCount(): Int = productos.size

    override fun onBindViewHolder(holder: ProductoHolder, position: Int) {
        // recupero el Producto de la lista
        val producto = productos.get(position)
        // se lo pongo al holder con su método setProducto
        holder.mostrarProducto(producto)
        // código que se ejecuta al pulsar sobre el elemento raíz de la vista del ProductoHolder
        holder.binding.root.setOnClickListener{
            lambda(holder)      // se llama a la lambda, pasando como parámetro el ProductoHolder
        }
    }

    fun setListaProductos(p:List<Producto>) {
        productos = p
        notifyDataSetChanged()      // hace que el RecyclerView se redibuje
    }

}