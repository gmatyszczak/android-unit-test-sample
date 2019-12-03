package pl.gmat.news.feature.news

import pl.gmat.news.common.model.News

sealed class NewsEffect {
    data class ShowNewsDetails(val news: News) : NewsEffect()
    object ShowRefreshError : NewsEffect()
}
