package com.hokagelab.www.footballmatchdua.mainMenu.teams

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.adapter.LogoTeamAdapter
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.mainMenu.teams.logoTeam.detailOverview.DetailsTeams
import com.hokagelab.www.footballmatchdua.model.TeamLogo
import org.jetbrains.anko.startActivity

class SearchTeam : AppCompatActivity(), MainSearchTeam {

    override fun showLogoTeams(data: List<TeamLogo>) {
        datas.clear()
        datas.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun handleEmpty() {
        datas.clear()
        adapter.notifyDataSetChanged()
    }

    private var datas: MutableList<TeamLogo> = mutableListOf()
    private lateinit var presenter: PresenterSearchTeam
    private lateinit var adapter: LogoTeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        val recycleView = this.findViewById<RecyclerView>(R.id.timSearchRecycler)
        recycleView?.layoutManager = GridLayoutManager(this, 3)

        adapter = LogoTeamAdapter(datas) {
            startActivity<DetailsTeams>(
                "idTeam" to it.idTeam,
                "nmTeam" to it.strTeam,
                "overviewTeam" to it.overviewTeam,
                "stadiumTeam" to it.stadium,
                "teamBadge" to it.teambadge,
                "yearTeam" to it.year
            )
        }

        recycleView.adapter = adapter

        presenter = PresenterSearchTeam(this, ApiRepository(), Gson())
        presenter.getSearchTeam("")

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
        presenter = PresenterSearchTeam(this, ApiRepository(), Gson())
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    return false
                }else{
                    presenter.getSearchTeam(query.toString())
                    return true
                }
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    return false
                }else{
                    presenter.getSearchTeam(query.toString())
                    return true
                }
            }
        })
    }
}
