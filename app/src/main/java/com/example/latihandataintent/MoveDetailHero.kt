package com.example.latihandataintent

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MoveDetailHero : AppCompatActivity() {
    companion object {
        const val EXTRA_HERO = "extra_hero"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_detail_hero)

        val imgHeroDetail: ImageView = findViewById(R.id.img_hero_detail)
        val tvHeroNameDetail: TextView = findViewById(R.id.tv_hero_name_detail)
        val tvHeroDescriptionDetail: TextView = findViewById(R.id.tv_hero_description_detail)

        val hero = intent.getParcelableExtra<Hero>(EXTRA_HERO)

        if (hero != null) {
            imgHeroDetail.setImageResource(hero.photo)
            tvHeroNameDetail.text = hero.name
            tvHeroDescriptionDetail.text = hero.description
        }

    }
}