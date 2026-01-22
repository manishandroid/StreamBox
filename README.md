# StreamBox - Use Case Layer

This branch introduces the UseCase layer for the home feature. The goal is to
move business decisions out of the ViewModel while keeping reducers pure and
state-driven UI intact.

## Why UseCases Now
UseCases sit between UI and data. They:

- Express a single, focused operation
- Provide clear input and output
- Keep business rules out of ViewModels
- Stay framework-agnostic and easy to test

At this stage, the data is still static. The point is the flow and boundaries.

## What Changed
- A domain model for home content
- A UseCase that returns home content
- ViewModel delegates work to the UseCase
- ViewModel translates results into reducer actions

The reducer remains unchanged: it only transforms state from actions.

## Files to Read First
- `features/home/domain/usecase/LoadHomeContentUseCase.kt`
- `features/home/ui/HomeViewModel.kt`
- `features/home/ui/HomeAction.kt`
- `features/home/ui/HomeReducer.kt`
- `features/home/ui/HomeState.kt`

## Production-Style Flow
UI event -> ViewModel -> UseCase -> reducer action -> StateFlow -> UI

This mirrors the production pattern where the ViewModel coordinates and the
UseCase owns business intent.
