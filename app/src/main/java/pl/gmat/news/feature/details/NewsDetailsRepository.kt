package pl.gmat.news.feature.details

import pl.gmat.news.common.Result
import pl.gmat.news.common.api.NewsService
import pl.gmat.news.common.apiCall
import pl.gmat.news.common.model.Comment
import javax.inject.Inject

interface NewsDetailsRepository {

    suspend fun loadComments(newsId: Int): List<Comment>
}

class NewsDetailsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
) : NewsDetailsRepository {

    override suspend fun loadComments(newsId: Int): List<Comment> {
        val result = apiCall { newsService.loadComments(newsId) }
        return if (result is Result.Success<List<Comment>>) {
            result.data
        } else {
            emptyList()
        }
    }
}