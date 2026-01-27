package com.imandroid.streambox.features.home.data.local.db

object HomeDatabaseAccessor {

    @Volatile
    private var daoProvider: (() -> HomeContentDao)? = null

    fun init(provider: () -> HomeContentDao) {
        daoProvider = provider
    }

    fun homeContentDao(): HomeContentDao {
        return checkNotNull(daoProvider) { "HomeDatabaseAccessor not initialized" }.invoke()
    }
}
