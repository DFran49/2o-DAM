package dam.mobiles.proyecto5.vista

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dam.mobiles.proyecto5.modelo.Producto
import dam.moviles.practica_5.databinding.ProductoBinding

class ProductoHolder(val binding:ProductoBinding)
    : RecyclerView.ViewHolder(binding.root) {

        lateinit var producto: Producto

        //Este método recibe un producto y actualiza la
        //vista "producto.xml" con los datoss de dicho producto
        fun mostrarProducto(p: Producto) {
            producto = p
            binding.txtNombre.text = p.nombre
            binding.txtPrecio.text = if(p.disponible) p.precio.toString() else "No disponible"
            binding.txtInformacion.text =
                "Color: ${p.detalles.color} Tallas: ${p.detalles.tallas.joinToString { "," }} "

        }
}