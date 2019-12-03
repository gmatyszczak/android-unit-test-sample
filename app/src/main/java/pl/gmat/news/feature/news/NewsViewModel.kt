package pl.gmat.news.feature.news

import pl.gmat.news.common.feature.BaseViewModel
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : BaseViewModel<NewsState, NewsEffect>() {

    override val initialState = NewsState()
}






























































































//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.asFlow
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.FlowPreview
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.flow.collect
//import kotlinx.coroutines.flow.debounce
//import kotlinx.coroutines.launch
//import pl.gmat.news.common.Result
//import pl.gmat.news.common.feature.BaseViewModel
//import pl.gmat.news.common.model.News
//import javax.inject.Inject
//
//@FlowPreview
//class NewsViewModel @Inject constructor(
//    private val repository: NewsRepository
//) : BaseViewModel<NewsState, NewsEffect>() {
//
//    override val initialState = NewsState()
//    val searchQueryLiveData = MutableLiveData<String>()
//
//    private lateinit var currentNewsJob: Job
//
//    init {
//        viewModelScope.apply {
//            loadNews()
//            refreshNews()
//            observeSearchText()
//        }
//    }
//
//    private fun CoroutineScope.loadNews(query: String = "") {
//        currentNewsJob = launch {
//            repository.loadNews(query).collect {
//                state.value = currentState.copy(
//                    news = it,
//                    isEmptyVisible = it.isEmpty()
//                )
//            }
//        }
//    }
//
//    private fun CoroutineScope.refreshNews() = launch {
//        state.value = currentState.copy(isLoading = true)
//        val result = repository.refreshNews()
//        if (result is Result.Failure) {
//            effect.value = NewsEffect.ShowRefreshError
//        }
//        state.value = currentState.copy(isLoading = false)
//    }
//
//    private fun CoroutineScope.observeSearchText() = launch {
//        searchQueryLiveData.asFlow()
//            .debounce(300)
//            .collect {
//                currentNewsJob.cancel()
//                loadNews(it)
//            }
//    }
//
//    fun onNewsRefresh() = viewModelScope.refreshNews()
//
//    fun onNewsClicked(news: News) {
//        effect.value = NewsEffect.ShowNewsDetails(news)
//    }
//}