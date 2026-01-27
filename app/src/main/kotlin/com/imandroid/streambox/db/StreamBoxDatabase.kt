package com.imandroid.streambox.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imandroid.streambox.features.home.data.local.db.HomeContentDao
import com.imandroid.streambox.features.home.data.local.db.HomeContentEntity

@Database(
    entities = [HomeContentEntity::class],
    version = 1,
    exportSchema = true
)
abstract class StreamBoxDatabase : RoomDatabase() {
    abstract fun homeContentDao(): HomeContentDao
}
