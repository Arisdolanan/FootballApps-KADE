package com.hokagelab.www.footballmatchdua.mainMenu.favorit.favTeam.detailFav

import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.api.SportDBApi
import com.hokagelab.www.footballmatchdua.model.TeamLogoResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PresenterDetailFavTeam(private val view: MainDetailFavTeam,
                             private val apiRespositori: ApiRepository,
                             private val gson: Gson
                    ){

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