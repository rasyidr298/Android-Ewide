package com.ewide.test.rasyidr.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ewide.test.rasyidr.data.local.GameEntities
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM gameEntities ORDER BY internalName ASC")
    fun getFav(): Flow<List<GameEntities>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFav(gameEntities: GameEntities)

    @Query("SELECT * FROM gameEntities WHERE gameID = :gameID")
    fun checkIsFav(gameID: String): Flow<GameEntities?>

    @Delete
    fun deleteFav(gameEntities: GameEntities)

    @Query("UPDATE gameEntities SET isFav = :isFav WHERE gameID = :gameID")
    fun updateIsFav(isFav: Boolean = true , gameID: String)
}