package com.hokagelab.www.footballmatchdua.view

import com.hokagelab.www.footballmatchdua.model.*
import com.hokagelab.www.footballmatchdua.model.LeagueResponse

interface MainView {
    fun last(data: List<LastEvent>)
    fun next(data: List<NextEvent>)

    fun logoHome(data: List<LogoTeam>)
    fun logoAway(data: List<LogoTeam>)

    fun eventDetail(data: List<Event>)

    fun showLeagueList(data: LeagueResponse)

    fun showLogoTeams(data: List<TeamLogo>)
    fun showDetailTeams(data: List<TeamDetail>)

    fun showFavoritTeam(data: List<FavoriteTeam>)
    fun handleEmpty()

}