package com.example.roomdb.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Country::class,State::class,City::class], version = 2)
abstract class CountryRoomDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
    companion object {
        /**
         * This is just for singleton pattern
         */
        private var INSTANCE: CountryRoomDatabase? = null
        fun getDatabase(context: Context): CountryRoomDatabase {
            if (INSTANCE == null) {
                synchronized(CountryRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        // Get CountryRoomDatabase database instance
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            CountryRoomDatabase::class.java, "phrase_database"
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
