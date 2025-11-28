package com.example.projetmobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val btnCalories = findViewById<Button>(R.id.btnCalories)
        val btnExercises = findViewById<Button>(R.id.btnExercises)
        val btnSupplements = findViewById<Button>(R.id.btnSupplements)

        btnCalories.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnExercises.setOnClickListener {
            startActivity(Intent(this, ExercisesActivity::class.java))
        }

        btnSupplements.setOnClickListener {
            startActivity(Intent(this, SupplementsActivity::class.java))
        }
    }
}
