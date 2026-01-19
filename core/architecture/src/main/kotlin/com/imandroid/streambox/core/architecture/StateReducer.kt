package com.imandroid.streambox.core.architecture

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

/**
 * Base implementation for UI state reducers.
 *
 * ## Pattern
 * All feature reducers extend this base class.
 * This provides:
 * - Thread-safe state updates via [DispatcherProvider.default]
 * - StateFlow-based state exposure for Compose integration
 * - A pure [reduce] function for predictable state transitions
 *
 * ## Implementation Guide
 * ```kotlin
 * class QuoteReducer(
 *     dispatcherProvider: DispatcherProvider
 * ) : StateReducer<QuoteScreenState, QuoteAction>(
 *     initialState = QuoteScreenState.None,
 *     dispatcherProvider = dispatcherProvider
 * ) {
 *     override fun reduce(
 *         action: QuoteAction,
 *         currentState: QuoteScreenState
 *     ): QuoteScreenState = when (action) {
 *         is QuoteAction.Load -> QuoteScreenState.Loading
 *         is QuoteAction.SetContent -> QuoteScreenState.Content(action.quote)
 *         is QuoteAction.SetError -> QuoteScreenState.Error(action.error.message)
 *     }
 * }
 * ```
 *
 * ## Trade-offs
 *
 * **StateFlow vs SharedFlow:**
 * We use StateFlow which always has a current value and replays only the
 * latest state. This means rapid updates may skip intermediate states.
 * For event-like emissions (snackbars, navigation), consider SharedFlow.
 *
 * **Synchronous reduce:**
 * The [reduce] function is synchronous and should be pure (no side effects).
 * Side effects like API calls should happen in the ViewModel, which then
 * dispatches result actions to the reducer.
 *
 * @param STATE The state type for this reducer
 * @param ACTION The action type this reducer handles
 * @param initialState The starting state before any actions
 * @param dispatcherProvider Provides coroutine dispatchers for thread safety
 *
 * @see Reducer for the contract
 * @see DispatcherProvider for testing support
 */
abstract class StateReducer<STATE : State, ACTION : Action>(
    initialState: STATE,
    private val dispatcherProvider: DispatcherProvider
) : Reducer<STATE, ACTION> {

    private val _state = MutableStateFlow(initialState)

    /**
     * The current state as a StateFlow.
     *
     * Compose UI should collect this using `collectAsStateWithLifecycle()`.
     */
    final override val state: StateFlow<STATE> get() = _state

    /**
     * Pure function that computes new state from current state and action.
     *
     * ## Requirements
     * - Must be pure: same inputs always produce same output
     * - Must be free of side effects: no network calls, no logging
     * - Should handle all action types (use exhaustive when)
     *
     * @param action The action that was dispatched
     * @param currentState The state before this action
     * @return The new state after applying the action
     */
    protected abstract fun reduce(action: ACTION, currentState: STATE): STATE

    /**
     * Dispatches an action to update the state.
     *
     * State updates happen on [DispatcherProvider.default] to ensure
     * thread safety without blocking the main thread.
     */
    final override suspend fun update(action: ACTION) {
        withContext(dispatcherProvider.default) {
            _state.value = reduce(action, state.value)
        }
    }
}
