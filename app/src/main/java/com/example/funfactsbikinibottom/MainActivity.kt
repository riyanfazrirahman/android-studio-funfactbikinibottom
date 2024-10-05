package com.example.funfactsbikinibottom

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvCharacter: RecyclerView
    private val list = ArrayList<Character>()
    private var isGridMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCharacter = findViewById(R.id.rv_character)
        rvCharacter.setHasFixedSize(true)

        if (savedInstanceState != null) {
            isGridMode = savedInstanceState.getBoolean("LAYOUT_MODE", false)
        }

        list.addAll(getListCharacter())
        showRecyclerList(isGridMode)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("LAYOUT_MODE", isGridMode)
    }

    private fun getListCharacter(): ArrayList<Character> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataFunFact = resources.getStringArray(R.array.data_funfact)
        val dataBithday = resources.getStringArray(R.array.data_birthday)
        val dataJob = resources.getStringArray(R.array.data_job)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listCharacter = ArrayList<Character>()
        for (i in dataName.indices) {
            val character = Character(
                dataName[i],
                dataFunFact[i],
                dataBithday[i],
                dataJob[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1)
            )
            listCharacter.add(character)
        }
        return listCharacter
    }

    private fun showRecyclerList(isGrid: Boolean) {
        rvCharacter.layoutManager = if (isGrid) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }
        val listCharacterAdapter = ListCharacterAdapter(list, isGrid)
        rvCharacter.adapter = listCharacterAdapter

        listCharacterAdapter.setOnItemClickCallback(object : ListCharacterAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Character) {
                showSelectedCharacter(data)
            }
        })
    }

    private fun showSelectedCharacter(character: Character) {
        Toast.makeText(this, "Kamu memilih " + character.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                isGridMode = false
                showRecyclerList(isGridMode)
            }
            R.id.action_grid -> {
                isGridMode = true
                showRecyclerList(isGridMode)
            }
            R.id.action_about -> {
                val person = aboutMe()
                val moveAboutActivity = Intent(this@MainActivity, AboutActivity::class.java)
                moveAboutActivity.putExtra(AboutActivity.EXTRA_PERSON, person)
                startActivity(moveAboutActivity)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun aboutMe (): Person {
        return Person(
            "Riyan Fazri Rahman",
            "a668b4ky3894@bangkit.academy",
            "Palangkaraya",
             R.drawable.ic_about
        )
    }
}