package com.imandroid.streambox.features.home.data.local

import com.imandroid.streambox.features.home.data.local.db.HomeContentEntity
import kotlinx.coroutines.flow.Flow

interface HomeContentLocalDataSource {
    fun observeAll(): Flow<List<HomeContentEntity>>
    suspend fun getAll(): List<HomeContentEntity>
    suspend fun upsertAll(items: List<HomeContentEntity>)
    suspend fun clearAll()
}
