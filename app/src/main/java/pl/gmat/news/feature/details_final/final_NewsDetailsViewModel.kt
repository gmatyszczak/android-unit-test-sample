package pl.gmat.news.feature.details_final

//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//import pl.gmat.news.common.feature.BaseViewModel
//import pl.gmat.news.common.model.News
//import javax.inject.Inject
//
//class NewsDetailsViewModel @Inject constructor(
//    private val news: News,
//    private val repository: NewsDetailsRepository
//) : BaseViewModel<NewsDetailsState, NewsDetailsEffect>() {
//
//    override val initialState = NewsDetailsState(news = news)
//
//    init {
//        state.value = initialState
//        viewModelScope.loadComments()
//    }
//
//    private fun CoroutineScope.loadComments() = launch {
//        state.value = initialState.copy(showLoader = true)
//        val comments = repository.loadComments(news.id)
//        state.value = currentState.copy(
//            comments = comments,
//            showLoader = false,
//            showComments = comments.isNotEmpty()
//        )
//    }
//}