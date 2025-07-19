Solution summary
Architecture overview
core – pure Kotlin domain layer (business models, use-cases).


core-data – data layer (Retrofit APIs, DTOs, Gson adapters, mappers, network helpers).


core-ui – design-system: reusable Compose widgets, theme.


feed feature – feed screen (pull-to-refresh + endless scroll).


search feature – search screen.


Key rule: each module depends only on the layer directly below it (UI → Domain → Data ). No DTOs leak upward – everything is mapped to domain models first (SOLID / Clean Arch).
Type-safe content rendering
Custom Gson adapters turn raw JSON fields content_type and type into enum values (ContentTypeDto, SectionLayoutDto).


FeedMapper converts polymorphic JSON into one of four sealed subclasses
 PodcastSection, EpisodeSection, AudioBookSection, AudioArticleSection.
 Compose renders a section with a simple when(section) – no unsafe casts.
Pagination and search
Feed observes LazyListState.layoutInfo. When the last visible index is within two items of the end it dispatches FeedEvent.LoadNextPage, guarded inside the VM to avoid duplicate loads.


Search keeps a MutableStateFlow debounced at 200 ms and distinctUntilChanged() before firing the use-case, so network calls happen only after the user stops typing.



Challenges and how I handled them
No UX spec / many layouts – created neutral row components in core-ui (SquareRowView, BigSquareRowView, TwoLineGridRowView). Feature code feeds them simple UI-models, so adding a new layout is one composable + one sealed subclass.


Polymorphic JSON – mapper returns null for unknown content_type, so bad data is skipped gracefully instead of crashing.


Flow exception transparency – adjusted NetworkUtilities.safeApiCall to emit Response.Error inside the same flow{} builder.


Date parsing on API 24+ – replaced java.time with SimpleDateFormat fallback in TimeFormatter.



Extra files currently unused (kept intentionally)
NetworkError.kt – placeholder for richer error parsing in the future.



Unit-test highlights
FeedMapperTest – verifies mapping for PODCAST / EPISODE / AUDIO_BOOK / AUDIO_ARTICLE plus graceful skip on UNKNOWN.


FeedRepositoryImplTest – covers success and failure paths (Response.Success, Response.Error).


SearchRepositoryImplTest – mirrors Feed tests for search endpoints.


FeedViewModelTest – proves initial load, refresh, and pagination all preserve global ordering of sections.


SearchViewModelTest– proves debouncing work as expected after 200ms with only one search call for rapid typing

AI assistance Disclaimer
ChatGPT (OpenAI) was used to speed up documentation and generate boiler-plate (unit-test skeletons, Gson adapters). 
All architectural decisions, final code, and fixes were written and reviewed by me.
