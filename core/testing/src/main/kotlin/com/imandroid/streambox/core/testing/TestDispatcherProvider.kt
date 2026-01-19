package com.imandroid.streambox.core.testing

import com.imandroid.streambox.core.architecture.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Test implementation of [DispatcherProvider] for unit tests.
 *
 * ## Pattern
 * All tests use a test dispatcher provider to ensure
 * deterministic execution of coroutines. This is critical for
 * testing reducers, use cases, and ViewModels.
 *
 * ## Usage
 *
 * ### Basic Usage (Unconfined)
 * ```kotlin
 * class QuoteReducerTest {
 *     private val dispatcherProvider = TestDispatcherProvider()
 *     private val reducer = QuoteReducer(dispatcherProvider)
 *
 *     @Test
 *     fun `when Load action then state is Loading`() = runTest {
 *         reducer.update(QuoteAction.Load)
 *         assertEquals(QuoteScreenState.Loading, reducer.state.value)
 *     }
 * }
 * ```
 *
 * ### With StandardTestDispatcher (for timing control)
 * ```kotlin
 * class ViewModelTest {
 *     private val testDispatcher = StandardTestDispatcher()
 *     private val dispatcherProvider = TestDispatcherProvider(testDispatcher)
 *
 *     @Test
 *     fun `test with timing`() = runTest(testDispatcher) {
 *         // Use advanceUntilIdle() to control execution
 *         viewModel.load()
 *         advanceUntilIdle()
 *         // Assert results
 *     }
 * }
 * ```
 *
 * ## Trade-offs
 *
 * **Unconfined (default):**
 * - Executes immediately, no need for advanceUntilIdle()
 * - Simpler test code
 * - May hide timing-related bugs
 *
 * **StandardTestDispatcher:**
 * - Explicit control over execution timing
 * - Better for testing delays, timeouts
 * - More verbose test code
 *
 * @param testDispatcher The dispatcher to use for all contexts.
 *                       Defaults to Unconfined for immediate execution.
 */
class TestDispatcherProvider(
    testDispatcher: CoroutineDispatcher = Dispatchers.Unconfined
) : DispatcherProvider {

    override val main: CoroutineContext = testDispatcher
    override val io: CoroutineContext = testDispatcher
    override val default: CoroutineContext = testDispatcher
}
