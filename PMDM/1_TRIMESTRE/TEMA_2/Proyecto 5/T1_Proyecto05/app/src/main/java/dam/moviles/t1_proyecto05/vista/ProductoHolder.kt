package dam.moviles.t1_proyecto05.vista

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import dam.moviles.t1_proyecto05.databinding.ItemProductoBinding
import dam.moviles.t1_proyecto05.modelo.Producto

class ProductoHolder(val binding: ItemProductoBinding)
    : RecyclerView.ViewHolder(binding.root){
        lateinit var producto: Producto

        fun mostrarProducto(p: Producto) {
            producto = p
            binding.txtNombre.text = p.nombre
            binding.txtPrecio.text = if(p.disponible) p.precio.toString() else "No disponible"
            binding.txtInfo.text =
                "Color: ${p.informacion.color} Tallas: ${p.informacion.tallas.joinToString(",")}"
            binding.imgProducto.setImageDrawable(cargarFoto("p:${p.id.toString()}"))
        }

        private fun cargarFoto(nombre:String): Drawable {
            // obtiene un objeto que representa a la app
            val context = binding.root.context
            // obtiene la carpeta de recursos
            val carpetaRes = context.resources
            // obtengo el id que tiene la foto buscada, en la carpeta raw
            val idFoto = carpetaRes.getIdentifier(nombre,"drawable","dam.moviles.t1_proyecto05")
            // cargamos la imagen
            val imagen = carpetaRes.getDrawable(idFoto, null)
            return imagen
        }
}