package carla.etchetto.trabajopractico3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import carla.etchetto.trabajopractico3.Modelos.Despachante
import carla.etchetto.trabajopractico3.Modelos.Pedido

class MainActivity : AppCompatActivity() {

    lateinit var hacerPedido: Button
    lateinit var verTopDespachante: Button
    lateinit var listaPedidos: Button
    var pedidos: ArrayList<Pedido> = ArrayList<Pedido>()
    var despachantes: ArrayList<Despachante> = ArrayList<Despachante>()
    val despachante = Despachante("Juan",0)
    val despachante2 = Despachante("Miguel",0)
    val despachante3 = Despachante("Pablo",0)
    val despachante4 = Despachante("Roberto",0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        despachantes.add(despachante)
        despachantes.add(despachante2)
        despachantes.add(despachante3)
        despachantes.add(despachante4)
        IniciarElementos()
        hacerPedido.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, seleccionProducto::class.java)
            intent.putExtra("pedidos", pedidos)
            intent.putExtra("despachantes",despachantes)
            startActivity(intent)
        })

        verTopDespachante.setOnClickListener(View.OnClickListener {
            val intent2 = Intent(this, topDespachante::class.java)
           intent2.putExtra("despachantes",despachantes)
            startActivity(intent2)
        })
        listaPedidos.setOnClickListener(View.OnClickListener {
            val intent3 = Intent(this, listadoPedidos::class.java)
            intent3.putExtra("pedidos", pedidos)
            startActivity(intent3)
        })




    }

    private fun IniciarElementos() {

        hacerPedido = findViewById(R.id.i_iniciarPedido)
        verTopDespachante = findViewById(R.id.m_verTop)
        listaPedidos = findViewById(R.id.m_pedidos)

        val intento = intent
        val datos = intent.extras

        if (datos != null) {
            val listaPedidos = intento.getSerializableExtra("pedidos") as ArrayList<Pedido>
            pedidos = listaPedidos
            val listaDespachantes = intento.getSerializableExtra("despachantes") as ArrayList<Despachante>
            despachantes = listaDespachantes

        }

    }

    private fun CrearDespachante(nombre: String, venta: Int): Despachante
    {
        var despachanteNuevo = Despachante (nombre, venta)
        return despachanteNuevo
    }


}