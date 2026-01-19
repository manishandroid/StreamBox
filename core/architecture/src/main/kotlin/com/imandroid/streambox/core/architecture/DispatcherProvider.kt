package com.imandroid.streambox.core.architecture

import kotlin.coroutines.CoroutineContext

/**
 * Abstraction over coroutine dispatchers for testability.
 *
 * ## Pattern
 * All coroutine-using classes receive dispatchers via this
 * interface rather than using `Dispatchers` directly. This allows tests
 * to substitute with [TestDispatcherProvider] for deterministic execution.
 *
 * ## Usage in Production
 * ```kotlin
 * class DispatcherProviderImpl : DispatcherProvider {
 *     override val main: CoroutineContext = Dispatchers.Main
 *     override val io: CoroutineContext = Dispatchers.IO
 *     override val default: CoroutineContext = Dispatchers.Default
 * }
 * ```
 *
 * ## Usage in Tests
 * ```kotlin
 * class TestDispatcherProvider(
 *     testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
 * ) : DispatcherProvider {
 *     override val main: CoroutineContext = testDispatcher
 *     override val io: CoroutineContext = testDispatcher
 *     override val default: CoroutineContext = testDispatcher
 * }
 * ```
 *
 * ## Dispatcher Guidelines
 *
 * | Dispatcher | Use For |
 * |------------|---------|
 * | [main] | UI updates, state emissions |
 * | [io] | Network calls, database, file I/O |
 * | [default] | CPU-intensive work, JSON parsing |
 *
 * ## Dependency Injection
 * Provide via Hilt as a Singleton:
 * ```kotlin
 * @Module
 * @InstallIn(SingletonComponent::class)
 * object DispatcherModule {
 *     @Provides
 *     @Singleton
 *     fun provideDispatcherProvider(): DispatcherProvider =
 *         DispatcherProviderImpl()
 * }
 * ```
 */
interface DispatcherProvider {

    /**
     * Dispatcher for UI-related work.
     *
     * Use for:
     * - State updates that will be observed by Compose
     * - Anything that touches Android UI
     *
     * Maps to `Dispatchers.Main` in production.
     */
    val main: CoroutineContext

    /**
     * Dispatcher for I/O-bound work.
     *
     * Use for:
     * - Network requests
     * - Database operations
     * - File read/write
     *
     * Maps to `Dispatchers.IO` in production.
     * Optimized for blocking I/O with a larger thread pool.
     */
    val io: CoroutineContext

    /**
     * Dispatcher for CPU-intensive work.
     *
     * Use for:
     * - JSON parsing
     * - Data transformation
     * - Sorting/filtering large lists
     * - State reduction (in [StateReducer])
     *
     * Maps to `Dispatchers.Default` in production.
     * Limited to number of CPU cores.
     */
    val default: CoroutineContext
}
