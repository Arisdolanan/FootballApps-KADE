package com.hokagelab.www.footballmatchdua.mainMenu.teams.logoTeam.logo

import com.hokagelab.www.footballmatchdua.model.*
import com.hokagelab.www.footballmatchdua.model.LeagueResponse

interface MainLogoTeam {
    fun showLeagueList(data: LeagueResponse)
    fun showLogoTeams(data: List<TeamLogo>)

}