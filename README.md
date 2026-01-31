# StreamBox - Image Loading

This branch adds image loading end-to-end for the Home grid. The goal is to
ensure image URLs flow through all layers and render in UI using shared
infrastructure, without touching business logic.

## Why Image Loading Is UI Infrastructure
Images are a presentation concern. Keeping loading logic in UI and design
system layers prevents networking or domain code from depending on UI details
while still enabling caching and consistent rendering.

## What Changed
- Image URLs now flow from API DTOs to Domain and UI models
- Shared image composable added in `core/ui` for consistent loading and fallback
- App-level image loader configured with caching defaults

## Composables to Start With
- `core/ui/components/StreamBoxImage.kt`
- `features/home/ui/HomeScreen.kt` (tile usage)

## Notes
- Reducers, use cases, and repositories remain unchanged
- Mappers pass URLs only; UI decides how to render images
- Layout remains the same; tiles now show real images
- The approach mirrors a reference project that centralizes image loading and UI rendering

## Demo Recording
![Home image loading demo](assets/image-loading-home-complete.gif)

[Download full HD video](assets/image-loading-home-complete.mp4?raw=1)

