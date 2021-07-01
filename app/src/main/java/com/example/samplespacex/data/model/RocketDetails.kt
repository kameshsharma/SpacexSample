package com.example.samplespacex.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "rocket_detail_table")
data class RocketDetails(
    @Json(name = "id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = "",

    @Json(name = "name")
    @ColumnInfo(name = "name")
    val name: String = "",

    @Json(name = "country")
    @ColumnInfo(name = "country")
    val country: String = "",

    @Json(name = "wikipedia")
    @ColumnInfo(name = "wiki_url")
    val wikiUrl: String = "",

    @Json(name = "description")
    @ColumnInfo(name = "description")
    val description: String = "",

    @Json(name = "cost_per_launch")
    @ColumnInfo(name = "cost_per_launch")
    val costPerLaunch: Int = 0,

    @Json(name = "success_rate_pct")
    @ColumnInfo(name = "success_rate_pct")
    val successRatePct: Int = 0,

    @Json(name = "active")
    @ColumnInfo(name = "active")
    val active: Boolean = false,

    @Json(name = "flickr_images")
    @ColumnInfo(name = "images")
    val images: List<String> = emptyList()

//    @Json(name = "height")
//    @ColumnInfo(name = "height")
//    val height: List<String> = emptyList(),
//
//    @Json(name = "diameter")
//    @ColumnInfo(name = "diameter")
//    val diameter: List<String> = emptyList()
)