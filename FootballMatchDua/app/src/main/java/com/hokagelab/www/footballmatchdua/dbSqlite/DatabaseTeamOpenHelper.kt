package com.hokagelab.www.footballmatchdua.dbSqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hokagelab.www.footballmatchdua.model.Favorite
import com.hokagelab.www.footballmatchdua.model.FavoriteTeam
import org.jetbrains.anko.db.*

class DatabaseTeamOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {

    companion object {
        private var instance: DatabaseTeamOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseTeamOpenHelper {
            if (instance == null)
                instance = DatabaseTeamOpenHelper(ctx.applicationContext)
            return instance as DatabaseTeamOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            FavoriteTeam.TABLE_TEAM,true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT,
            FavoriteTeam.NAMA_TEAM to TEXT,
            FavoriteTeam.FOTO_TEAM to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteTeam.TABLE_TEAM, true)
    }
}

val Context.databaseTeam: DatabaseTeamOpenHelper
    get() = DatabaseTeamOpenHelper.getInstance(applicationContext)

