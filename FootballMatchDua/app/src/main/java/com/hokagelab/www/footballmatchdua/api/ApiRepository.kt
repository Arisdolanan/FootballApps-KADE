package com.hokagelab.www.footballmatchdua.api

import java.net.URL

class ApiRepository{
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}