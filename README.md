# StreamBox - Offline Mediator

This branch introduces the mediator that coordinates remote and local data
sources. The goal is to keep repositories thin while adding network-first,
DB-fallback behavior for offline browsing.

## Why Mediator Logic Is Separate
Persistence infrastructure and coordination logic are different concerns. This
branch focuses only on decision flow between sources without changing UI,
reducers, or UseCase contracts.

## What Changed
- Feature-specific offline mediator in the data layer
- RemoteDataSource and LocalDataSource orchestration
- Repository delegates to mediator
- Strict mapping boundaries preserved

## Files to Read First
- `features/home/data/mediator/HomeOfflineMediator.kt`
- `features/home/data/mediator/HomeOfflineMediatorImpl.kt`
- `features/home/data/remote/HomeContentRemoteDataSource.kt`
- `features/home/data/local/HomeContentLocalDataSource.kt`
- `features/home/data/HomeContentRepositoryImpl.kt`

## Flow Summary
UI event -> ViewModel -> UseCase -> Repository -> OfflineMediator
-> RemoteDataSource / LocalDataSource -> Domain models -> UI mapper
-> Reducer action -> StateFlow -> UI
