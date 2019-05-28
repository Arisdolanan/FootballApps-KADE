package com.hokagelab.www.footballmatchdua.mainMenu.favorit.favTeam


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.adapter.FavoritTeamAdapter
import com.hokagelab.www.footballmatchdua.dbSqlite.databaseTeam
import com.hokagelab.www.footballmatchdua.mainMenu.favorit.favTeam.detailFav.DetailsFavoritTeam
import com.hokagelab.www.footballmatchdua.model.FavoriteTeam
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.intentFor


class FavoritTeamFragment : Fragment() {

    private var dataFav: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoritTeamAdapter
    private var listener: OnFragmentInteractionListener? = null

    private fun showFavorites() {
        context?.databaseTeam?.use {
            val result = select(FavoriteTeam.TABLE_TEAM)
            val TAG = "Data"
            val favorite = result.parseList(classParser<FavoriteTeam>())
            Log.e(TAG, "Apa ni = "+favorite)

            dataFav.clear()
            dataFav.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    interface OnFragmentInteractionListener {
        fun onMatchListItemClick(match: FavoriteTeam)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_fav_team, container, false)

        val recycleView = view.findViewById<RecyclerView>(R.id.favoritTeamRecycler)
        recycleView.layoutManager = LinearLayoutManager(context)

        adapter = FavoritTeamAdapter(dataFav){
            val match = dataFav.get(dataFav.indexOf(it))
//            Toast.makeText(activity, "data = "+match, Toast.LENGTH_SHORT).show()
            startActivity(intentFor<DetailsFavoritTeam>(
                "id" to it.id,
                "idTeam" to it.TEAM_ID,
                "namaTeam" to it.NAMA_TEAM,
                "fotoTeam" to it.FOTO_TEAM
            ))

            listener?.onMatchListItemClick(match)
        }
        recycleView.adapter = adapter

        showFavorites()

        onResume()

        return view

    }

    override fun onResume() {
        super.onResume()
        showFavorites()
    }

}
