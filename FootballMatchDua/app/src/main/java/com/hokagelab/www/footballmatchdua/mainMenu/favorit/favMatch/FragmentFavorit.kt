package com.hokagelab.www.footballmatchdua.mainMenu.favorit.favMatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.adapter.FavoritAdapter
import com.hokagelab.www.footballmatchdua.dbSqlite.database
import com.hokagelab.www.footballmatchdua.mainMenu.match.detailMatch.DetailFavoritMatch
import com.hokagelab.www.footballmatchdua.model.Favorite
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.intentFor


class FragmentFavorit : Fragment() {

    private var dataFav: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoritAdapter
    private var listener: OnFragmentInteractionListener? = null

    private fun showFavorites() {
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITES)
            val TAG = "Data"
            val favorite = result.parseList(classParser<Favorite>())
            Log.e(TAG, "Apa ni = "+favorite)

            dataFav.clear()
            dataFav.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    interface OnFragmentInteractionListener {
        fun onMatchListItemClick(match: Favorite)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_favorit, container, false)

        val recycleView = view.findViewById<RecyclerView>(R.id.favRecycler)
        recycleView.layoutManager = LinearLayoutManager(context)

        adapter = FavoritAdapter(dataFav){
            val match = dataFav.get(dataFav.indexOf(it))
//            Toast.makeText(activity, "data = "+match, Toast.LENGTH_SHORT).show()
            startActivity(intentFor<DetailFavoritMatch>(
                "idEvent" to it.idE.toString(),
                "idtimsatu" to it.idSatu,
                "idtimdua" to it.idDua
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
