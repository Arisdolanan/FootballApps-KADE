package com.hokagelab.www.footballmatchdua.mainMenu.match

import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.api.SportDBApi
import com.hokagelab.www.footballmatchdua.model.*
import com.hokagelab.www.footballmatchdua.view.MainSearchMatch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PresenterSearchMatch(private val view: MainSearchMatch,
                           private val apiRespositori: ApiRepository,
                           private val gson: Gson
                    ){

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