package com.hokagelab.www.footballmatchdua.mainMenu.teams.logoTeam.detailPlayers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.hokagelab.www.footballmatchdua.R
import kotlinx.android.synthetic.main.activity_details_player.*

class DetailsPlayer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_player)

        val nama = intent.getStringExtra("namaPlayer")
        val berat = intent.getStringExtra("beratPlayer")
        val foto = intent.getStringExtra("fotoPlayer")
        val posisi = intent.getStringExtra("posisiPlayer")
        val tinggi = intent.getStringExtra("tinggiPlayer")
        val overview = intent.getStringExtra("overviewPlayer")

        beratPlayers.text = berat
        tinggiPlayers.text = tinggi
        posisiPlayers.text = posisi
        overviewPlayers.text= overview
        Glide.with(this).load(foto).into(fotoPlayer)

        //actionbar
        val actionbar = supportActionBar
        actionbar!!.title = nama
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
