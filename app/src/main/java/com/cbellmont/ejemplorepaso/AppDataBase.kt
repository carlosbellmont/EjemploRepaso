package com.cbellmont.ejemplorepaso

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Pregunta::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun PreguntaDao(): PreguntaDao
}