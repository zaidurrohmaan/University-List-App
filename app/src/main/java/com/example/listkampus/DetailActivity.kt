package com.example.listkampus

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_UNIVERSITY = "extra_university"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataHero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<University>(EXTRA_UNIVERSITY, University::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<University>(EXTRA_UNIVERSITY)
        }

        val btnUniversityWeb: Button = findViewById(R.id.btn_web)
        btnUniversityWeb.setOnClickListener {
            val moveIntent = Intent(Intent.ACTION_VIEW, Uri.parse(dataHero?.web))
            startActivity(moveIntent)
        }

        val btnUniversityMap: Button = findViewById(R.id.btn_share)
        btnUniversityMap.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.setType("text/plain")
            share.putExtra(Intent.EXTRA_TEXT,  dataHero?.name + "\n\n" + dataHero?.description + "\n\n(Sumber: Wikipedia).")
            startActivity(Intent.createChooser(share, "Share Link"))
        }

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.iv_detail_photo)
        val tvDetailWeb: TextView = findViewById(R.id.tv_detail_web)
        val tvDetailRank: TextView = findViewById(R.id.tv_detail_rank)

        tvDetailName.text = dataHero?.name
        tvDetailDescription.text = dataHero?.description
        dataHero?.photo?.let { ivDetailPhoto.setImageResource(it) }
        tvDetailWeb.text = dataHero?.web
        tvDetailRank.text = dataHero?.rank
    }
}

