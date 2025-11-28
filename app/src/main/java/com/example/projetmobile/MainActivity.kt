package com.example.projetmobile

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val radioGroupSexe = findViewById<RadioGroup>(R.id.radioGroupSexe)
        val editTaille = findViewById<EditText>(R.id.editTaille)
        val editPoids = findViewById<EditText>(R.id.editPoids)
        val editAge = findViewById<EditText>(R.id.editAge)
        val spinnerActivity = findViewById<Spinner>(R.id.spinnerActivity)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val resultText = findViewById<TextView>(R.id.resultText)

        val activitiesList = listOf(
            "1-3 jours / semaine (Léger)",
            "4-5 jours / semaine (Modéré)",
            "6-7 jours / semaine (Intensif)"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            activitiesList
        )

        spinnerActivity.adapter = adapter

        btnCalculate.setOnClickListener {

            if (editTaille.text.isEmpty() || editPoids.text.isEmpty() || editAge.text.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val taille = editTaille.text.toString().toDouble()
            val poids = editPoids.text.toString().toDouble()
            val age = editAge.text.toString().toDouble()

            val selectedSexId = radioGroupSexe.checkedRadioButtonId
            if (selectedSexId == -1) {
                Toast.makeText(this, "Veuillez choisir le sexe", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sexe = findViewById<RadioButton>(selectedSexId).text.toString()

            val activityFactor = when (spinnerActivity.selectedItem.toString()) {
                activitiesList[0] -> 1.375
                activitiesList[1] -> 1.55
                else -> 1.725
            }

            val bmr = if (sexe == "Homme") {
                88.36 + (13.4 * poids) + (4.8 * taille) - (5.7 * age)
            } else {
                447.6 + (9.2 * poids) + (3.1 * taille) - (4.3 * age)
            }

            val tdee = bmr * activityFactor

            resultText.text = "Besoin calorique : %.0f kcal/jour".format(tdee)
        }

        btnClear.setOnClickListener {
            editTaille.text.clear()
            editPoids.text.clear()
            editAge.text.clear()
            radioGroupSexe.clearCheck()
            resultText.text = "Résultat : — kcal"
        }
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish() // ferme cette activité et revient au menu principal
        }
    }
}
