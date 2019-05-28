package com.hokagelab.www.footballmatchdua.mainMenu.match.last


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.adapter.LastAdapter
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.model.*

import kotlinx.android.synthetic.main.fragment_fragment_last.view.*
import org.jetbrains.anko.support.v4.intentFor
import android.view.MenuInflater
import com.hokagelab.www.footballmatchdua.mainMenu.match.detailMatch.DetailFavoritMatch
import com.hokagelab.www.footballmatchdua.mainMenu.match.SearchMatch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentLast : Fragment(), MainLast {

    private var lastE: MutableList<LastEvent> = mutableListOf()
    private lateinit var presenter: PresenterLast
    private lateinit var last: LastAdapter
    private lateinit var recycler : RecyclerView
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var spinner: Spinner
    private lateinit var item: League

    override fun last(data: List<LastEvent>){
        lastE.clear()
        lastE.addAll(data)
        last.notifyDataSetChanged()
    }
    override fun handleEmpty() {
        lastE.clear()
        last.notifyDataSetChanged()
       }
    interface OnFragmentInteractionListener {
        fun onMatchListItemClick(match: LastEvent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment_last, container, false)

        presenter = PresenterLast(this, ApiRepository(), Gson())
        presenter.getLeagueList()


        spinner = view.spinner_last

        recycler = view.findViewById(R.id.lastRecycler)
        recycler.layoutManager = LinearLayoutManager(context)

        last = LastAdapter(lastE){
            val match = lastE.get(lastE.indexOf(it))
//            Toast.makeText(activity, "data = "+match, Toast.LENGTH_SHORT).show()
            startActivity(intentFor<DetailFavoritMatch>(
                "idEvent" to it.eventId,
                "idtimsatu" to it.idclubsatu,
                "idtimdua" to it.idclubdua
            ))

            listener?.onMatchListItemClick(match)
        }

        recycler.adapter = last

        presenter = PresenterLast(this, ApiRepository(), Gson())
        presenter.getLast("4328")

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
                presenter.getLast(item.idLeague.toString())
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_searchlogo, menu)
        return
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search) {
            val moveIntent = Intent(context, SearchMatch::class.java)
            startActivity(moveIntent)
        }
        return super.onOptionsItemSelected(item)
    }

}
