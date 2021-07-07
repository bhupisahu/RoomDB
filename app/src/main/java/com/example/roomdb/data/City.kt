package com.example.roomdb.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_table")
class City(

    @PrimaryKey var cityName: String,
    var stateName: String,

)