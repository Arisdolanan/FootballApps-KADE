package com.hokagelab.www.footballmatchdua.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.hokagelab.www.footballmatchdua.mainMenu.teams.logoTeam.detailOverview.FragmentOverview
import com.hokagelab.www.footballmatchdua.mainMenu.teams.logoTeam.detailPlayers.FragmentPlayers

class TeamAdapter(fm:FragmentManager):FragmentPagerAdapter(fm){
    private val pages = listOf(
        FragmentOverview(),
        FragmentPlayers()
    )
    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FragmentOverview()
            1 -> fragment = FragmentPlayers()
            //        return pages[position]
        }
        return fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Overview"
            else -> "Players"
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

}