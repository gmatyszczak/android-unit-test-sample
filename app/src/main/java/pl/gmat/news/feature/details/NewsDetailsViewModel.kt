package pl.gmat.news.feature.details

import pl.gmat.news.common.feature.BaseViewModel
import javax.inject.Inject

class NewsDetailsViewModel @Inject constructor() :
    BaseViewModel<NewsDetailsState, NewsDetailsEffect>() {

    override val initialState = NewsDetailsState()

}