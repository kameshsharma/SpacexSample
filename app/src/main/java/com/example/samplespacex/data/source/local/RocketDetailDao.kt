package com.example.samplespacex.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.samplespacex.data.model.RocketDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDetailDao {
    @Query("SELECT * FROM rocket_detail_table where id = :id")
    fun getRocketsDetail(id:String): Flow<RocketDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(rockets: RocketDetails)

    @Query("DELETE FROM rocket_detail_table")
    suspend fun deleteRocketsDetail()
}