package com.imandroid.streambox.core.architecture

/**
 * Transforms data between architectural layers.
 *
 * ## Pattern
 * Mappers enforce strict layer boundaries:
 * - **Data Layer:** `NetworkModel → DomainModel`
 * - **Presentation Layer:** `DomainModel → UiState`
 *
 * This prevents layer coupling and makes each layer independently testable.
 *
 * ## Naming Convention
 * Follow the pattern: `{Source}To{Target}Mapper`
 *
 * Examples:
 * - `ContentNetToContentMapper` (network → domain)
 * - `ContentToUiStateMapper` (domain → presentation)
 * - `ProfileEntityToProfileMapper` (persistence → domain)
 *
 * ## Usage
 * ```kotlin
 * class ContentNetToContentMapper : Mapper<ContentNet, Content> {
 *     override fun map(input: ContentNet): Content = Content(
 *         id = input.id,
 *         title = input.title,
 *         category = input.category
 *     )
 * }
 * ```
 *
 * ## Testing
 * Mappers are pure functions and should have comprehensive unit tests.
 * Each edge case in the transformation should be tested.
 *
 * @param IN The input type to transform from
 * @param OUT The output type to transform to
 */
interface Mapper<IN, OUT> {

    /**
     * Transforms input to output.
     *
     * This function should be:
     * - Pure: same input always produces same output
     * - Side-effect free: no network calls, no state changes
     * - Total: handles all possible input values without throwing
     *
     * @param input The source data to transform
     * @return The transformed result
     */
    fun map(input: IN): OUT
}
