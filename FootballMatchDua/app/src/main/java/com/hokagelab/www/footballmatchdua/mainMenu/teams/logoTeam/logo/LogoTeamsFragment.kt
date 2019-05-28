package com.hokagelab.www.footballmatchdua.mainMenu.teams.logoTeam.logo


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.mainMenu.teams.logoTeam.detailOverview.DetailsTeams
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.adapter.LogoTeamAdapter
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.model.*
import com.hokagelab.www.footballmatchdua.mainMenu.teams.SearchTeam
import kotlinx.android.synthetic.main.fragment_teams.view.*
import org.jetbrains.anko.support.v4.intentFor


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamsFragment : Fragment() , MainLogoTeam {
    override fun showLogoTeams(data: List<TeamLogo>) {
        lastE.clear()
        lastE.addAll(data)
        last.notifyDataSetChanged()
    }

    private var lastE: MutableList<TeamLogo> = mutableListOf()
    private lateinit var presenter: PresenterLogoTeam
    private lateinit var last: LogoTeamAdapter
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var spinner: Spinner
    private lateinit var item: League

    interface OnFragmentInteractionListener {
        fun onMatchListItemClick(match: TeamLogo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_teams, container, false)

        presenter = PresenterLogoTeam(
            this,
            ApiRepository(),
            Gson()
        )
        presenter.getLeagueList()

        spinner = view.spinner_tim


        val recycler = view.findViewById<RecyclerView>(R.id.timRecycler)
        recycler.layoutManager = GridLayoutManager(context, 3)

        last = LogoTeamAdapter(lastE){
            val match = lastE.get(lastE.indexOf(it))
//            Toast.makeText(activity, "data = "+match, Toast.LENGTH_SHORT).show()
            startActivity(intentFor<DetailsTeams>(
                "idTeam" to it.idTeam,
                "nmTeam" to it.strTeam,
                "overviewTeam" to it.overviewTeam,
                "stadiumTeam" to it.stadium,
                "teamBadge" to it.teambadge,
                "yearTeam" to it.year
            ))

            listener?.onMatchListItemClick(match)
        }

        recycler.adapter = last

        presenter.getTeamLogo("4328")

        setHasOptionsMenu(true)

        return view
    }

    override fun showLeagueList(data: LeagueResponse) {
        val spinnerAdapter = ArrayAdapter(context,
            android.R.layout.simple_spinner_item,
            data.leagues.filter { it.strSport.equals("Soccer")})

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                item = spinner.selectedItem as League
                if (item.idLeague == null){
                    presenter.getTeamLogo("4328")
                }else{
                    presenter.getTeamLogo(item.idLeague.toString())
                }
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_searchlogo, menu)
        return
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search) {
            val moveIntent = Intent(context, SearchTeam::class.java)
            startActivity(moveIntent)
        }
        return super.onOptionsItemSelected(item)
    }

}
