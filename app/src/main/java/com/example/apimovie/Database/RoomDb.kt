package com.example.apimovie.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apimovie.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class RoomDb: RoomDatabase() {
    abstract val daodb: DaoDb
    companion object {
        @Volatile
        private var INSTANCE: RoomDb? = null
        fun getInstance(context: Context): RoomDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDb::class.java,
                        "Nasa_Database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}