package com.hokagelab.www.footballmatchdua.mainMenu.match.next

import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.api.SportDBApi
import com.hokagelab.www.footballmatchdua.model.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PresenterNext(private val view: MainNext,
                    private val apiRespositori: ApiRepository,
                    private val gson: Gson
                    ){

    fun getNext(leagueId: String?){
        doAsync {
            val data = gson.fromJson(apiRespositori
                .doRequest(SportDBApi.getNext(leagueId)),
                NextResponse::class.java
            )

            uiThread {
                if(!data.events.isNullOrEmpty())
                view.next(data.events)
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