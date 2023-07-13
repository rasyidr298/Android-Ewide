package com.ewide.test.rasyidr.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ewide.test.rasyidr.data.local.GameEntities

@Database(
    entities = [
        GameEntities::class], version = 2, exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}