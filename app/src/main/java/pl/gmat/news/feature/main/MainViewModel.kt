package pl.gmat.news.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import pl.gmat.news.common.SingleLiveEvent
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    val state = MutableLiveData<MainState>()
    val effect = SingleLiveEvent<MainEffect>()
    private val currentState
        get() = state.value ?: MainState()
    private val compositeDisposable = CompositeDisposable()

    init {
        loadNews()
        refreshNews()
    }

    private fun loadNews() {
        repository.loadNews()
            .subscribe {
                state.value = currentState.copy(news = it)
            }
            .addTo(compositeDisposable)
    }

    private fun refreshNews() {
        state.value = currentState.copy(isLoading = true)
        repository.refreshNews().subscribeBy(
            onComplete = {
                state.value = currentState.copy(isLoading = false)
            }, onError = {
                effect.value = MainEffect.ShowRefreshError(it)
                state.value = currentState.copy(isLoading = false)
            }
        ).addTo(compositeDisposable)
    }

    fun onNewsRefresh() = refreshNews()

    fun onNewsClicked(news: News) {
        effect.value = MainEffect.ShowNews(news.id)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}