package com.example.listkampus

import ListUniversityAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvUniversities: RecyclerView
    private val list = ArrayList<University>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUniversities = findViewById(R.id.rv_heroes)
        rvUniversities.setHasFixedSize(true)

        list.addAll(getListUniversities())
        showRecyclerList()
    }

    private fun getListUniversities(): ArrayList<University> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataWeb = resources.getStringArray(R.array.data_web)
        val dataRank = resources.getStringArray(R.array.data_rank)

        val listHero = ArrayList<University>()
        for (i in dataName.indices) {
            val university = University(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataWeb[i], dataRank[i])
            listHero.add(university)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvUniversities.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListUniversityAdapter(list)
        rvUniversities.adapter = listHeroAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val aboutIntent = Intent(this@MainActivity, MenuActivity::class.java)
                startActivity(aboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}