package pl.gmat.news.feature.main

data class MainState(
    val news: List<News> = emptyList(),
    val isLoading: Boolean = false
)