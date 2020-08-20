package com.cbellmont.ejemplorepaso

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PreguntaDao {

    @Query("SELECT * FROM Pregunta")
    fun getAllLive(): LiveData<List<Pregunta>>

    @Insert
    fun insert(Pregunta: Pregunta)

    @Delete
    fun delete(Pregunta: Pregunta)

}
