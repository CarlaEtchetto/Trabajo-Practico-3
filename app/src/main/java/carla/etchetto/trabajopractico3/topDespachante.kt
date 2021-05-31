package carla.etchetto.trabajopractico3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import carla.etchetto.trabajopractico3.Modelos.Despachante
import carla.etchetto.trabajopractico3.Modelos.Pedido

class topDespachante : AppCompatActivity() {


    lateinit var listadoLayout: LinearLayout
    var despachantes: ArrayList<Despachante> = ArrayList<Despachante>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_despachante)
        var des = Despachante("",0)
        despachantes.add(des)
        IniciarElementos()

        var view = LayoutInflater.from(this).inflate(R.layout.listado,null)
        listadoLayout=findViewById(R.id.t_listado)
        val nombre: TextView =  view.findViewById(R.id.l_seg)
        val cantVendida: TextView =  view.findViewById(R.id.l_seg_num)
        val tNombre: TextView = view.findViewById(R.id.l_nombre)
        val tVentas: TextView = view.findViewById(R.id.l_ventas)
        despachantes.sortByDescending { it.ventas }

        var topDespachante = despachantes.first()

        tNombre.setText(topDespachante.nombre)
        tVentas.setText(topDespachante.ventas.toString())
       despachantes.forEach{
            if(it.nombre != topDespachante.nombre) {
                nombre.text = nombre.text.toString() + it.nombre + "\n"
                cantVendida.text = cantVendida.text.toString() + it.ventas + "\n"
            }
        }


        listadoLayout.removeAllViews()
        listadoLayout.addView(view)


    }

    private fun IniciarElementos(){

        val intento = intent
        val datos = intent.extras

        if (datos != null) {

            val listaDespachantes = intento.getSerializableExtra("despachantes") as ArrayList<Despachante>
            despachantes = listaDespachantes

        }
    }

}