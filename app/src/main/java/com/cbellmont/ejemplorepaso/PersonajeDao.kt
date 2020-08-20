package com.cbellmont.ejemplorepaso

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface PreguntaDao {

    @Query("SELECT * FROM Pregunta")
    fun getAllLive(): LiveData<List<Pregunta>>

    @Insert
    fun insert(Pregunta: Pregunta)

    @Delete
    fun delete(Pregunta: Pregunta)

}
