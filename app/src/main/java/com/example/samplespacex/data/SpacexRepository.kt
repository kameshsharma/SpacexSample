package com.example.samplespacex.data

import androidx.room.withTransaction
import com.example.samplespacex.data.source.local.SpacexDatabase
import com.example.samplespacex.data.source.remote.ApiHandler
import com.example.samplespacex.util.networkBoundResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpacexRepository @Inject constructor(
    private val api: ApiHandler,
    private val db: SpacexDatabase
) : SpacexHandlerRepository {
    private val rocketDao = db.rocketDao()
    private val rocketDetailDao = db.rocketDetailDao()

    override fun getRockets() = networkBoundResource(
        query = {
            rocketDao.getRockets()
        },
        fetch = {
            api.getRockets()
        },
        saveFetchResult = { rockets ->
            db.withTransaction {
                with(rocketDao) {
                    deleteRockets()
                    insert(rockets)
                }
            }
        }
    )

    override fun getRocketsDetails(id:String) = networkBoundResource(
        query = {
            rocketDetailDao.getRocketsDetail(id)
        },
        fetch = {
            api.getRocketsDetails(id)
        },
        saveFetchResult = { rocketDetail ->
            db.withTransaction {
                with(rocketDetailDao) {
                    deleteRocketsDetail()
                    insertDetail(rocketDetail)
                }
            }
        }
    )
}