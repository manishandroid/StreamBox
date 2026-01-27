package com.imandroid.streambox.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object RoomDatabaseFactory {

    fun <T : RoomDatabase> create(
        context: Context,
        databaseClass: Class<T>,
        name: String
    ): T = Room.databaseBuilder(
        context.applicationContext,
        databaseClass,
        name
    ).fallbackToDestructiveMigration().build()

    fun <T : RoomDatabase> createInMemory(
        context: Context,
        databaseClass: Class<T>
    ): T = Room.inMemoryDatabaseBuilder(
        context.applicationContext,
        databaseClass
    ).fallbackToDestructiveMigration().build()
}
