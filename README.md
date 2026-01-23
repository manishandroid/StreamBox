# StreamBox - Repository Pattern

This branch introduces the Repository pattern to decouple the domain layer from
data sources. UseCases now depend on repository interfaces, not concrete data.

## Why Repositories Now
Repositories define how the domain accesses data without revealing the source.
This keeps UseCases focused on business intent and prepares the app for real
API integration later.

## What Changed
- Domain repository interface for home content
- In-memory repository implementation with deterministic data
- UseCase depends on the repository interface
- Mapper and reducer flow remains unchanged

## Files to Read First
- `features/home/domain/repository/HomeContentRepository.kt`
- `features/home/data/HomeContentRepositoryImpl.kt`
- `features/home/domain/usecase/LoadHomeContentUseCase.kt`
- `features/home/mapper/HomeContentUiMapper.kt`
- `features/home/ui/HomeViewModel.kt`

## Flow Summary
UI event -> ViewModel -> UseCase -> Repository -> Domain model
-> Mapper -> Reducer action -> StateFlow -> UI
