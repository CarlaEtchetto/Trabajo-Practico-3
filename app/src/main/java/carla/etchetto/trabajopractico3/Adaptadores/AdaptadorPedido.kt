package carla.etchetto.trabajopractico3.Adaptadores


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import carla.etchetto.trabajopractico3.Modelos.Pedido
import carla.etchetto.trabajopractico3.R
import carla.etchetto.trabajopractico3.listadoPedidos

class AdaptadorPedido(private val dataSet: ArrayList<Pedido>): RecyclerView.Adapter<AdaptadorPedido.ViewHolder>()

{
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view)
    {

        val foto: ImageView
        val item: TextView
        val precio: TextView
        val caja: TextView
        val nombreDespachante: TextView
        init {

            foto = view.findViewById(R.id.p_imagen)
            item = view.findViewById(R.id.p_item)
            precio = view.findViewById(R.id.p_precio)
            caja= view.findViewById(R.id.p_cajero)
            nombreDespachante=view.findViewById(R.id.p_despachante)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pedidos,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.item.text= "Producto: " + dataSet[position].item
        holder.precio.text="Precio: " + dataSet[position].precio.toString()  + "$"
        holder.caja.text="Caja: " + dataSet[position].cajero
        holder.nombreDespachante.text="Despacho: " + dataSet[position].despachante
        holder.foto.setImageResource(dataSet[position].foto)


    }
}