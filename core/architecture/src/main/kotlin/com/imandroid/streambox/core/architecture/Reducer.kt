package com.imandroid.streambox.core.architecture

import kotlinx.coroutines.flow.Flow

/**
 * Contract for state containers that process actions and emit states.
 *
 * ## Pattern
 * Reducers are the single source of truth for screen state.
 * ViewModels hold a reference to a reducer and observe its state flow.
 *
 * ## Responsibilities
 * - Maintain current state
 * - Process incoming actions
 * - Emit new states based on action + current state
 *
 * ## Thread Safety
 * Implementations must ensure thread-safe state updates.
 * See [StateReducer] for the standard implementation.
 *
 * @param S The state type, must implement [State]
 * @param A The action type, must implement [Action]
 *
 * @see StateReducer for the base implementation
 */
interface Reducer<S : State, A : Action> {

    /**
     * Observable stream of states.
     *
     * Collectors will receive the current state immediately upon collection,
     * followed by any subsequent state changes.
     */
    val state: Flow<S>

    /**
     * Dispatches an action to trigger a state transition.
     *
     * This is a suspend function to allow for thread-safe state updates.
     * The actual state computation happens synchronously within the
     * [StateReducer.reduce] function.
     *
     * @param action The action to process
     */
    suspend fun update(action: A)
}
