package com.hokagelab.www.footballmatchdua.mainMenu.teams

import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.api.SportDBApi
import com.hokagelab.www.footballmatchdua.model.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PresenterSearchTeam(private val view: MainSearchTeam,
                          private val apiRespositori: ApiRepository,
                          private val gson: Gson
                    ){

    fun getSearchTeam(namaTeam: String?) {
        doAsync {
            val data = gson.fromJson(apiRespositori
                .doRequest(SportDBApi.getSearchTeam(namaTeam)),
                SearchTeamResponse::class.java
            )

            uiThread {
                if(!data.teams.isNullOrEmpty())
                    view.showLogoTeams(data.teams)
                else
                    view.handleEmpty()
            }
        }
    }


}