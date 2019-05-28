package com.hokagelab.www.footballmatchdua.presenter

import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.api.SportDBApi
import com.hokagelab.www.footballmatchdua.model.LastEvent
import com.hokagelab.www.footballmatchdua.model.LastResponse
import com.hokagelab.www.footballmatchdua.view.MainDetailMatch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class lastTest {
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
            gson
        )
    }


    @Test
    fun testGetLastTeam(){
        val lastTeams: MutableList<LastEvent> = mutableListOf()
        val response = LastResponse(lastTeams)
        val id = "1234"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportDBApi.getLast(id)),
                LastResponse::class.java
                )).thenReturn(response)

            presenter.getLast(id)
            Mockito.verify(view).last(lastTeams)

        }
    }

}