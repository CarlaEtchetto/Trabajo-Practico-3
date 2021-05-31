package carla.etchetto.trabajopractico3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.util.Log
import carla.etchetto.trabajopractico3.Modelos.Despachante
import carla.etchetto.trabajopractico3.Modelos.Pedido

class seleccionProducto : AppCompatActivity() {
    var pedidos:ArrayList<Pedido> = ArrayList<Pedido>()
    val gustosHelado:ArrayList<String> = ArrayList<String>()
    var despachantes: ArrayList<Despachante> = ArrayList<Despachante>()
    lateinit var tipoHeladoRG: RadioGroup
    lateinit var tipoHeladoRB: RadioButton
    lateinit var gustos: EditText
    lateinit var agregar: Button
    lateinit var hacerPedido: Button
    lateinit var seleccion: Button
    lateinit var ver_producto: LinearLayout
    lateinit var pedido: Pedido
    var precio: Int = 0
    var foto:Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_producto)
        IniciarElementos()

        val cajerosNombres = arrayOf("Seleccione caja","Primera Caja", "Segunda Caja", "Tercera Caja")
        val adicionalesCuarto = arrayOf("Seleccione Adicional","Crema de vainilla","Chocolate fundido")
        val adicionalesKilo = arrayOf("Seleccione Adicional","Crema de vainilla","Chocolate fundido","Almendras")
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cajerosNombres)
        var adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item,adicionalesKilo)

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        val spin: Spinner = findViewById(R.id.s_cajeros)
        val spin2: Spinner = findViewById(R.id.s_adicional)

        spin.adapter = adapter
        spin2.adapter=adapter2

        var cajero: String = ""
        var adicional: String=""
        hacerPedido.isEnabled= false
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                cajero = parent.getItemAtPosition(position).toString()
            }
        }
        spin2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                adicional = parent.getItemAtPosition(position).toString()
            }
        }


       seleccion.setOnClickListener(View.OnClickListener {
           tipoHeladoRG = findViewById(R.id.r_productos)
           tipoHeladoRB= findViewById(tipoHeladoRG.checkedRadioButtonId)
           var tipoHelado: String = tipoHeladoRB.text.toString()
           MuestroFoto()
         if (tipoHelado.equals("CONO"))
         {
             spin2.isEnabled=false
             hacerPedido.isEnabled=true
             foto=R.mipmap.cono

         }
         else if (tipoHelado.equals("1/4 KILO"))
         {
             spin2.isEnabled=true
             adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item,adicionalesCuarto)
             adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
             spin2.adapter=adapter2
             foto=R.mipmap.cuarto
             hacerPedido.isEnabled=true
         }
           else
         {
             spin2.isEnabled=true
             adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item,adicionalesKilo)
             adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
             spin2.adapter=adapter2
             foto=R.mipmap.kilo
             hacerPedido.isEnabled=true

         }

        })

        agregar.setOnClickListener(View.OnClickListener {
            var tipoHelado: String = tipoHeladoRB.text.toString()

            agregarGustos(tipoHelado,gustosHelado)

        })

        hacerPedido.setOnClickListener(View.OnClickListener {
            var caja1 = pedidos.count { it.cajero == "Primera Caja" }
            var caja2 = pedidos.count { it.cajero == "Segunda Caja" }
            var caja3 = pedidos.count { it.cajero == "Tercera Caja" }
            var cajeroOk: Boolean
            cajeroOk=ValidarCajero(caja1,caja2,caja3,cajero)

            adicional = ChequeoAdic(adicional)
            pedido=Pedido (tipoHeladoRB.text.toString(),cajero.toString(),gustosHelado,adicional,"",precio,foto)


            if (cajeroOk == true) {
                pedidos.add(pedido)
                val intent = Intent(this, despachante::class.java)
                intent.putExtra("pedidos", pedidos)
                intent.putExtra("despachantes", despachantes)
                startActivity(intent)
            }
        })
    }

    private fun ChequeoAdic(adic: String): String{
        if (adic == "Seleccione Adicional")
        {return  "Sin Adicional"}
        return adic
    }

    private fun MuestroFoto(){
        val view = LayoutInflater.from(this).inflate(R.layout.productos,null)
        val foto: ImageView = view.findViewById(R.id.e_imagen)
        when(tipoHeladoRB.text.toString()){
            "CONO"->{
                foto.setImageResource(R.mipmap.cono)
                precio=150

            }
            "1/4 KILO"->{
                foto.setImageResource(R.mipmap.cuarto)
                precio=300
            }
            "KILO"->{
                foto.setImageResource(R.mipmap.kilo)
               precio=600
            }
        }
        ver_producto.removeAllViews()
        ver_producto.addView(view)

    }

    private fun ValidarCajero(caja1: Int,caja2: Int,caja3: Int, cajero: String ):Boolean{

        if(caja1 > 4 && cajero.equals("Primera Caja"))
        {
            Toast.makeText(this,"CAJA NO DISPONIBLE", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(caja2 > 9 && cajero.equals("Segunda Caja"))
        {
            Toast.makeText(this,"CAJA NO DISPONIBLE", Toast.LENGTH_SHORT).show()
            return false
        }
        else if (caja3 > 14 && cajero.equals("Tercera Caja"))
        {
            Toast.makeText(this,"CAJA NO DISPONIBLE", Toast.LENGTH_SHORT).show()
            return false
        }
        if(cajero.equals("Seleccione caja"))
        {
            Toast.makeText(this,"Seleccione un cajero", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun agregarGustos(tipo: String, helado:ArrayList<String>){
        if(tipo.equals("CONO"))
        {
            if(helado.size<=1)
            {
                gustosHelado.add(gustos.text.toString())
                Toast.makeText(this,"GUSTO AGREGADO", Toast.LENGTH_SHORT).show()
                gustos.setText("")


            }
            else
            {
                Toast.makeText(this,"SABORES COMPLETOS", Toast.LENGTH_SHORT).show()
                agregar.isEnabled=false

            }
        }
       else if (tipo.equals("1/4 KILO"))
        {
            if(helado.size<=2)
            {
                gustosHelado.add(gustos.text.toString())
                Toast.makeText(this,"GUSTO AGREGADO", Toast.LENGTH_SHORT).show()
                gustos.setText("")

            }
            else
            {
                Toast.makeText(this,"SABORES COMPLETOS", Toast.LENGTH_SHORT).show()
                agregar.isEnabled=false

            }
        }

        else if (tipo.equals("KILO"))
        {
            if(helado.size<=3)
            {
                gustosHelado.add(gustos.text.toString())
                Toast.makeText(this,"GUSTO AGREGADO", Toast.LENGTH_SHORT).show()
                gustos.setText("")

            }
            else
            {
                Toast.makeText(this,"SABORES COMPLETOS", Toast.LENGTH_SHORT).show()
                agregar.isEnabled=false

            }
        }

    }


    private fun IniciarElementos(){
        gustos = findViewById(R.id.h_ingresoGustos)
        agregar = findViewById(R.id.b_gustos)
        hacerPedido = findViewById(R.id.b_hacerPedido)
        ver_producto = findViewById(R.id.ver_producto)
        seleccion = findViewById(R.id.b_seleccion)


        val intento = intent
        val datos = intent.extras

        if (datos != null) {
            val listaPedidos = intento.getSerializableExtra("pedidos") as ArrayList<Pedido>
            pedidos = listaPedidos
            val listaDespachantes = intento.getSerializableExtra("despachantes") as ArrayList<Despachante>
            despachantes=listaDespachantes
        }

    }

}