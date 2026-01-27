package com.imandroid.streambox.db

import android.content.Context
import com.imandroid.streambox.core.database.RoomDatabaseFactory

object StreamBoxDatabaseProvider {

    private const val DATABASE_NAME = "streambox.db"

    @Volatile
    private var instance: StreamBoxDatabase? = null

    fun init(context: Context) {
        if (instance != null) return
        synchronized(this) {
            if (instance == null) {
                instance = RoomDatabaseFactory.create(
                    context = context,
                    databaseClass = StreamBoxDatabase::class.java,
                    name = DATABASE_NAME
                )
            }
        }
    }

    fun get(): StreamBoxDatabase =
        checkNotNull(instance) { "StreamBoxDatabaseProvider not initialized" }

    fun createInMemory(context: Context): StreamBoxDatabase =
        RoomDatabaseFactory.createInMemory(
            context = context,
            databaseClass = StreamBoxDatabase::class.java
        )
}
