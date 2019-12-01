package pl.gmat.news.feature.news

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pl.gmat.news.common.Result
import pl.gmat.news.common.feature.BaseViewModel
import pl.gmat.news.common.model.News
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : BaseViewModel<NewsState, NewsEffect>() {

    override val initialState = NewsState()

    init {
        viewModelScope.loadNews()
        viewModelScope.refreshNews()
    }

    private fun CoroutineScope.loadNews() = launch {
        repository.loadNews().collect {
            state.value = currentState.copy(news = it)
        }
    }

    private fun CoroutineScope.refreshNews() = launch {
        state.value = currentState.copy(isLoading = true)
        val result = repository.refreshNews()
        if (result is Result.Failure) {
            effect.value = NewsEffect.ShowRefreshError
        }
        state.value = currentState.copy(isLoading = false)
    }

    fun onNewsRefresh() = viewModelScope.refreshNews()

    fun onNewsClicked(news: News) {
        effect.value = NewsEffect.ShowNews(news.id)
    }
}