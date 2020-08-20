package com.cbellmont.ejemplorepaso

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {

    suspend fun loadPreguntas(context: Context) : LiveData<List<Pregunta>> {
        return withContext(Dispatchers.IO){
            App.getDatabase(context).PreguntaDao().getAllLive()
        }
    }

    suspend fun addPregunta(context: Context, enunciadoPregunta : String){
        withContext(Dispatchers.IO){
            App.getDatabase(context).PreguntaDao().insert(Pregunta(enunciadoPregunta))
        }
    }
}