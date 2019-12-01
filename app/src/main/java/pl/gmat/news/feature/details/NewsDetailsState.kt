package pl.gmat.news.feature.details

import pl.gmat.news.common.model.Comment
import pl.gmat.news.common.model.News

data class NewsDetailsState(
    val news: News,
    val comments: List<Comment> = emptyList(),
    val showLoader: Boolean = false,
    val showComments: Boolean = false
)