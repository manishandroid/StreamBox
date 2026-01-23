# StreamBox - Mapper Pattern

This branch introduces the Mapper pattern to keep the domain layer stable while
allowing the UI to evolve independently. Mapping is the boundary that prevents
presentation needs from leaking into business models.

## Why Introduce Mappers Here
UseCases return domain models that represent business intent. UI needs a
presentation-friendly shape. Mappers provide:

- A clear separation between domain and UI models
- Pure, synchronous transformations
- A single place to evolve UI shape without touching domain models

## What Changed
- Domain models remain in the domain layer
- UI models live in the UI layer
- Mappers translate domain -> UI
- ViewModel applies mapping before dispatching reducer actions

Reducers still only transform UI state from actions.

## Files to Read First
- `features/home/domain/HomeContent.kt`
- `features/home/ui/model/HomeContentUi.kt`
- `features/home/mapper/HomeContentUiMapper.kt`
- `features/home/ui/HomeViewModel.kt`

## Flow Summary
UI event -> ViewModel -> UseCase (domain model) -> Mapper (UI model)
-> Reducer action -> StateFlow -> UI
