package com.hokagelab.www.footballmatchdua.view

import com.hokagelab.www.footballmatchdua.model.Event

interface MainSearchMatch {
    fun eventDetail(data: List<Event>)
    fun handleEmpty()
}