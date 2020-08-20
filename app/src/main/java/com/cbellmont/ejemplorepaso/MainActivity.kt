package com.cbellmont.ejemplorepaso

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var model : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        setupObserver()
        setupAlertDialog()
    }

    fun setupObserver() {
        val preguntasObserver = Observer<List<Pregunta>> { listaPreguntas ->
            setupTvPreguntas(listaPreguntas)
        }
        CoroutineScope(Dispatchers.IO).launch{
            val lista = model.loadPreguntas(this@MainActivity)
            withContext(Dispatchers.Main){
                lista.observe(this@MainActivity, preguntasObserver)
            }
        }
    }

    fun setupTvPreguntas(listaPreguntas: List<Pregunta>) {
        tvPreguntas.text = ""
        listaPreguntas.forEach { tvPreguntas.append("${it.pregunta}\n") }
    }

    fun setupAlertDialog() {
        fbAdd.setOnClickListener {
            val inflater = LayoutInflater.from(applicationContext)
            val viewInterna = inflater.inflate(R.layout.dialog_main, null)
            val edPregunta = viewInterna.findViewById<EditText>(R.id.etPregunta)

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Añade tu pregunta")
            builder.setMessage("Al pulsar guardar, tu pregunta será añadida")
            builder.setView(viewInterna)

            builder.setPositiveButton("Guardar") { dialog, which ->
                if (edPregunta.text.isNotEmpty()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        model.addPregunta(this@MainActivity, edPregunta.text.toString())
                        mostrarToast("Tu pregunta se ha guardado correctamente")
                    }
                } else {
                    // TODO no cerrar el dialogo.
                    Toast.makeText(
                        applicationContext,
                        "Tu pregunta está vacia. Escribe algo.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            builder.setNegativeButton("Cancelar") { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    "Tu pregunta no ha sido guardada",
                    Toast.LENGTH_SHORT
                ).show()
            }
            builder.show()
        }
    }

    suspend fun mostrarToast(text : String) {
        withContext(Dispatchers.Main) {
            Toast.makeText(
                applicationContext,
                text,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}