package pl.gmat.news.feature.news

data class NewsState(
    val news: List<News> = emptyList(),
    val isLoading: Boolean = false
)