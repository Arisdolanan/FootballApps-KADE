package com.hokagelab.www.footballmatchdua.model

data class Favorite(
    val idE: Long?,
    val idSatu: String,
    val idDua: String,
    val dateSchedule: String,
    val namaSatu: String,
    val namaDua: String,
    val SatuScore: String,
    val DuaScore: String) {

    companion object {
        const val TABLE_FAVORITES: String = "TABLE_FAVORITES"
        const val EVENT_ID: String = "EVENT_ID"
        const val ID_SATU: String = "ID_SATU"
        const val ID_DUA: String = "ID_DUA"
        const val DATE_SCHEDULE: String = "DATE_SCHEDULE"
        const val NAMA_SATU: String = "NAMA_SATU"
        const val NAMA_DUA: String = "NAMA_DUA"
        const val SCORE_SATU: String = "SCORE_SATU"
        const val SCORE_DUA: String = "SCORE_DUA"
    }
}