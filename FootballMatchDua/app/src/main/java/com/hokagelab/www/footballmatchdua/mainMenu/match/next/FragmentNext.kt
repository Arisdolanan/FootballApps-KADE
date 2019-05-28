package com.hokagelab.www.footballmatchdua.mainMenu.match.next


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.adapter.NextAdapter
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.model.*
import com.hokagelab.www.footballmatchdua.model.LeagueResponse
import com.hokagelab.www.footballmatchdua.mainMenu.match.detailMatch.DetailFavoritMatch
import com.hokagelab.www.footballmatchdua.mainMenu.match.SearchMatch
import kotlinx.android.synthetic.main.fragment_fragment_next.view.*
import org.jetbrains.anko.support.v4.intentFor


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FragmentNext : Fragment(), MainNext {
    private var n: MutableList<NextEvent> = mutableListOf()
    private lateinit var presenter: PresenterNext
    private lateinit var next: NextAdapter
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var spinner: Spinner
    private lateinit var item: League

    override fun next(data: List<NextEvent>) {
        n.clear()
        n.addAll(data)
        next.notifyDataSetChanged()
    }
    override fun handleEmpty() {
        n.clear()
        next.notifyDataSetChanged()
    }

    interface OnFragmentInteractionListener {
        fun onMatchListItemClick(match: NextEvent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment_next, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.nextRecycler)
        recycler.layoutManager = LinearLayoutManager(activity)

        presenter = PresenterNext(this, ApiRepository(), Gson())
        presenter.getLeagueList()
        spinner = view.spinner_next

        next = NextAdapter(n){
            val m = n.get(n.indexOf(it))
//            Toast.makeText(activity, "data = "+m, Toast.LENGTH_SHORT).show()
            startActivity(intentFor<DetailFavoritMatch>(
                "clubsatu" to it.clubsatu,
                "clubdua" to it.clubdua,
                "idEvent" to it.eventId,
                "idtimsatu" to it.idclubsatu,
                "idtimdua" to it.idclubdua
            ))

            listener?.onMatchListItemClick(m)

        }

        recycler.adapter = next
        presenter.getNext("4328")

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

                val TAG = "TAG"
                Log.e(TAG, item.toString())
                presenter.getNext(item.idLeague.toString())
//                presenter.getMatchList(resources.getString(R.string.next_match),item.idLeague.toString())
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
