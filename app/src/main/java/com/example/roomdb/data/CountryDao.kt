package com.example.roomdb.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdb.data.Country

@Dao
public interface CountryDao {

    @Query ( "SELECT * FROM  country_table")
    fun getAllCountry() : List<Country>


    @Insert
    fun insert(country: Country)


    @Query ( "SELECT * FROM  state_table WHERE countryName=:countryName")
    fun getAllState(countryName:String) : List<State>

    @Insert
    fun insertState(state: State)


    @Query ( "SELECT * FROM  city_table WHERE stateName=:stateName")
    fun getAllCity(stateName:String) : List<City>

    @Insert
    fun insertCity(city: City)
}