# StreamBox - UI Composition Improvements

This branch refines the Home screen composition with a top bar, dynamic genre
tabs, a structured grid section, and a bottom navigation bar. The goal is to
mirror real streaming app screen composition without changing architecture or
data flow.

## Why This Structure
Streaming home screens rely on predictable layering: a stable top bar, a
discoverable filter row, a scrollable content grid, and a persistent bottom
bar. This keeps navigation visible while content remains the focus.

## What Changed
- Top bar added to anchor the screen identity
- Genre tabs rendered dynamically from UI state
- Grid content filtered by selected genre (UI-only)
- Bottom bar added with static tabs and visual selection state

## Composables to Start With
- `features/home/ui/HomeScreen.kt`
- `HomeTopBar`, `GenreTabRow`, `HomeGrid`, `HomeBottomBar`

## Notes
- No reducer, use case, repository, or mediator changes
- Filtering is UI-driven and lightweight
- Layering order is explicit: top bar → tabs → grid → bottom bar

---

# StreamBox - Starting Screen Update

This branch redesigns the starting screen so it explains what StreamBox is,
why it exists, and how developers should use it. The goal is to add clear
context before the user enters the Home screen.

## Why This Screen Matters
StreamBox is a learning-focused app. Without context, the entry screen looks
like a basic launcher. This update turns it into an introduction that sets
expectations and points developers to where deeper information lives.

## What Changed
- Starting screen now explains the app purpose and architecture focus
- Clear references to the repository and blog posts (text only)
- Primary call-to-action updated to “Explore App”
- Layout is scrollable and content-first

## Composable to Start With
- `features/home/ui/HomeScreen.kt`
- `HomeIdle` (starting screen content)

## Notes
- No Home screen changes
- No reducer, use case, repository, or mediator changes
- This screen is presentational only

