# StreamBox - Reducer Basics

This branch introduces a minimal, production-style StateReducer setup for a
single placeholder screen. It focuses on the reducer pattern itself: actions
flow in, state flows out, and the UI simply renders what it observes.

## What a StateReducer Is
A StateReducer is a pure function wrapped in a small state container. It:

- Receives an **Action**
- Combines it with the **current State**
- Produces a **new State** without side effects

This makes state transitions predictable, testable, and easy to reason about.

## Why This Pattern Scales
Large apps benefit from reducers because they:

- Centralize state transitions in one place
- Keep UI logic minimal and declarative
- Make it easy to test each state change in isolation
- Support unidirectional data flow across teams and features

## Files to Read First
- `core/architecture/StateReducer.kt` (base reducer contract)
- `features/home/ui/HomeAction.kt` (actions)
- `features/home/ui/HomeState.kt` (state model)
- `features/home/ui/HomeReducer.kt` (pure transitions)
- `features/home/ui/HomeViewModel.kt` (action coordination)
- `features/home/ui/HomeScreen.kt` (state-driven UI)

## How This Sets the Foundation
The home screen now demonstrates:

- Unidirectional data flow (UI -> Action -> Reducer -> State -> UI)
- Immutable state updates
- Clear separation between user events and state transitions

Later branches will introduce real data sources, use cases, and persistence on
top of this reducer foundation.
