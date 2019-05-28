package com.hokagelab.www.footballmatchdua.mainMenu.match

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.adapter.EventAdapter
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.mainMenu.match.detailMatch.DetailFavoritMatch
import com.hokagelab.www.footballmatchdua.model.*
import com.hokagelab.www.footballmatchdua.view.MainSearchMatch
import org.jetbrains.anko.startActivity

class SearchMatch : AppCompatActivity(), MainSearchMatch {

    override fun eventDetail(data: List<Event>) {
        datas.clear()
        datas.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun handleEmpty() {
        datas.clear()
        adapter.notifyDataSetChanged()
    }


    private var datas: MutableList<Event> = mutableListOf()

    private lateinit var presenter: PresenterSearchMatch
    private lateinit var adapter: EventAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val recycleView = this.findViewById<RecyclerView>(R.id.searchRecycler)
        recycleView?.layoutManager = LinearLayoutManager(this)

        adapter = EventAdapter(datas) {
            startActivity<DetailFavoritMatch>(
                "idEvent" to it.eventId,
                "idtimsatu" to it.idClubSatu,
                "idtimdua" to it.idClubDua
            )
        }

        recycleView.adapter = adapter

        presenter = PresenterSearchMatch(this, ApiRepository(), Gson())
        presenter.getSearch("")

        //actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "Search Match"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_search, menu)
        val menuSearch = menu?.findItem(R.id.searchId)
        val searchView = menuSearch?.actionView as android.widget.SearchView
        searchQuery(searchView)
        return super.onCreateOptionsMenu(menu)
    }

    private fun searchQuery(searchView: android.widget.SearchView) {
        presenter = PresenterSearchMatch(this, ApiRepository(), Gson())
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    return false
                }else{
                    presenter.getSearch(query.toString())
                    return true
                }
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    return false
                }else{
                    presenter.getSearch(query.toString())
                    return true
                }
            }
        })
    }
}
