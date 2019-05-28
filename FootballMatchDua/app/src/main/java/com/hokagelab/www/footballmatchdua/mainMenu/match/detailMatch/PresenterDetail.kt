package com.hokagelab.www.footballmatchdua.presenter

import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.api.SportDBApi
import com.hokagelab.www.footballmatchdua.model.*
import com.hokagelab.www.footballmatchdua.view.MainDetailMatch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PresenterDetail(private val view: MainDetailMatch,
                      private val apiRespositori: ApiRepository,
                      private val gson: Gson
                    ){


    fun getLogo(teamId: String?, teamStatus: String?){
        doAsync {
            val data = gson.fromJson(apiRespositori
                .doRequest(SportDBApi.getLogo(teamId)),
                LogoTeamResponse::class.java
            )

            uiThread {
                if (teamStatus == "home")
                    if(!data.teams.isNullOrEmpty())
                        view.logoHome(data.teams)
                    else
                        view.handleEmpty()

                else
                    if(!data.teams.isNullOrEmpty())
                        view.logoAway(data.teams)
                    else
                        view.handleEmpty()
            }
        }
    }

    fun getEvent(events: String?) {
        doAsync {
            val data = gson.fromJson(apiRespositori
                .doRequest(SportDBApi.getEventDetail(events)),
                EventResponse::class.java
            )

            uiThread {
                if(!data.events.isNullOrEmpty())
                    view.eventDetail(data.events)
                else
                    view.handleEmpty()
            }
        }
    }

    fun getSearch(namaTeam: String?) {
        doAsync {
            val data = gson.fromJson(apiRespositori
                .doRequest(SportDBApi.getSearch(namaTeam)),
                SearchEventResponse::class.java
            )

            uiThread {
                if(!data.event.isNullOrEmpty())
                    view.eventDetail(data.event)
                else
                    view.handleEmpty()

            }
        }
    }


}