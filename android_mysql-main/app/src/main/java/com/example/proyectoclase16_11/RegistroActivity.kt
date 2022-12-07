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
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class RegistroActivity : AppCompatActivity() {

    private lateinit var etNombre:EditText
    private lateinit var etEmail:EditText
    private lateinit var etTelefono:EditText
    private lateinit var etPass:EditText
    private lateinit var etId:EditText

    private lateinit var id:String

    private lateinit var procesoEnCola:RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        etNombre = findViewById(R.id.et_Nombre)
        etEmail = findViewById(R.id.et_Email)
        etTelefono = findViewById(R.id.et_Telefono)
        etPass = findViewById(R.id.et_Password)
        etId = findViewById(R.id.et_id)
        id = intent.getStringExtra("id").toString()
        etId.setText(id)

        cargarDatos()
    }
    fun cargarDatos(){
        //llenarCampos()

        procesoEnCola= Volley.newRequestQueue(this)
        val url = "http://192.168.1.4/android_mysql1/consulta.php?id=$id"
        val consulta = JsonObjectRequest(Request.Method.GET,url,null,
            Response.Listener { Respuesta->

                etNombre.setText(Respuesta.getString("nombre"))
                etEmail.setText(Respuesta.getString("email"))
                etTelefono.setText(Respuesta.getString("telefono"))
                etPass.setText(Respuesta.getString("pass"))
                Toast.makeText(this,"Datos obtenidos",Toast.LENGTH_LONG).show()
            }, Response.ErrorListener { error->
                Toast.makeText(this,"Datos error de conexion: $error",Toast.LENGTH_LONG).show()
            })

        //llenarCampos()

        procesoEnCola.add(consulta)
    }

    fun limpiarCampos(){
        etNombre.setText("")
        etEmail.setText("")
        etTelefono.setText("")
        etPass.setText("")
        etId.setText("")
    }
    fun regresar(Vista: View){
        var ventana: Intent = Intent(this,MainActivity::class.java)
        startActivity(ventana)
    }

    fun eliminar(Vista: View){
        val url = "http://192.168.1.4/android_mysql1/borrar.php"
        procesoEnCola= Volley.newRequestQueue(this)
        var resultadoPost = object :StringRequest(Request.Method.POST,url,
            Response.Listener { Respuesta->
                limpiarCampos()
                Toast.makeText(this,"El usuario a sido borrado",Toast.LENGTH_LONG).show()
            }, Response.ErrorListener {
                Toast.makeText(this,"Error al borrar usuario",Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String,String>()
                parametros.put("id",etId.text.toString())
                return parametros
            }
        }
        procesoEnCola.add(resultadoPost)
    }

    fun editar(Vista: View){
        val url = "http://192.168.1.4/android_mysql1/editar.php"
        procesoEnCola = Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(Request.Method.POST,url,
            Response.Listener { respuesta->
                Toast.makeText(this,"El usuario fue editado",Toast.LENGTH_LONG).show()
            },Response.ErrorListener { error->
                Toast.makeText(this,"Error al edidar usuario",Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String,String>()
                parametros.put("id",etId.text.toString())
                parametros.put("nombre",etNombre.text.toString())
                parametros.put("email",etEmail.text.toString())
                parametros.put("telefono",etTelefono.text.toString())
                parametros.put("pass",etPass.text.toString())
                return parametros

            }
        }
        procesoEnCola.add(resultadoPost)
    }
}
