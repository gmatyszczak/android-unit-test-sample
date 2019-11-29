package pl.gmat.news.feature.news

sealed class NewsEffect {
    data class ShowNews(val id: Int) : NewsEffect()
    object ShowRefreshError : NewsEffect()
}
