package com.hokagelab.www.footballmatchdua.mainMenu.match.detailMatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.R.menu.nav_item
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.dbSqlite.database
import com.hokagelab.www.footballmatchdua.model.Event
import com.hokagelab.www.footballmatchdua.model.Favorite
import com.hokagelab.www.footballmatchdua.model.LogoTeam
import com.hokagelab.www.footballmatchdua.presenter.PresenterDetail
import com.hokagelab.www.footballmatchdua.util.dateNewFormat
import com.hokagelab.www.footballmatchdua.view.MainDetailMatch
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailFavoritMatch : AppCompatActivity(), MainDetailMatch {
    override fun logoHome(data: List<LogoTeam>) {
        Glide.with(this)
            .load(data[0].teambadge)
            .into(logosatu)    }

    override fun logoAway(data: List<LogoTeam>) {
        Glide.with(this)
            .load(data[0].teambadge)
            .into(logodua)
    }

    override fun handleEmpty() {
        details.clear()
    }

    override fun eventDetail(data: List<Event>) {
        details.clear()
        details.addAll(data)

        var satuScore = data[0].scoreSatu.toString()
        var duaScore = data[0].scoreDua.toString()

        dtanggal.text = dateNewFormat(data[0].dateSchedule, false)
        dtime.text = dateNewFormat(data[0].strTime, true)

        if (data[0].scoreSatu.toString() == "null") satuScore = ""
        if (data[0].scoreDua.toString() == "null") duaScore = ""
        dscoresatu.text = satuScore
        dscoredua.text = duaScore

        val hClub = data[0].clubSatu
        val hGoalDetail = data[0].hGoalDetails
        val hRedCard = data[0].hRedCard
        val hYellowCard = data[0].hYellowCard
        val hGoalkeeper = data[0].hGoalKeeper
        val hDefense = data[0].hDefense
        val hMidfield = data[0].hMidfield
        val hForward = data[0].hForward
        val hSubstitutes = data[0].hSubtitutes

        val aClub = data[0].clubDua
        val aGoalDetail = data[0].aGoalDetails
        val aRedCard = data[0].aRedCard
        val aYellowCard = data[0].aYellowCard
        val aGoalkeeper = data[0].aGoalKeeper
        val aDefense = data[0].aDefense
        val aMidfield = data[0].aMidfield
        val aForward = data[0].aForward
        val aSubstitutes = data[0].aSubtitutes

        detailsatu.text = hClub
        dgoalsatu.text = hGoalDetail
        dkeepersatu.text = hGoalkeeper
        ddefensesatu.text = hDefense
        dmidsatu.text = hMidfield
        dforwardsatu.text = hForward
        dsubsatu.text = hSubstitutes
        dredsatu.text = hRedCard
        dyellowsatu.text = hYellowCard

        detaildua.text = aClub
        dgoaldua.text = aGoalDetail
        dkeeperdua.text = aGoalkeeper
        ddefensedua.text = aDefense
        dmiddua.text = aMidfield
        dforwarddua.text = aForward
        dsubdua.text = aSubstitutes
        dreddua.text = aRedCard
        dyellowdua.text = aYellowCard

        isGetDataFinish = true

    }

    private lateinit var presenter: PresenterDetail
    private var details: MutableList<Event> = mutableListOf()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var idEvent: String= ""
    private var isGetDataFinish = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        idEvent = intent.getStringExtra("idEvent")

        val TAG = "DATA"
        Log.e(TAG, "DATA EVENT = "+idEvent)

        val idtimsatu = intent.getStringExtra("idtimsatu")
        val idtimdua = intent.getStringExtra("idtimdua")

        checkFavorite()
        isGetDataFinish = false
        presenter = PresenterDetail(this, ApiRepository(), Gson())
        presenter.run {
            getEvent(idEvent)
            getLogo(idtimsatu, "home")
            getLogo(idtimdua, "away")
        }

        //actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "Details Match"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(nav_item, menu)
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
            database.use {
                insert(
                    Favorite.TABLE_FAVORITES,
                    Favorite.EVENT_ID to idEvent.toInt(),
                    Favorite.ID_SATU to details[0].idClubSatu.toString(),
                    Favorite.ID_DUA to details[0].idClubDua.toString(),
                    Favorite.DATE_SCHEDULE to details[0].dateSchedule.toString(),
                    Favorite.NAMA_SATU to details[0].clubSatu.toString(),
                    Favorite.NAMA_DUA to details[0].clubDua.toString(),
                    Favorite.SCORE_SATU to details[0].scoreSatu.toString(),
                    Favorite.SCORE_DUA to details[0].scoreDua.toString())
            }
            Snackbar.make(root_layout,"Added to favorite ",Snackbar.LENGTH_LONG).show()
            isFavorite = true
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(root_layout,e.localizedMessage,Snackbar.LENGTH_LONG).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITES
                    , "(EVENT_ID = {eventId})"
                    , "eventId" to idEvent)
            }
            Snackbar.make(root_layout,"Remove to favorite ",Snackbar.LENGTH_LONG).show()
            isFavorite = false
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(root_layout,e.localizedMessage,Snackbar.LENGTH_LONG).show()
        }
    }

    private fun checkFavorite() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITES)
                .whereArgs("(EVENT_ID = {eventId})", "eventId" to idEvent)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}
