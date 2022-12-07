package com.example.proyectoclase16_11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private lateinit var et_Nombre:EditText
    private lateinit var et_Email:EditText
    private lateinit var et_Telefono:EditText
    private lateinit var et_Pass:EditText
    private  lateinit var etId: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_Nombre = findViewById(R.id.et_Nombre)
        et_Email = findViewById(R.id.et_Email)
        et_Telefono = findViewById(R.id.et_Telefono)
        et_Pass = findViewById(R.id.et_Password)
        etId = findViewById(R.id.et_Id)

    }

    fun guardar(vista: View) {

        val url ="http://192.168.1.4/android_mysql1/insertar.php"
        val procesoEnCola: RequestQueue = Volley.newRequestQueue(this)
        var resultadoPost = object :StringRequest(Request.Method.POST,url,
            Response.Listener<String>  {respuesta->
                Toast.makeText(this,"Usuario ingresado",Toast.LENGTH_LONG).show()

                et_Nombre.setText("")
                et_Email.setText("")
                et_Telefono.setText("")
                et_Pass.setText("")

            },Response.ErrorListener { error->
                Toast.makeText(this,"Error $error",Toast.LENGTH_LONG).show()
            }
            ){

            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap <String,String>()

                parametros.put("nombre",et_Nombre.text.toString())
                parametros.put("email",et_Email.text.toString())
                parametros.put("telefono",et_Telefono.text.toString())
                parametros.put("pass",et_Pass.text.toString())

                return parametros
            }
        }
        procesoEnCola.add(resultadoPost)
    }


    fun enviarId(vista: View) {

        var ventana: Intent = Intent(this,RegistroActivity::class.java)
        ventana.putExtra("id",etId.text.toString())
        startActivity(ventana)



    }
}