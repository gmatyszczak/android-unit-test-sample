package pl.gmat.news.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pl.gmat.news.common.DispatcherProvider
import pl.gmat.news.common.Result
import pl.gmat.news.common.SingleLiveEvent
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    val state = MutableLiveData<MainState>()
    val effect = SingleLiveEvent<MainEffect>()
    private val currentState = state.value ?: MainState()

    init {
        viewModelScope.loadNews()
        viewModelScope.refreshNews()
    }

    private fun CoroutineScope.loadNews() = launch(dispatcherProvider.main()) {
        repository.loadNews().collect {
            state.value = (currentState.copy(news = it))
        }
    }

    private fun CoroutineScope.refreshNews() = launch(dispatcherProvider.main()) {
        state.value = currentState.copy(isLoading = true)
        val result = repository.refreshNews()
        if (result is Result.Failure) {
            effect.value = MainEffect.ShowRefreshError(result.error)
        }
        state.value = currentState.copy(isLoading = false)
    }

    fun onNewsRefresh() = viewModelScope.refreshNews()

    fun onNewsClicked(news: News) {
        effect.value = MainEffect.ShowNews(news.id)
    }
}