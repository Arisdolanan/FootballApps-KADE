package com.hokagelab.www.footballmatchdua.mainMenu.match.last

import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.api.SportDBApi
import com.hokagelab.www.footballmatchdua.model.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PresenterLast(private val view: MainLast,
                    private val apiRespositori: ApiRepository,
                    private val gson: Gson
                    ){


    fun getLast(leagueId: String?){
        doAsync {
            val data = gson.fromJson(apiRespositori
                .doRequest(SportDBApi.getLast(leagueId)),
                LastResponse::class.java
            )

            uiThread {
                if(!data.events.isNullOrEmpty())
                view.last(data.events)
                else
                    view.handleEmpty()
            }
        }
    }

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

}