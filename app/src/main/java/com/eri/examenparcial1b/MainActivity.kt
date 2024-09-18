package com.eri.examenparcial1b

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
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
    lateinit var ximageViewNino : ImageView
    lateinit var ximageViewNina : ImageView

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
        ximageViewNino = findViewById(R.id.imageViewNino)
        ximageViewNina = findViewById(R.id.imageViewnina)

        genero = arrayOf("Niño", "Niña")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, genero)
        xListviewGenero.adapter = adaptador
        xListviewGenero.setOnItemClickListener { parent, view, position, id ->
            generoSeleccionado = genero[position]
        }

        ximageViewNino.visibility = View.INVISIBLE
        ximageViewNina.visibility = View.INVISIBLE

        xbuttonCalcular.setOnClickListener {
            CalcularTanner()
        }


    }


    fun CalcularTanner() {

        val padreTexto = xeditTextPadre.text.toString()
        val madreTexto = xeditTextMadre.text.toString()
        val sel : String

        // Verifica que los campos no estén vacíos y que el género esté seleccionado
        if (padreTexto.isNotEmpty() && madreTexto.isNotEmpty() && generoSeleccionado != null) {
            try {
                val padre = padreTexto.toDouble()
                val madre = madreTexto.toDouble()
                val promedio = (padre + madre)
                var resultado = 0.0

                if (generoSeleccionado == "Niño") {
                    resultado = (promedio + 13) / 2
                    ximageViewNino.visibility = View.VISIBLE
                    ximageViewNina.visibility = View.INVISIBLE
                    sel= "del"

                } else {
                    resultado = (promedio - 13) / 2
                    ximageViewNina.visibility = View.VISIBLE
                    ximageViewNino.visibility = View.INVISIBLE
                    sel= "dela"
                }

                xtextViewResultado.text = "El resultado "+ sel + " "+ generoSeleccionado + " es: %.2f".format(resultado) + " CM"

            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Por favor ingrese valores válidos para las alturas", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Verifica si falta algún campo
            if (padreTexto.isEmpty() || madreTexto.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese las alturas del padre y la madre", Toast.LENGTH_SHORT).show()
            } else if (generoSeleccionado == null) {
                Toast.makeText(this, "Por favor seleccione un género", Toast.LENGTH_SHORT).show()
            }
        }
    }
}