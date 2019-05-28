package com.hokagelab.www.footballmatchdua.mainMenu.teams.logoTeam.detailOverview

import android.database.sqlite.SQLiteConstraintException
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.adapter.TeamAdapter
import com.hokagelab.www.footballmatchdua.dbSqlite.databaseTeam
import com.hokagelab.www.footballmatchdua.model.FavoriteTeam
import kotlinx.android.synthetic.main.activity_details_teams.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailsTeams : AppCompatActivity() {
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var idTeam: String= ""
    private var nmTeam: String= ""
    private var fotoTeam: String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_teams)

        idTeam = intent.getStringExtra("idTeam")

        viewpager_team.adapter = TeamAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_team)

        checkFavorite()

        //actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "Details Club"
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
                    if (isFavorite) removeFromFavorite()
                    else addToFavorite()
                    setFavoriteIcon(isFavorite)
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
        nmTeam = intent.getStringExtra("nmTeam")
        fotoTeam = intent.getStringExtra("teamBadge")

        try {
            databaseTeam.use {
                insert(
                    FavoriteTeam.TABLE_TEAM,
                    FavoriteTeam.TEAM_ID to idTeam,
                    FavoriteTeam.NAMA_TEAM to nmTeam,
                    FavoriteTeam.FOTO_TEAM to fotoTeam)
            }
            Toast.makeText(this, "Added to favorite ", Toast.LENGTH_SHORT).show()
            isFavorite = true
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }

    }

    private fun removeFromFavorite() {
        try {
            databaseTeam.use {
                delete(
                    FavoriteTeam.TABLE_TEAM
                    , "(TEAM_ID = {idTeam})"
                    , "idTeam" to idTeam)
            }
            Toast.makeText(this, "Remove to favorite ", Toast.LENGTH_SHORT).show()

            isFavorite = false
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkFavorite():Boolean {
//        if (idTeam == "133610"){
//            val data = ""
//            Log.e(data, "Ini terdeteksi"+idTeam)
//        }
        databaseTeam.use {
            val result = select(FavoriteTeam.TABLE_TEAM)
                .whereArgs("(TEAM_ID = {idTeam})", "idTeam" to idTeam)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true

        }
        return true
    }

}
