package carla.etchetto.trabajopractico3.Modelos

import java.io.Serializable

data class Pedido(val item: String, val cajero: String, val helado: ArrayList<String>, val adicional: String,val despachante: String, val precio: Int, val foto: Int):Serializable
