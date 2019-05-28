package com.hokagelab.www.footballmatchdua.view

import com.hokagelab.www.footballmatchdua.model.Event
import com.hokagelab.www.footballmatchdua.model.LogoTeam

interface MainDetailMatch {
    fun logoHome(data: List<LogoTeam>)
    fun logoAway(data: List<LogoTeam>)
    fun eventDetail(data: List<Event>)
    fun handleEmpty()

}