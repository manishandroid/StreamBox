# StreamBox - Offline Room Database

This branch introduces shared Room database infrastructure. The goal is to make
Room available as a durable local store without changing any repository or
business logic yet.

## Why Room Now
Room is added early so later features can store offline data safely. This
prepares the app for offline browsing while keeping the current flow
network-first.

## What This Branch Adds
- Shared Room infrastructure module in `core:database`
- Single app database definition in `app` with feature entities/DAOs
- Feature-local entities and DAOs for home content
- LocalDataSource abstraction wrapping the DAO
- Database provider for creating/injecting the DB

## What This Branch Does NOT Do
- No mediator or fallback logic
- No repository reads from Room
- No changes to UseCases, reducers, or UI
- No caching strategy or pagination

## Files to Read First
- `core/database/src/main/kotlin/com/imandroid/streambox/core/database/RoomDatabaseFactory.kt`
- `app/src/main/kotlin/com/imandroid/streambox/db/StreamBoxDatabase.kt`
- `app/src/main/kotlin/com/imandroid/streambox/db/StreamBoxDatabaseProvider.kt`
- `features/home/data/local/db/HomeContentDao.kt`
- `features/home/data/local/db/HomeContentEntity.kt`
- `features/home/data/local/HomeContentLocalDataSource.kt`

