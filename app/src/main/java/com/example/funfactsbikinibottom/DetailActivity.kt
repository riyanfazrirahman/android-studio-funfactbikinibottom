package com.example.funfactsbikinibottom

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_CHARACTER = "extra_character"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val ivDetailPhoto : ImageView = findViewById(R.id.iv_detail_photo)
        val tvDetailName : TextView = findViewById(R.id.tv_detail_name)
        val tvDetailFunFact : TextView = findViewById(R.id.tv_detail_fun_fact)
        val tvDetailBirthday : TextView = findViewById(R.id.tv_detail_birthday)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val tvDetailJob: TextView = findViewById(R.id.tv_detail_job)

        val dataCharacter = getCharacterData()

        if (dataCharacter != null) {
            ivDetailPhoto.setImageResource(dataCharacter.photo)
            tvDetailName.text = dataCharacter.name
            tvDetailFunFact.text = dataCharacter.funfact
            tvDetailBirthday.text = dataCharacter.birthday
            tvDetailDescription.text = dataCharacter.description
            tvDetailJob.text = dataCharacter.job
        }

        val btnAbout: Button = findViewById(R.id.btn_about)
        btnAbout.setOnClickListener(this)

        val moreImage: TextView = findViewById(R.id.tv_detail_more_image)
        moreImage.setOnClickListener {
            Toast.makeText(this, "This feature is not available yet.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCharacterData(): Character? {
        return if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Character>(EXTRA_CHARACTER, Character::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Character>(EXTRA_CHARACTER)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_about -> {
                val main = MainActivity()
                val person = main.aboutMe()
                val moveAboutActivity = Intent(this@DetailActivity, AboutActivity::class.java)
                moveAboutActivity.putExtra(AboutActivity.EXTRA_PERSON, person)
                startActivity(moveAboutActivity)
            }
        }
    }


}