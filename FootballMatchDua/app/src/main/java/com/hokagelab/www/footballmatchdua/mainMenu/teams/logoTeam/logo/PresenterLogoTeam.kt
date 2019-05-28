package com.hokagelab.www.footballmatchdua.mainMenu.teams.logoTeam.logo

import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.api.SportDBApi
import com.hokagelab.www.footballmatchdua.model.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PresenterLogoTeam(private val view: MainLogoTeam,
                        private val apiRespositori: ApiRepository,
                        private val gson: Gson
                    ){

    fun getLeagueList() {
        doAsync{
            val data = gson.fromJson(apiRespositori
                .doRequest(SportDBApi.getLeague()),
                LeagueResponse::class.java
                )

            uiThread {
                view.showLeagueList(data)
            }

        }
    }

    fun getTeamLogo(leagueId: String?) {
        doAsync{
            val data = gson.fromJson(apiRespositori
                .doRequest(SportDBApi.getTeamSearch(leagueId)),
                TeamLogoResponse::class.java
            )

            uiThread {
                view.showLogoTeams(data.teams)
            }

        }
    }

    fun getTeam(leagueId: String?) {
        doAsync{
            val data = gson.fromJson(apiRespositori
                .doRequest(SportDBApi.getLogo(leagueId)),
                TeamLogoResponse::class.java
            )

            uiThread {
                view.showLogoTeams(data.teams)
            }

        }
    }

}