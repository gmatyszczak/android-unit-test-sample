package pl.gmat.news.feature.main

sealed class MainEffect {
    data class ShowNews(val id: Int) : MainEffect()
    data class ShowRefreshError(val error: Throwable) : MainEffect()
}
