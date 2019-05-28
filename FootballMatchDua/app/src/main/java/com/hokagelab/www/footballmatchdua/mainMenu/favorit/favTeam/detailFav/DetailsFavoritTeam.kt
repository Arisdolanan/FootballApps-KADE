package com.hokagelab.www.footballmatchdua.mainMenu.favorit.favTeam.detailFav

import android.database.sqlite.SQLiteConstraintException
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.adapter.TeamAdapter
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.dbSqlite.databaseTeam
import com.hokagelab.www.footballmatchdua.model.FavoriteTeam
import com.hokagelab.www.footballmatchdua.model.TeamLogo
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_details_favorit_team.*
import kotlinx.android.synthetic.main.fragment_fragment_overview.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailsFavoritTeam : AppCompatActivity(), MainDetailFavTeam {

    override fun showLogoTeams(data: List<TeamLogo>) {
        details.clear()
        details.addAll(data)

        val namaClub = data[0].strTeam
        val yearClub = data[0].year
        val stadiumClub = data[0].stadium
        val overviewClub = data[0].overviewTeam

        tv_namaTeam.text = namaClub
        tv_tahunTeam.text = yearClub
        tv_stadionTeam.text = stadiumClub
        tv_overviewTeam.text = overviewClub

        Glide.with(this)
            .load(data[0].teambadge)
            .into(img_badge)

        isGetDataFinish = true
    }

    private var details: MutableList<TeamLogo> = mutableListOf()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var idTeam: String= ""
    private var isGetDataFinish = false

    private lateinit var presenter : PresenterDetailFavTeam


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_favorit_team)

        viewpager_teamD.adapter = TeamAdapter(supportFragmentManager)
        tabs_mainD.setupWithViewPager(viewpager_teamD)


        idTeam = intent.getStringExtra("idTeam")

        val TAG = "DATA"
        Log.e(TAG, "DATA EVENT = "+idTeam)

        checkFavorite()
        isGetDataFinish = true

        presenter = PresenterDetailFavTeam(this, ApiRepository(), Gson())
        presenter.run {
            getTeam(idTeam)
        }

        //actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "Details Favorite Teams"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_item, menu)
        menuItem = menu
        setFavoriteIcon(isFavorite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.fav -> {
                if (isGetDataFinish) {
                    if (isFavorite) removeFromFavorite()
                    else addToFavorite()
                    setFavoriteIcon(isFavorite)
                }else{
                    Snackbar.make(root_layout,"Harap Tunggu ..",Snackbar.LENGTH_LONG).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun setFavoriteIcon(favorite: Boolean) {
        if (favorite) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                menuItem?.findItem(R.id.fav)?.icon = getDrawable(R.drawable.favorit_pul)
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                menuItem?.findItem(R.id.fav)?.icon = getDrawable(R.drawable.favorit_line)
            }
        }
    }

    private fun addToFavorite() {
        try {
            databaseTeam.use {
                insert(
                    FavoriteTeam.TABLE_TEAM,
                    FavoriteTeam.ID to idTeam.toInt(),
                    FavoriteTeam.NAMA_TEAM to details[0].strTeam.toString(),
                    FavoriteTeam.FOTO_TEAM to details[0].teambadge.toString())
            }
            Snackbar.make(root_layout,"Added to favorite ", Snackbar.LENGTH_LONG).show()
            isFavorite = true
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(root_layout,e.localizedMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            databaseTeam.use {
                delete(
                    FavoriteTeam.TABLE_TEAM
                    , "(TEAM_ID = {teamId})"
                    , "teamId" to idTeam)
            }
            Toast.makeText(this, "Remove to favorite ", Toast.LENGTH_SHORT).show()
//            Snackbar.make(root_layout,"Remove to favorite ", Snackbar.LENGTH_LONG).show()
            isFavorite = false
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(root_layout,e.localizedMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun checkFavorite() {
        databaseTeam.use {
            val result = select(FavoriteTeam.TABLE_TEAM)
                .whereArgs("(TEAM_ID = {idTeam})", "idTeam" to idTeam)
            val favorite = result.parseList(classParser<FavoriteTeam>())
//            val TAG = "DATA"
//            Log.e(TAG,"Data Favorit = "+result)
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}
