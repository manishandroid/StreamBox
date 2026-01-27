package com.imandroid.streambox.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.imandroid.streambox.features.home.data.local.db.HomeContentEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StreamBoxDatabaseTest {

    private lateinit var database: StreamBoxDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            StreamBoxDatabase::class.java
        ).build()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun upsert_and_read_back_home_content() = runBlocking {
        val dao = database.homeContentDao()
        val items = listOf(
            HomeContentEntity(id = "1", title = "Night Signal", year = "2024", category = "Sci-Fi", imageUrl = null),
            HomeContentEntity(id = "2", title = "Harborline", year = "2023", category = "Drama", imageUrl = null)
        )

        dao.upsertAll(items)
        val stored = dao.getAll()

        assertEquals(2, stored.size)
        assertEquals("Night Signal", stored.first().title)
    }
}
