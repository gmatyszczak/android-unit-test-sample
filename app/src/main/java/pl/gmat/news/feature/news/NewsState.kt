package pl.gmat.news.feature.news

import pl.gmat.news.common.model.News

data class NewsState(
    val news: List<News> = emptyList(),
    val isLoading: Boolean = false,
    val isEmptyVisible: Boolean = false
)