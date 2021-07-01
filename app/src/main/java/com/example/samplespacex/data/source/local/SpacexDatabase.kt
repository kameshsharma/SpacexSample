package com.example.samplespacex.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.samplespacex.data.model.Rocket
import com.example.samplespacex.data.model.RocketDetails
import com.example.samplespacex.util.RoomConverters

@Database(
    entities = [Rocket::class, RocketDetails::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class SpacexDatabase : RoomDatabase() {

    abstract fun rocketDao(): RocketDao

    abstract fun rocketDetailDao(): RocketDetailDao

    companion object {
        const val DATABASE_NAME = "musk_database"
    }
}