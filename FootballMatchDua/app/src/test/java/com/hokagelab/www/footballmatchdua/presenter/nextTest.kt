package com.hokagelab.www.footballmatchdua.presenter

import com.google.gson.Gson
import com.hokagelab.www.footballmatchdua.TestContextProvider
import com.hokagelab.www.footballmatchdua.api.ApiRepository
import com.hokagelab.www.footballmatchdua.api.SportDBApi
import com.hokagelab.www.footballmatchdua.model.NextEvent
import com.hokagelab.www.footballmatchdua.model.NextResponse
import com.hokagelab.www.footballmatchdua.view.MainDetailMatch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class nextTest {
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
        val nextTeams: MutableList<NextEvent> = mutableListOf()
        val response = NextResponse(nextTeams)
        val id = "1234"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportDBApi.getNext(id)),
                NextResponse::class.java
                )).thenReturn(response)

            presenter.getNext(id)
            Mockito.verify(view).next(nextTeams)

        }
    }

}