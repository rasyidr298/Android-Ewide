package com.ewide.test.rasyidr.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "gameEntities")
data class GameEntities(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "gameID")
    var gameID: Int? = 0,

    @ColumnInfo(name = "cheapest")
    var cheapest: String? = "",

    @ColumnInfo(name = "cheapestDealID")
    var cheapestDealID: String? = "",

    @ColumnInfo(name = "external")
    var external: String? = "",

    @ColumnInfo(name = "internalName")
    var internalName: String? = "",

    @ColumnInfo(name = "steamAppID")
    var steamAppID: String? = "",

    @ColumnInfo(name = "thumb")
    var thumb: String? = "",

    @ColumnInfo(name = "isFav")
    var isFav: Boolean? = false
)