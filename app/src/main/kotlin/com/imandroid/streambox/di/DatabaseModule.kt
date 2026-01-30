package com.imandroid.streambox.di

import android.content.Context
import androidx.room.Room
import com.imandroid.streambox.db.StreamBoxDatabase
import com.imandroid.streambox.features.home.data.local.db.HomeContentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "streambox.db"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): StreamBoxDatabase = Room.databaseBuilder(
        context,
        StreamBoxDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideHomeContentDao(
        database: StreamBoxDatabase
    ): HomeContentDao = database.homeContentDao()
}
