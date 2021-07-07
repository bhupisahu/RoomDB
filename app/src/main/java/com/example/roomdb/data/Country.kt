package com.example.roomdb.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_table")
class Country(

    @PrimaryKey var countryName: String

)