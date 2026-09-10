package com.proflucas.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class CelsiusActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_celsius)

        Toast.makeText(this, "Trocou para Celsius!", Toast.LENGTH_SHORT).show()

        val txtPeso = findViewById<EditText>(R.id.txtPeso)
        val txtAltura = findViewById<EditText>(R.id.txtAltura)

        val btnCalcularCelsius = findViewById<Button>(R.id.btnCalcularIMC)

        val labelConversor = findViewById<TextView>(R.id.labelConversor)

        btnCalcularCelsius.setOnClickListener {

            val peso = txtPeso.text.toString().toFloatOrNull()
            val altura = txtAltura.text.toString().toFloatOrNull()

            if (peso ==  null || altura == null) {
                Toast.makeText(this, "Os dois campos precisam ser preenchidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val IMC = peso / ((altura / 100) * ( altura / 100));
                IMCIndicator(IMC, labelConversor)

            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Digite um número válido!", Toast.LENGTH_SHORT).show()
                labelConversor.text = "O valor digitado não é valido"
            }
        }

        val btnSwitchToMedia = findViewById<Button>(R.id.btnLayoutMedia)

        btnSwitchToMedia.setOnClickListener {
            switchToLayoutMedia()
        }
    }

    private fun switchToLayoutMedia() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun  IMCIndicator(IMC: Float, label: TextView)
    {
        when {
            IMC <= 18.5 -> label.text = " Seu peso esta abaixo do ideal para sua idade\n" + "IMC: %.2f".format(IMC)
            IMC <= 25 -> label.text ="Seu peso esta ideal, não se preocupe\n" + "IMC: %.2f".format(IMC)
            IMC <= 30 -> label.text ="Seu peso esta acima do ideal\n" + "IMC: %.2f".format(IMC)
            IMC <= 35 -> label.text ="Voce sofre de obesidade grau I, procure um nutricionista\n" + "IMC: %.2f".format(IMC)
            IMC <= 40 -> label.text ="Voce sofre de obesidade grau II, procure um nutricionista o mais rapido possivel\n" + "IMC: %.2f".format(IMC)
            else -> label.text = "Voce sofre de obesidade grau III, procure um nutricionista e um medico o mais rapido possivel.\n" + "IMC: %.2f".format(IMC)
        }
    }

}