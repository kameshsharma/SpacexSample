package com.example.samplespacex.data

import com.example.samplespacex.data.model.Rocket
import com.example.samplespacex.data.model.RocketDetails
import com.example.samplespacex.util.Result
import kotlinx.coroutines.flow.Flow

interface SpacexHandlerRepository {
    fun getRockets(): Flow<Result<List<Rocket>>>

    fun getRocketsDetails(id:String): Flow<Result<RocketDetails>>
}