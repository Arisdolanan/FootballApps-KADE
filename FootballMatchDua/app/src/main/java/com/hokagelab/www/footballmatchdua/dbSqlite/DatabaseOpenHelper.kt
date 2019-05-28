package com.hokagelab.www.footballmatchdua.dbSqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hokagelab.www.footballmatchdua.model.Favorite
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {

    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null)
                instance = DatabaseOpenHelper(ctx.applicationContext)
            return instance as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            Favorite.TABLE_FAVORITES, true,
                Favorite.EVENT_ID to INTEGER + PRIMARY_KEY,
                Favorite.ID_SATU to TEXT,
                Favorite.ID_DUA to TEXT,
                Favorite.DATE_SCHEDULE to TEXT,
                Favorite.NAMA_SATU to TEXT,
                Favorite.NAMA_DUA to TEXT,
                Favorite.SCORE_SATU to TEXT,
                Favorite.SCORE_DUA to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorite.TABLE_FAVORITES, true)
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)

