package com.imandroid.streambox.features.home.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeContentDao {

    @Query("SELECT * FROM home_content")
    fun observeAll(): Flow<List<HomeContentEntity>>

    @Query("SELECT * FROM home_content")
    suspend fun getAll(): List<HomeContentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<HomeContentEntity>)

    @Query("DELETE FROM home_content")
    suspend fun clearAll()
}
