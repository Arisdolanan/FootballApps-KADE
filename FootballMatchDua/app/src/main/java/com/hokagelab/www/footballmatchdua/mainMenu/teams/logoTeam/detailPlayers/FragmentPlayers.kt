package com.hokagelab.www.footballmatchdua.mainMenu.teams.logoTeam.detailPlayers


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.adapter.PlayerAdapter
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.model.TeamDetail
import org.jetbrains.anko.support.v4.intentFor


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentPlayers : Fragment(), MainPlayer {

    override fun showDetailTeams(data: List<TeamDetail>) {
        lastE.clear()
        lastE.addAll(data)
        last.notifyDataSetChanged()
    }

    interface OnFragmentInteractionListener {
        fun onMatchListItemClick(match: TeamDetail)
    }

    private var lastE: MutableList<TeamDetail> = mutableListOf()
    private lateinit var last: PlayerAdapter
    private lateinit var presenter: PresenterPlayer
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment_players, container, false)

        val id = activity!!.intent.getStringExtra("idTeam")
        val TAG = "ID = "
        Log.e(TAG, "ID APA = "+id)

        val recycler = view.findViewById<RecyclerView>(R.id.playerRecycler)
        recycler.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?

        last = PlayerAdapter(lastE){
            val match = lastE.get(lastE.indexOf(it))
//            Toast.makeText(activity, "data = "+match, Toast.LENGTH_SHORT).show()
            startActivity(intentFor<DetailsPlayer>(
                "idTeam" to it.idTeam,
                "namaPlayer" to it.strPlayer,
                "posisiPlayer" to it.strPosition,
                "beratPlayer" to it.strBerat,
                "tinggiPlayer" to it.strTinggi,
                "overviewPlayer" to it.strOverview,
                "fotoPlayer" to it.playerSlide

            ))

            listener?.onMatchListItemClick(match)
        }

        recycler.adapter = last

        presenter = PresenterPlayer(this, ApiRepository(), Gson())
        presenter.getTeamDetail(id)

        return view
    }


}
