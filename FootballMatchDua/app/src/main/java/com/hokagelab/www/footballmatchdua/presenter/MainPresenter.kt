package com.hokagelab.www.footballmatchdua.presenter

import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.api.SportDBApi
import com.hokagelab.www.footballmatchdua.model.*
import com.hokagelab.www.footballmatchdua.view.MainDetailMatch
import com.hokagelab.www.footballmatchdua.view.MainView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
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