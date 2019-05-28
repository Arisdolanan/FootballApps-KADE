package com.hokagelab.www.footballmatchdua.mainMenu.match.next

import com.hokagelab.www.footballmatchdua.model.*
import com.hokagelab.www.footballmatchdua.model.LeagueResponse

interface MainNext {
    fun next(data: List<NextEvent>)
    fun showLeagueList(data: LeagueResponse)
    fun handleEmpty()

}