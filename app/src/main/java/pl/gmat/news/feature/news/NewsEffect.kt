package pl.gmat.news.feature.news

sealed class NewsEffect {
    data class ShowNews(val id: Int) : NewsEffect()
    data class ShowRefreshError(val error: Throwable) : NewsEffect()
}
