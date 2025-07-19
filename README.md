# Khalid Podcast

A clean-architecture showcase built with **100% Kotlin + Jetpack Compose**.  
It demonstrates searchable, paginated audio content (podcasts, episodes, audiobooks, articles) with a modular, test-driven codebase.

---

## Solution Overview

### Layers & Modules

- **core** – pure domain layer (business models & use-cases).  
- **core-data** – data layer (Retrofit APIs, DTOs, Gson adapters, mappers, network helpers).  
- **core-ui** – design system (re-usable Compose widgets + app theme).  
- **feature-feed** – Feed screen with pull-to-refresh & endless scroll.  
- **feature-search** – Search screen with debounced queries.

> **Rule of thumb:** each module depends only on the one directly beneath it (UI → Domain → Data).  
> No DTOs leak upward—every network object is mapped into a domain model first (Clean Arch / SOLID).

---

## Type-Safe Rendering

1. Custom **Gson** adapters convert the raw `content_type` and `type` JSON fields into strongly-typed enums.
2. **FeedMapper** turns polymorphic JSON into one of four sealed classes:  
   `PodcastSection`, `EpisodeSection`, `AudioBookSection`, `AudioArticleSection`.
3. In Compose the UI renders each section with a simple `when(section) { … }`—no unsafe casts.

---

## Pagination & Search

- **Feed** observes `LazyListState.layoutInfo`; when the last visible index is within two items of the end it dispatches `FeedEvent.LoadNextPage` (VM-guarded to avoid duplicate calls).  
- **Search** keeps a debounced `MutableStateFlow` (200 ms, `distinctUntilChanged`) so network hits happen only after the user stops typing.

---

## Challenges & What I Did

- **No detailed UX spec, many layouts** – introduced neutral row components in `core-ui`  
  (`SquareRowView`, `BigSquareRowView`, `TwoLineGridRowView`). Adding a new layout = one new composable + one sealed subclass.
- **Polymorphic JSON** – mapper returns `null` for unknown `content_type`, silently skipping bad data instead of crashing.  
- **Flow exception transparency** – re-worked `NetworkUtilities.safeApiCall` to emit `Response.Error` inside the same `flow {}` builder.  
- **Date parsing on API 24** – fell back from `java.time` to `SimpleDateFormat` in `TimeFormatter`.

---

## Extra Files (kept for future work)

- `NetworkError.kt` – placeholder for richer error parsing.

---

## Unit-Test Highlights

- `FeedMapperTest` – verifies correct mapping for PODCAST / EPISODE / AUDIO_BOOK / AUDIO_ARTICLE + graceful skip on UNKNOWN.  
- `FeedRepositoryImplTest` – covers success & failure paths (`Response.Success`, `Response.Error`).  
- `SearchRepositoryImplTest` – mirrors Feed tests for search endpoints.  
- `FeedViewModelTest` – proves initial load, refresh, and pagination all preserve global section order.  
- `SearchViewModelTest` – ensures only **one** search call after rapid typing (200 ms debounce).

---

## Try It Out

Source code + full test suite: **<https://github.com/KhalidDev0/Khalid-Podcast>**

---

## AI Assistance Disclaimer

I used **ChatGPT (OpenAI)** solely to speed up documentation and generate boiler-plate (e.g. unit-test skeletons, Gson adapters).  
All architectural decisions, final code, and fixes were written & reviewed by me.
