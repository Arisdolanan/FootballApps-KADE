package com.hokagelab.www.footballmatchdua.mainMenu.match.last

import com.hokagelab.www.footballmatchdua.model.*
import com.hokagelab.www.footballmatchdua.model.LeagueResponse

interface MainLast {
    fun last(data: List<LastEvent>)
    fun showLeagueList(data: LeagueResponse)

    fun handleEmpty()

}