package com.hokagelab.www.footballmatchdua.model

import com.google.gson.annotations.SerializedName

data class TeamDetailResponse (
    @SerializedName("player")
    val player:List<TeamDetail>
)