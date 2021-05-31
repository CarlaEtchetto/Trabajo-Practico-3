package carla.etchetto.trabajopractico3

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import carla.etchetto.trabajopractico3.Modelos.Despachante
import carla.etchetto.trabajopractico3.Modelos.Pedido

class despachante : AppCompatActivity() {
    var pedidos:ArrayList<Pedido> = ArrayList<Pedido>()
    var despachantes: ArrayList<Despachante> = ArrayList<Despachante>()
    lateinit var gusto1: TextView
    lateinit var gusto2: TextView
    lateinit var gusto3: TextView
    lateinit var gusto4: TextView
    lateinit var adicional: TextView
    lateinit var descripcion: TextView
    lateinit var confirmar: Button
    lateinit var validar: Button
    var pedidoOk: Boolean=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_despachante)
        IniciarElementos()
        val numero:Int = pedidos.size-1
        val pedido: Pedido = pedidos.get(numero)
        adicional.setText(pedido.adicional)
        descripcion.setText(pedido.item)
        val foto: ImageView = findViewById(R.id.d_foto)
        foto.setImageResource(pedido.foto)
        CargoGustos(pedido)
        val despachantesNombre= arrayOf("Juan","Pablo", "Miguel", "Roberto")
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, despachantesNombre)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        val spin: Spinner = findViewById(R.id.d_spinner)
        spin.adapter = adapter
        var despachante: String = ""
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                despachante = parent.getItemAtPosition(position).toString()
            }
        }
        validar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(it.context)
            builder.setTitle("EL CLIENTE CONFIRMA EL PEDIDO")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Si"){dialogInterface, i -> Toast.makeText(this, "PEDIDO ACEPTADO", Toast.LENGTH_SHORT).show()
                pedidoOk=true}
            builder.setNegativeButton("No"){dialogInterface, i ->  Toast.makeText(this,"PEDIDO RECHAZADO",Toast.LENGTH_SHORT).show()
                pedidoOk=false}
            builder.setNeutralButton("Cancelar"){dialogInterface, i -> Toast.makeText(this,"CANCELAR POR DIFERENCIAS EN EL PEDIDO",Toast.LENGTH_SHORT).show()
                pedidoOk=false }

            val alerta: AlertDialog = builder.create()
            alerta.setCancelable(false)
            alerta.show()
        })

        confirmar.setOnClickListener(View.OnClickListener {

            var pedidoFinalizado = Pedido (pedido.item.toString(),pedido.cajero.toString(),pedido.helado,pedido.adicional.toString(),despachante,pedido.precio, pedido.foto)

            if(pedidoOk) {
                for (item in despachantes)
                {
                    if (item.nombre==despachante)
                    {
                        item.ventas +=1
                    }
                }
                pedidos.remove(pedido)
                pedidos.add(pedidoFinalizado)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("pedidos", pedidos)
                intent.putExtra("despachantes", despachantes)
                startActivity(intent)
            }
            else
            {
                pedidos.remove(pedido)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("pedidos", pedidos)
                intent.putExtra("despachantes", despachantes)
                startActivity(intent)

            }
        })

    }


    private fun IniciarElementos(){

        gusto1=findViewById(R.id.d_gusto1)
        gusto2=findViewById(R.id.d_gusto2)
        gusto3=findViewById(R.id.d_gusto3)
        gusto4=findViewById(R.id.d_gusto4)
        adicional=findViewById(R.id.d_adicional)
        descripcion=findViewById(R.id.d_descripcion)
        confirmar=findViewById(R.id.d_confirmar)
        validar=findViewById(R.id.d_validar)

        val intento = intent
        val datos = intent.extras

        if (datos != null) {
            val listaPedidos = intento.getSerializableExtra("pedidos") as ArrayList<Pedido>
            pedidos = listaPedidos
            val listaDespachante = intento.getSerializableExtra("despachantes") as ArrayList<Despachante>
            despachantes=listaDespachante
        }

    }



    private fun CargoGustos(pedido: Pedido){
        var numero: Int = pedido.helado.size

     if(numero>3)
     {
         gusto1.setText(pedido.helado[0].toString())
         gusto2.setText(pedido.helado[1].toString())
         gusto3.setText(pedido.helado[2].toString())
         gusto4.setText(pedido.helado[3].toString())
     }
        else if(numero>2)
     {
         gusto1.setText(pedido.helado[0].toString())
         gusto2.setText(pedido.helado[1].toString())
         gusto3.setText(pedido.helado[2].toString())
     }
     else if (numero>1)
     {

         gusto1.setText(pedido.helado[0].toString())
         gusto2.setText(pedido.helado[1].toString())
     }

    }
}