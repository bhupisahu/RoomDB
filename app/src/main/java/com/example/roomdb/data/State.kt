package com.example.roomdb.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "state_table")
class State(

    @PrimaryKey var stateName: String,
    var countryName: String

)