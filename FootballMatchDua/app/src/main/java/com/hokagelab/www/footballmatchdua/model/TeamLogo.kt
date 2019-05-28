package com.hokagelab.www.footballmatchdua.model

import com.google.gson.annotations.SerializedName

data class TeamLogo(
    @SerializedName("strTeamBadge")
    var teambadge: String? = null,

    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strTeam")
    var strTeam: String? = null,

    @SerializedName("intFormedYear")
    var year: String? = null,

    @SerializedName("strStadium")
    var stadium: String? = null,

    @SerializedName("strDescriptionEN")
    var overviewTeam: String? = null

)