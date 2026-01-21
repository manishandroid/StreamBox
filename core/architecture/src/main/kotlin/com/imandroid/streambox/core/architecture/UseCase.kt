package com.imandroid.streambox.core.architecture

import kotlinx.coroutines.flow.Flow

/**
 * Encapsulates a single business operation.
 *
 * ## Pattern
 * Use cases are the API between presentation and domain layers.
 * ViewModels never access repositories directly—they go through use cases.
 *
 * This provides:
 * - Single responsibility: one use case = one operation
 * - Testability: easy to mock in ViewModel tests
 * - Reusability: same use case can be used by multiple ViewModels
 *
 * ## Variants
 *
 * | Type | Parameters | Return | Use When |
 * |------|------------|--------|----------|
 * | [Suspending] | None | `R` | Simple async operation |
 * | [SuspendingWithParam] | `P` | `R` | Async operation with input |
 * | [Streaming] | None | `Flow<R>` | Observe data changes |
 * | [StreamingWithParam] | `P` | `Flow<R>` | Observe with filter/params |
 *
 * ## Naming Convention
 * Use verb phrases: `Get`, `Create`, `Update`, `Delete`, `Observe`
 *
 * Examples:
 * - `GetContentUseCase` → returns `Result<Content>`
 * - `ObserveSettingsUseCase` → returns `Flow<Settings>`
 * - `CreateProfileUseCase` → takes `ProfileData`, returns `Result<Profile>`
 *
 * ## Return Types
 * Always wrap fallible operations in `Result<T>`:
 * ```kotlin
 * interface GetContentUseCase : UseCase.Suspending<Result<Content>>
 * ```
 *
 * This makes error handling explicit at the call site.
 */
sealed interface UseCase {

    /**
     * Suspending operation without parameters.
     *
     * Use for simple async operations like fetching current state.
     *
     * Example:
     * ```kotlin
     * interface GetCurrentUserUseCase : UseCase.Suspending<Result<User>>
     *
     * class GetCurrentUserUseCaseImpl(
     *     private val repository: UserRepository
     * ) : GetCurrentUserUseCase {
     *     override suspend fun invoke(): Result<User> =
     *         repository.getCurrentUser()
     * }
     * ```
     */
    interface Suspending<R> : UseCase {
        suspend operator fun invoke(): R
    }

    /**
     * Suspending operation with parameters.
     *
     * Use when the operation requires input data.
     *
     * Example:
     * ```kotlin
     * interface CreateProfileUseCase :
     *     UseCase.SuspendingWithParam<ProfileData, Result<Profile>>
     *
     * class CreateProfileUseCaseImpl(
     *     private val repository: ProfileRepository
     * ) : CreateProfileUseCase {
     *     override suspend fun invoke(param: ProfileData): Result<Profile> =
     *         repository.create(param)
     * }
     * ```
     */
    interface SuspendingWithParam<P, R> : UseCase {
        suspend operator fun invoke(param: P): R
    }

    /**
     * Reactive stream without parameters.
     *
     * Use for observing data that changes over time.
     *
     * Example:
     * ```kotlin
     * interface ObserveSettingsUseCase : UseCase.Streaming<Settings>
     *
     * class ObserveSettingsUseCaseImpl(
     *     private val repository: SettingsRepository
     * ) : ObserveSettingsUseCase {
     *     override fun invoke(): Flow<Settings> =
     *         repository.observeSettings()
     * }
     * ```
     */
    interface Streaming<R> : UseCase {
        operator fun invoke(): Flow<R>
    }

    /**
     * Reactive stream with parameters.
     *
     * Use when the stream needs configuration or filtering.
     *
     * Example:
     * ```kotlin
     * interface SearchContentUseCase :
     *     UseCase.StreamingWithParam<SearchQuery, List<Content>>
     *
     * class SearchContentUseCaseImpl(
     *     private val repository: ContentRepository
     * ) : SearchContentUseCase {
     *     override fun invoke(param: SearchQuery): Flow<List<Content>> =
     *         repository.search(param.query)
     *             .debounce(300)
     *             .distinctUntilChanged()
     * }
     * ```
     */
    interface StreamingWithParam<P, R> : UseCase {
        operator fun invoke(param: P): Flow<R>
    }
}
