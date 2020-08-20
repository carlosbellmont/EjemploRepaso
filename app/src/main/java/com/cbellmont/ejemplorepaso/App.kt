package com.cbellmont.ejemplorepaso

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.*


class App : Application() {

    companion object {
        private var db : AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            db?.let { return it }

            db = Room.databaseBuilder(context, AppDatabase::class.java, "database-name")
                .build()
            return db as AppDatabase
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AppDatabase::class.java, "database-name")
            .build()
    }

}