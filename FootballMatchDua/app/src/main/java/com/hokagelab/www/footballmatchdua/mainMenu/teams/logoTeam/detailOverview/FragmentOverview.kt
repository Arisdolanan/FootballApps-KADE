package com.hokagelab.www.footballmatchdua.mainMenu.teams.logoTeam.detailOverview


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hokagelab.www.footballmatchdua.R
import kotlinx.android.synthetic.main.fragment_fragment_overview.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentOverview : Fragment(){
    var tvNama: TextView? = null
    var tvTahun: TextView? = null
    var tvStadion: TextView? = null
    var tvOverview: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment_overview, container, false)

        val namaTeam = activity!!.intent.getStringExtra("nmTeam")
        val tahunTeam = activity!!.intent.getStringExtra("yearTeam")
        val stadionTeam = activity!!.intent.getStringExtra("stadiumTeam")
        val overviewTeam = activity!!.intent.getStringExtra("overviewTeam")
        val imgBadges = activity!!.intent.getStringExtra("teamBadge")

        tvNama = view.tv_namaTeam
        tvTahun = view.tv_tahunTeam
        tvStadion = view.tv_stadionTeam
        tvOverview = view.tv_overviewTeam

        tvNama!!.setText(namaTeam)
        tvTahun!!.setText(tahunTeam)
        tvStadion!!.setText(stadionTeam)
        tvOverview!!.setText(overviewTeam)

        Glide.with(view.context).load(imgBadges).into(view.img_badge)

        return view
    }


}
