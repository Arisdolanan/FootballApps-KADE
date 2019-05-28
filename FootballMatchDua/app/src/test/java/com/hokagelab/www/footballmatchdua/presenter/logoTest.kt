package com.hokagelab.www.footballmatchdua.presenter

import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.TestContextProvider
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.api.SportDBApi
import com.hokagelab.www.footballmatchdua.model.LogoTeam
import com.hokagelab.www.footballmatchdua.model.LogoTeamResponse
import com.hokagelab.www.footballmatchdua.view.MainDetailMatch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class logoTest {
    @Mock
    private
    lateinit var view: MainDetailMatch

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PresenterDetail

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = PresenterDetail(
            view,
            apiRepository,
            gson,
            TestContextProvider()
        )
    }


    @Test
    fun testGetNextTeam(){
        val logoTeams: MutableList<LogoTeam> = mutableListOf()
        val response = LogoTeamResponse(logoTeams)
        val id = "1234"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportDBApi.getLogo(id)),
                LogoTeamResponse::class.java
                )).thenReturn(response)

            presenter.getLogo(id, "home")
            presenter.getLogo(id, "away")
            Mockito.verify(view).logoAway(logoTeams)
            Mockito.verify(view).logoHome(logoTeams)

        }
    }

}