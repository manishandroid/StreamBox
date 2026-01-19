package com.imandroid.streambox.core.architecture

/**
 * Base interface for all UI states in StreamBox.
 *
 * ## Pattern
 * Every screen state inherits from a common State interface.
 * This enables consistent state modeling across features and allows
 * for generic handling in base classes.
 *
 * ## Usage
 * Feature states should be modeled as sealed classes:
 * ```kotlin
 * sealed class QuoteScreenState : State {
 *     data object None : QuoteScreenState()
 *     data object Loading : QuoteScreenState()
 *     data class Content(val quote: Quote) : QuoteScreenState()
 *     data class Error(val message: String) : QuoteScreenState()
 * }
 * ```
 *
 * ## Trade-offs
 * - Using an interface (not sealed) allows states from different modules
 * - Each feature defines its own sealed hierarchy for exhaustive matching
 *
 * @see StateReducer for how states are produced from actions
 * @see Action for the events that trigger state changes
 */
interface State
