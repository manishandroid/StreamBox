package com.imandroid.streambox.features.home.data.local

import com.imandroid.streambox.features.home.data.local.db.HomeContentDao
import com.imandroid.streambox.features.home.data.local.db.HomeContentEntity
import kotlinx.coroutines.flow.Flow

class HomeContentLocalDataSourceImpl(
    private val dao: HomeContentDao
) : HomeContentLocalDataSource {
    override fun observeAll(): Flow<List<HomeContentEntity>> = dao.observeAll()

    override suspend fun getAll(): List<HomeContentEntity> = dao.getAll()

    override suspend fun upsertAll(items: List<HomeContentEntity>) {
        dao.upsertAll(items)
    }

    override suspend fun clearAll() {
        dao.clearAll()
    }
}
