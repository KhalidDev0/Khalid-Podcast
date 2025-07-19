# Khalid Podcast 🎧

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2-blueviolet?logo=kotlin\&logoColor=white)](#)
[![Compose](https://img.shields.io/badge/Jetpack_Compose-1.7-green?logo=android\&logoColor=white)](#)
[![CI](https://img.shields.io/badge/CI-GitHub_Actions-brightgreen?logo=githubactions\&logoColor=white)](#)

A **production-grade Android app** searches audio content (podcasts, episodes, audiobooks, articles):

* Clean Architecture & SOLID
* Jetpack Compose + Material 3
* K2 compiler, Kotlin Flow & Coroutines
* Modular Gradle build (AGP 8.1, Version Catalogs)
* Test-driven development

---

## 📐 Module map

```
app (NavHost + Hilt entry)
│
├─ core            ← Pure domain layer (models, use-cases)
├─ core-data       ← Retrofit APIs, DTO↔domain mappers, safeApiCall
├─ core-ui         ← Design-system: theme + reusable Composables
│
├─ feed            ← Infinite scroll + pull-to-refresh
└─ search          ← Debounced search with result states
```

> **Rule:** each module depends **only** on the one directly beneath it (UI → Domain → Data).
> No DTOs touch the UI.

---

## ⚙️ Architecture highlights

| Concern                           | Implementation                                                                | Why it matters                               |
| --------------------------------- | ----------------------------------------------------------------------------- | -------------------------------------------- |
| **Separation of concerns**        | Clear domain / data / ui boundaries                                           | Easier testing & parallel work               |
| **Type-safe polymorphic mapping** | `ContentTypeDto` + sealed `Section` hierarchy                                 | UI does simple `when(section)` with no casts |
| **Resilience**                    | `NetworkUtilities.safeApiCall` collapses exceptions → `Response.Error`        | One error path, Flow never crashes           |
| **Performance**                   | • Pagination triggers 2-items-before-end<br>• Search input `debounce(200 ms)` | Saves battery & bandwidth                    |
| **Build times**                   | K2 compiler, small modules, centralized versions                              | \<5s incremental build                       |
| **App size**                      | R8 + resource shrinking, single `OkHttp`                                      | Release APK ≈ 3 MB lighter than monolith     |

---

## 🧑‍💻 Example snippets

### 1 · Value-object domain model

```kotlin
@JvmInline
value class EpisodeId(val value: String)
```

### 2 · Use-case (pure function)

```kotlin
class GetFeedUseCase @Inject constructor(
    private val repo: FeedRepository
) {
    operator fun invoke(page: Int): Flow<Response<List<Section>>> =
        repo.feed(page)
}
```

### 3 · MVI ViewModel

```kotlin
@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeed: GetFeedUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FeedState())
    val state: StateFlow<FeedState> = _state

    init { load() }

    fun onEvent(e: FeedEvent) = viewModelScope.launch {
        when (e) {
            FeedEvent.Refresh      -> load(force = true)
            FeedEvent.LoadNextPage -> if (!_state.value.isLoadingMore) loadNext()
        }
    }
    ...
}
```

### 4 · Reusable Compose row

```kotlin
@Composable
fun EpisodeRow(ui: EpisodeUi) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { ui.onClick() }
            .padding(16.dp)
    ) {
        AsyncImage(
            model = ui.coverUrl,
            contentDescription = null,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(ui.title, style = MaterialTheme.typography.titleMedium)
            Text(ui.duration, style = MaterialTheme.typography.bodySmall)
        }
    }
}
```

---

## 🧪 Test coverage

* **Mapper tests** – every `content_type` → section branch, plus unknown-type skip
* **Repository tests** – success & failure (`Response.Success`, `Response.Error`)
* **ViewModel tests** – verifies pagination ordering + debounced search fires once

---

## 🚀 Running locally

```bash
git clone https://github.com/KhalidDev0/Khalid-Podcast.git
cd Khalid-Podcast
./gradlew :app:installDebug     # or open with Android Studio Hedgehog
```

---

## 🗺️ Roadmap

* Offline cache with Room + Paging 3
* Baseline Profile for faster cold-start
* Compose-Multiplatform desktop preview
* Granular `NetworkError` handling

---

## 📝 AI disclaimer

*Documentation & boiler-plate were accelerated with **ChatGPT (OpenAI)**.*
All architecture decisions, production code, and reviews were authored by **Khalid Almuqrin**.

---

Happy listening! Feel free to ⭐ star, fork, or open an issue.
