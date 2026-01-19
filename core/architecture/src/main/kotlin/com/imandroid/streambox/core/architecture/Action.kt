package com.imandroid.streambox.core.architecture

/**
 * Base interface for all actions that can be dispatched to reducers.
 *
 * ## Pattern
 * Actions represent user intents or system events that should trigger
 * state changes. They are modeled as sealed classes per feature.
 *
 * ## Naming Convention
 * Actions should be named as commands or events:
 * - Commands: `Load`, `Refresh`, `Submit`, `SetContent`
 * - Events: `ContentLoaded`, `ErrorOccurred`
 *
 * ## Usage
 * ```kotlin
 * sealed class QuoteAction : Action {
 *     data object Load : QuoteAction()
 *     data class SetContent(val quote: Quote) : QuoteAction()
 *     data class SetError(val error: Throwable) : QuoteAction()
 * }
 * ```
 *
 * ## Best Practices
 * - Keep actions immutable (use data class/object)
 * - Include only the data needed for the state transition
 * - Avoid business logic in action definitions
 *
 * @see Reducer for how actions are processed
 * @see StateReducer for the base implementation
 */
interface Action
