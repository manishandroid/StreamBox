# StreamBox

**Production Pattern Learning App**

StreamBox is a learning-focused Android application that demonstrates production-grade architecture patterns used in large-scale streaming apps. Each Git branch builds on a shared foundation, progressively teaching one concept at a time.

---

## 🎯 Purpose

This repository serves as:

1. **Learning Reference** — Practice production Android patterns in isolation
2. **Pattern Catalog** — Documented implementations of scalable architecture
3. **Progressive Curriculum** — Branches ordered by complexity and dependency
4. **Interview Prep** — Real-world examples of Clean Architecture, MVI, and testing

---

## 🏗️ Architecture Overview

StreamBox follows a **Clean Architecture** approach with clear layer separation:

```
┌─────────────────────────────────────────────────────────────┐
│                         app/                                │
│   Application, MainActivity, DI modules                    │
├─────────────────────────────────────────────────────────────┤
│                      features/                              │
│   home/  (placeholder on main, features on branches)        │
│   Each feature: ui/ → viewmodel/ → domain/ → data/          │
├─────────────────────────────────────────────────────────────┤
│                        core/                                │
│   architecture/  State, Action, Reducer, UseCase, Mapper    │
│   designsystem/  AppTheme, colors, typography, spacing      │
│   ui/            Shared UI components                       │
│   testing/       TestDispatcherProvider                     │
└─────────────────────────────────────────────────────────────┘
```

### Core Principles

| Principle | Implementation |
|-----------|----------------|
| **Unidirectional Data Flow** | Actions → Reducer → State → UI |
| **Layer Separation** | Data ↔ Domain ↔ Presentation via interfaces |
| **Testability First** | Dispatcher injection, pure reducers, mockable use cases |
| **Composition over Inheritance** | Interfaces, delegation, function composition |

---

## 📦 Module Structure

### `core/architecture`
Foundational primitives for state management:

- **`State`** — Base interface for UI states
- **`Action`** — Base interface for reducer actions
- **`Reducer`** — Contract for state containers
- **`StateReducer`** — Base implementation with thread-safe updates
- **`UseCase`** — Sealed interface with 4 variants (Suspending, SuspendingWithParam, Streaming, StreamingWithParam)
- **`Mapper`** — Transform data between layers
- **`DispatcherProvider`** — Testable coroutine dispatcher abstraction

### `core/designsystem`
Design system tokens and theme:

- **`AppTheme`** — Access colors, typography, spacers
- **`AppColors`** — Semantic color tokens (background, text, brand, status)
- **`AppTypography`** — Text style tokens
- **`AppSpacers`** — Spacing tokens (xs, sm, md, lg, xl, xxl)
- **`StreamBoxTheme`** — Theme provider composable

### `core/ui`
Shared UI components:

- **`LoadingIndicator`** — Standard loading spinner

### `core/testing`
Test utilities:

- **`TestDispatcherProvider`** — Deterministic coroutine execution in tests

### `features/home`
Placeholder feature (on `main`):

- Static screen demonstrating design system usage
- Will evolve in feature branches

---

## 🌿 Branch Strategy

Each branch adds **exactly one concept** on top of `main`:

| Order | Branch | Focus |
|-------|--------|-------|
| 1 | `main` | Base app (this branch) |
| 2 | `feature/reducer-basics` | State + Action + Reducer for home screen |
| 3 | `feature/use-case-layer` | Domain layer with UseCase pattern |
| 4 | `feature/mapper-pattern` | Layer transformation with Mapper |
| 5 | `feature/repository-pattern` | Data layer abstraction |
| 6 | `feature/network-integration` | Ktor + real API |
| 7 | `feature/persistence-datastore` | DataStore-backed persistence |
| 8 | `feature/navigation-flow` | Multi-screen navigation |
| 9 | `feature/analytics-events` | Event tracking infrastructure |
| 10 | `feature/unit-testing` | Reducer, UseCase, ViewModel tests |
| 11 | `feature/snapshot-testing` | Paparazzi UI tests |

### Branch Rules

