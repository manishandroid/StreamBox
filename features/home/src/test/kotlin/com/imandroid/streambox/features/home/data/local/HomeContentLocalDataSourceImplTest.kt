package com.imandroid.streambox.features.home.data.local

import com.imandroid.streambox.features.home.data.local.db.HomeContentDao
import com.imandroid.streambox.features.home.data.local.db.HomeContentEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeContentLocalDataSourceImplTest {

    @Test
    fun `given dao data when observeAll then returns flow`() = runTest {
        val dao = mockk<HomeContentDao>()
        val entities = listOf(HomeContentEntity(id = "1", title = "Cached", year = "2024", category = "Drama", imageUrl = null))
        every { dao.observeAll() } returns flowOf(entities)
        val dataSource = HomeContentLocalDataSourceImpl(dao)

        val result = dataSource.observeAll().first()

        assertEquals(entities, result)
    }

    @Test
    fun `given dao when getAll then delegates`() = runTest {
        val dao = mockk<HomeContentDao>()
        val entities = listOf(HomeContentEntity(id = "1", title = "Cached", year = "2024", category = "Drama", imageUrl = null))
        coEvery { dao.getAll() } returns entities
        val dataSource = HomeContentLocalDataSourceImpl(dao)

        val result = dataSource.getAll()

        assertEquals(entities, result)
        coVerify(exactly = 1) { dao.getAll() }
    }

    @Test
    fun `given items when upsertAll then delegates`() = runTest {
        val dao = mockk<HomeContentDao>(relaxed = true)
        val items = listOf(HomeContentEntity(id = "1", title = "Cached", year = "2024", category = "Drama", imageUrl = null))
        val dataSource = HomeContentLocalDataSourceImpl(dao)

        dataSource.upsertAll(items)

        coVerify(exactly = 1) { dao.upsertAll(items) }
    }

    @Test
    fun `when clearAll then delegates`() = runTest {
        val dao = mockk<HomeContentDao>(relaxed = true)
        val dataSource = HomeContentLocalDataSourceImpl(dao)

        dataSource.clearAll()

        coVerify(exactly = 1) { dao.clearAll() }
    }
}
