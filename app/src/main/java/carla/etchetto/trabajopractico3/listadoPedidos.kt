package carla.etchetto.trabajopractico3

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import carla.etchetto.trabajopractico3.Adaptadores.AdaptadorPedido
import carla.etchetto.trabajopractico3.Modelos.Despachante
import carla.etchetto.trabajopractico3.Modelos.Pedido

class listadoPedidos : AppCompatActivity() {

    var pedidos: ArrayList<Pedido> = ArrayList<Pedido>()
    lateinit var cantidad: TextView
    lateinit var listadoPedidosRV: RecyclerView

    @SuppressLint("WrongConstant")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_pedidos)
        IniciarElementos()
        cantidad.setText("Cantidad total de Pedidos: " + pedidos.size.toString())
        listadoPedidosRV = findViewById(R.id.r_listadoPedidos_rv)
        listadoPedidosRV.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        listadoPedidosRV.adapter=AdaptadorPedido(pedidos)
    }

    private fun IniciarElementos(){
        cantidad = findViewById(R.id.lp_total)
        val intento = intent
        val datos = intent.extras

        if (datos != null) {
            val listaPedidosFinalizados = intento.getSerializableExtra("pedidos") as ArrayList<Pedido>
            pedidos = listaPedidosFinalizados
        }
    }

}