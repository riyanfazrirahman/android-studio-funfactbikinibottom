package com.example.funfactsbikinibottom

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AboutActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val ivPhoto:ImageView = findViewById(R.id.iv_detail_photo)
        val tvName:TextView = findViewById(R.id.tv_about_name)
        val tvEmail:TextView = findViewById(R.id.tv_about_email)
        val tvCity:TextView = findViewById(R.id.tv_about_city)

        val person = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Person>(EXTRA_PERSON, Person::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Person>(EXTRA_PERSON)
        }

        if (person != null) {
            ivPhoto.setImageResource(person.photo)
            tvName.text = person.name
            tvEmail.text = person.email
            tvCity.text = person.city
        }
    }
}