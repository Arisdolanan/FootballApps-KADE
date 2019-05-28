package com.hokagelab.www.footballmatchdua.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.hokagelab.www.footballmatchdua.mainMenu.match.last.FragmentLast
import com.hokagelab.www.footballmatchdua.mainMenu.match.next.FragmentNext

class PagerAdapter(fm:FragmentManager):FragmentPagerAdapter(fm){
    private val pages = listOf(
        FragmentLast(),
        FragmentNext()
    )
    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FragmentLast()
            1 -> fragment = FragmentNext()
            //        return pages[position]
        }
        return fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Last Match"
            else -> "Next Match"
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

}