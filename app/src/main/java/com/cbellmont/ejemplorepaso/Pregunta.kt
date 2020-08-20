package com.cbellmont.ejemplorepaso

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Pregunta(val pregunta : String) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}