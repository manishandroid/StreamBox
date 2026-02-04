# StreamBox - Snapshot Testing

This branch adds snapshot testing for Compose UI to catch visual regressions.
The goal is to validate UI output without running emulators or touching
business logic.

## Why Snapshot Testing Now
Unit tests already cover logic. Snapshot tests add confidence at the visual
layer once the architecture and UI structure are stable.

## What Is Snapshot-Tested
- Home screen (idle, loading, error, content)
- Poster tile (2:3)
- Top bar + tabs layout
- Starting / intro screen

## What Snapshot Tests Do Not Do
- No ViewModels, reducers, or use cases
- No data fetching or coroutines
- No Android framework dependencies

## How It Mirrors Production Strategy
Snapshots are focused, declarative, and state-driven. They complement unit
tests without replacing them and only change when the UI changes intentionally.

