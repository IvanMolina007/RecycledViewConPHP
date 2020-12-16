package com.example.reclycledviewkotlinlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.reclycledviewkotlinlin.modelos.Perros
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rows.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        conectarJson()

    }

    fun conectarJson() {  // conecta con una url y devuelve su contenido

        val listaPerros = ArrayList<Perros>()

        val url = "http://iesayala.ddns.net/IvanMolina007/jsonperros.php"
        val queue = Volley.newRequestQueue(this)
        val stringRequest =
            StringRequest(Request.Method.GET, url, { response ->
                //conectó correctamente


                val jsonArray = JSONArray(response)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = JSONObject(jsonArray.getString(i))
                    val p = Perros(
                        jsonObject.get("Raza").toString(),
                        jsonObject.get("EdadMax").toString().toInt(),
                        jsonObject.get("Tamaño").toString().toInt(),
                        jsonObject.get("Lugar").toString(),
                        jsonObject.get("Tipo").toString(),
                        jsonObject.get("Imagen").toString()
                    )

                    listaPerros.add(p)

                }

                recyclerView.layoutManager =  LinearLayoutManager(this)
                recyclerView.layoutManager=LinearLayoutManager(this)
                recyclerView.addItemDecoration((DividerItemDecoration(this, DividerItemDecoration.VERTICAL)))
                val a = RecyclerAdapter(this, listaPerros)
                recyclerView.adapter = a

            }, {
                println(it.message)
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT)
                    .show()   // se produjo un error
            })
        queue.add(stringRequest)

    }

}