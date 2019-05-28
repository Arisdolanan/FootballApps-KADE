package com.hokagelab.www.footballmatchdua.model

import com.google.gson.annotations.SerializedName

data class LastEvent(
    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("dateEvent")
    var dateSchedule:String? = null,

    @SerializedName("strDate")
    var dateevent: String? = null,

    // Home
    @SerializedName("strHomeTeam")
    var clubsatu: String? = null,

    @SerializedName("idHomeTeam")
    var idclubsatu: String? = null,

    @SerializedName("intHomeScore")
    var scoresatu: String? = null,

    // Away
    @SerializedName("strAwayTeam")
    var clubdua: String? = null,

    @SerializedName("idAwayTeam")
    var idclubdua: String? = null,

    @SerializedName("intAwayScore")
    var scoredua: String? = null,

    @SerializedName("strHomeGoalDetails")
    var homegoaldetails: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var hgoalkeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var hdefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var hmidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    var hforward: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var hsubtitutes: String? = null,

    @SerializedName("strHomeRedCards")
    var hredcard: String? = null,

    @SerializedName("strHomeYellowCards")
    var hyellowcard: String? = null,


    @SerializedName("strAwayGoalDetails")
    var awaygoaldetails: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var agoalkeeper: String? = null,

    @SerializedName("strAwayLineupDefense")
    var adefense: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var amidfield: String? = null,

    @SerializedName("strAwayLineupForward")
    var aforward: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var asubtitutes: String? = null,

    @SerializedName("strAwayRedCards")
    var aredcard: String? = null,

    @SerializedName("strAwayYellowCards")
    var ayellowcard: String? = null,

    @SerializedName("strTime")
    var strTime: String? = null


)