- **Additive only** — Never remove code from `main`, only add
- **Single focus** — One pattern per branch
- **Documented** — Each branch has its own README section
- **Self-contained** — Compiles and runs independently

---

## 🧪 Testing Philosophy

StreamBox follows production-grade testing strategy:

### Unit Tests
- **Reducers** — Pure functions, test state transitions
- **Mappers** — Input/output validation
- **Use Cases** — Mock repository, verify orchestration
- **ViewModels** — Mock use cases, verify state flow

### Snapshot Tests (future branch)
- Paparazzi for UI regression testing
- No emulator required

### Test Infrastructure
```kotlin
// TestDispatcherProvider for deterministic tests
class HomeReducerTest {
    private val dispatcherProvider = TestDispatcherProvider()
    private val reducer = HomeReducer(dispatcherProvider)

    @Test
    fun `Load action transitions to Loading state`() = runTest {
        reducer.update(HomeAction.Load)
        assertEquals(HomeState.Loading, reducer.state.value)
    }
}
```

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Android SDK 34

### Build & Run

```bash
# Clone the repository
git clone https://github.com/user/StreamBox.git
cd StreamBox

# Build
./gradlew assembleDebug

# Run unit tests
./gradlew test
```

### Open in Android Studio

1. Open Android Studio
2. File → Open → Select `StreamBox` directory
3. Wait for Gradle sync
4. Run on emulator or device

---

## 📖 Architecture Patterns Reference

Each pattern in StreamBox is derived from production streaming apps:

| Pattern | Location |
|---------|----------|
| StateReducer | `core/architecture/` |
| State/Action | `core/architecture/` |
| UseCase | `core/architecture/` |
| Mapper | `core/architecture/` |
| DispatcherProvider | `core/architecture/` |
| AppTheme | `core/designsystem/` |
| TestDispatcherProvider | `core/testing/` |

---

## 📂 Project Structure

```
StreamBox/
├── app/                          # Application module
│   ├── src/main/
│   │   ├── kotlin/.../
│   │   │   ├── StreamBoxApplication.kt
│   │   │   ├── MainActivity.kt
│   │   │   └── di/AppModule.kt
│   │   ├── res/
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
│
├── core/
│   ├── architecture/             # Pure Kotlin module
│   │   └── src/main/kotlin/.../
│   │       ├── State.kt
│   │       ├── Action.kt
│   │       ├── Reducer.kt
│   │       ├── StateReducer.kt
│   │       ├── UseCase.kt
│   │       ├── Mapper.kt
│   │       └── DispatcherProvider.kt
│   │
│   ├── designsystem/             # Android library
│   │   └── src/main/kotlin/.../theme/
│   │       ├── AppTheme.kt
│   │       ├── AppColors.kt
│   │       ├── AppTypography.kt
│   │       └── AppSpacers.kt
│   │
│   ├── ui/                       # Android library
│   │   └── src/main/kotlin/.../components/
│   │       └── LoadingIndicator.kt
│   │
│   └── testing/                  # Pure Kotlin module
│       └── src/main/kotlin/.../
│           └── TestDispatcherProvider.kt
│
├── features/
│   └── home/                     # Android library
│       └── src/main/kotlin/.../ui/
│           └── HomeScreen.kt
│
├── gradle/
│   ├── libs.versions.toml        # Version catalog
│   └── wrapper/
│
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

---

## 🔧 Dependencies

| Dependency | Version | Purpose |
|------------|---------|---------|
| Kotlin | 2.0.0 | Language |
| Compose BOM | 2024.02.00 | UI toolkit |
| Hilt | 2.50 | Dependency injection |
| Coroutines | 1.8.0 | Async operations |
| MockK | 1.13.9 | Testing mocks |

---

## 📜 License

This is a learning project for educational purposes.

---

## 🤝 Contributing

1. Checkout the appropriate feature branch
2. Make focused, single-purpose changes
3. Ensure tests pass
4. Update documentation

## Visitor Count
<div align="center">
        <img alt="Visitor Stats" 
            src="https://widgetbite.com/stats/manishandroid"/>  
    </div>

---

**Branch: `main`** — Base app with architecture primitives only.
