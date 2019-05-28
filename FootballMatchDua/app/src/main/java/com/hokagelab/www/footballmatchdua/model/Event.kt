package com.hokagelab.www.footballmatchdua.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("dateEvent")
    var dateSchedule:String? = null,

    @SerializedName("strDate")
    var dateEvent: String? = null,

    // Home
    @SerializedName("strHomeTeam")
    var clubSatu: String? = null,

    @SerializedName("idHomeTeam")
    var idClubSatu: String? = null,

    @SerializedName("intHomeScore")
    var scoreSatu: String? = null,

    // Away
    @SerializedName("strAwayTeam")
    var clubDua: String? = null,

    @SerializedName("idAwayTeam")
    var idClubDua: String? = null,

    @SerializedName("intAwayScore")
    var scoreDua: String? = null,

    @SerializedName("strHomeGoalDetails")
    var hGoalDetails: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var hGoalKeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var hDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var hMidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    var hForward: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var hSubtitutes: String? = null,

    @SerializedName("strHomeRedCards")
    var hRedCard: String? = null,

    @SerializedName("strHomeYellowCards")
    var hYellowCard: String? = null,


    @SerializedName("strAwayGoalDetails")
    var aGoalDetails: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var aGoalKeeper: String? = null,

    @SerializedName("strAwayLineupDefense")
    var aDefense: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var aMidfield: String? = null,

    @SerializedName("strAwayLineupForward")
    var aForward: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var aSubtitutes: String? = null,

    @SerializedName("strAwayRedCards")
    var aRedCard: String? = null,

    @SerializedName("strAwayYellowCards")
    var aYellowCard: String? = null,

    @SerializedName("strTime")
    var strTime: String? = null



)