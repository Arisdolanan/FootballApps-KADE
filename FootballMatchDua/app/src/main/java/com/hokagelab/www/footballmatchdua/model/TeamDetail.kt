package com.hokagelab.www.footballmatchdua.model

import com.google.gson.annotations.SerializedName

data class TeamDetail(
    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strThumb")
    var playerfoto: String? = null,

    @SerializedName("strFanart1")
    var playerSlide: String? = null,

    @SerializedName("strPlayer")
    var strPlayer: String? = null,

    @SerializedName("strPosition")
    var strPosition: String? = null,

    @SerializedName("strHeight")
    var strTinggi: String? = null,

    @SerializedName("strWeight")
    var strBerat: String? = null,

    @SerializedName("strDescriptionEN")
    var strOverview: String? = null


)