# StreamBox - Unit Testing

This branch standardizes StreamBox’s unit testing approach so it mirrors
production-grade patterns. The goal is to make tests deterministic, readable,
and aligned across layers without touching runtime behavior.

## What Was Limited Before
- Tests mixed fakes and ad-hoc coroutine setups
- Some tests relied on manual dispatcher overrides
- Flow and coroutine testing patterns were inconsistent

## What Changed
- Mocking is standardized with MockK
- Coroutine tests use `runTest` and injected test dispatchers
- Flow testing adopts Turbine for deterministic assertions
- Tests use Given/When/Then naming and intent-driven structure

## Layer Coverage
- Reducers: pure state transitions
- UseCases: orchestration and dependency interaction
- Repositories/Mediators: success and failure behavior
- Mappers: deterministic transformations
- ViewModels: state flow emissions

## Why These Tools
- **MockK** provides flexible mocking without boilerplate
- **kotlinx-coroutines-test** makes async logic deterministic
- **Turbine** simplifies Flow assertions and improves readability

## Notes
- No production behavior changes
- No instrumentation or UI tests
- Focus remains on unit-level confidence and clarity

