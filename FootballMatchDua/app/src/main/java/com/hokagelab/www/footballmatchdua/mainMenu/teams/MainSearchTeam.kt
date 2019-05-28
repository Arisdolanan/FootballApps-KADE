package com.hokagelab.www.footballmatchdua.mainMenu.teams

import com.hokagelab.www.footballmatchdua.model.*

interface MainSearchTeam {
    fun showLogoTeams(data: List<TeamLogo>)
    fun handleEmpty()

}