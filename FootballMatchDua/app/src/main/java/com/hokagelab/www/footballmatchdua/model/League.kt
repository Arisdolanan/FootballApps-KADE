package com.hokagelab.www.footballmatchdua.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class League(
    @SerializedName("idLeague")
    var idLeague: String? = null,
    @SerializedName("strLeague")
    var strLeague: String? = null,
    @SerializedName("strSport")
    var strSport: String? = null

) : Parcelable {
    override fun toString(): String {
        return strLeague.toString()
    }
}