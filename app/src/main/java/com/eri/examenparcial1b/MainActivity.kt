package com.eri.examenparcial1b

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var xListviewGenero : ListView
    lateinit var genero : Array<String>
    lateinit var xeditTextPadre : EditText
    lateinit var xeditTextMadre : EditText
    lateinit var xtextViewResultado : TextView
    lateinit var xbuttonCalcular : Button
     var generoSeleccionado : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        xListviewGenero = findViewById(R.id.ListViewGenero)
        xeditTextPadre = findViewById(R.id.editTextPadre)
        xeditTextMadre = findViewById(R.id.editTextMadre)
        xtextViewResultado = findViewById(R.id.textViewResultado)
        xbuttonCalcular = findViewById(R.id.buttonCalcular)

        genero = arrayOf("Niño", "Niña")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, genero)
        xListviewGenero.adapter = adaptador
        xListviewGenero.setOnItemClickListener { parent, view, position, id ->
            generoSeleccionado = genero[position]
        }


        xbuttonCalcular.setOnClickListener {
            CalcularTanner()
        }
    }


    fun CalcularTanner() {

        if (xeditTextPadre.text.toString().isNotEmpty() && xeditTextMadre.text.toString().isNotEmpty() && generoSeleccionado != null) {
            val padre = xeditTextPadre.text.toString().toDouble()
            val madre = xeditTextMadre.text.toString().toDouble()
            val promedio = (padre + madre)
            var resultado = 0.0


            if (generoSeleccionado == "Niño") {
                resultado = (promedio + 13) / 2
            } else {
                resultado = (promedio - 13) / 2
            }


            xtextViewResultado.text = "El resultado es: %.2f".format(resultado)

        } else {

            xtextViewResultado.text = "Por favor ingresa todos los datos y selecciona un género"
        }
    }
}