package com.hokagelab.www.footballmatchdua.model

data class FavoriteTeam(
    val id: Long?,
    val TEAM_ID: String?,
    val NAMA_TEAM: String?,
    val FOTO_TEAM: String?) {

    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID"
        const val TEAM_ID: String = "TEAM_ID"
        const val NAMA_TEAM: String = "NAMA_TEAM"
        const val FOTO_TEAM: String = "FOTO_TEAM"
    }
}