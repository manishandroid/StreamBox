# StreamBox - Network Integration (Ktor)

This branch introduces real network-backed data using Ktor. The goal is to
replace in-memory data with a clean, isolated network layer while keeping the
Repository, UseCase, Mapper, and Reducer responsibilities intact.

## Why Network Integration Now
Repositories are already abstracted and UseCases already depend on interfaces.
That makes this the natural point to swap in a real data source without
changing any upstream logic.

## What Changed
- Shared Ktor client configuration in `core:network`
- Network API and DTOs introduced for content listing
- DTO -> Domain mapping stays inside the data layer
- Repository now calls the network API and returns domain models

## Files to Read First
- `core/network/src/main/kotlin/com/imandroid/streambox/core/network/KtorClientProvider.kt`
- `features/home/data/network/HomeNetworkModule.kt`
- `features/home/data/network/KtorHomeContentApi.kt`
- `features/home/data/network/dto/HomeContentDto.kt`
- `features/home/data/mapper/HomeContentDtoMapper.kt`
- `features/home/data/HomeContentRepositoryImpl.kt`

## Flow Summary
UI event -> ViewModel -> UseCase -> Repository -> Ktor API -> DTO
-> DTO mapper -> Domain model -> UI mapper -> Reducer action -> StateFlow -> UI
