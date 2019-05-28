package com.hokagelab.www.footballmatchdua.mainMenu.teams.logoTeam.detailPlayers

import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.api.SportDBApi
import com.hokagelab.www.footballmatchdua.model.TeamDetailResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PresenterPlayer(private val view: MainPlayer,
                      private val apiRespositori: ApiRepository,
                      private val gson: Gson
                    ){

    fun getTeamDetail(leagueId: String?) {
        doAsync{
            val data = gson.fromJson(apiRespositori
                .doRequest(SportDBApi.getTeamDetail(leagueId)),
                TeamDetailResponse::class.java
            )

            uiThread {
                view.showDetailTeams(data.player)
            }

        }
    }

}