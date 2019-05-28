package com.hokagelab.www.footballmatchdua.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.hokagelab.www.footballmatchdua.mainMenu.favorit.favMatch.FragmentFavorit
import com.hokagelab.www.footballmatchdua.mainMenu.favorit.favTeam.FavoritTeamFragment

class FavAdapter(fm:FragmentManager):FragmentPagerAdapter(fm){
    private val pages = listOf(
        FragmentFavorit(),
        FavoritTeamFragment()
    )
    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FragmentFavorit()
            1 -> fragment = FavoritTeamFragment()

            //        return pages[position]
        }
        return fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Matches"
            else -> "Teams"
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

}