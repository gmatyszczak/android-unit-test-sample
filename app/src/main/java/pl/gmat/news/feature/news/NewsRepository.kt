package pl.gmat.news.feature.news

import kotlinx.coroutines.flow.Flow
import pl.gmat.news.common.Result
import pl.gmat.news.common.apiCall
import pl.gmat.news.feature.news.api.NewsService
import pl.gmat.news.feature.news.dao.NewsDao
import javax.inject.Inject

interface NewsRepository {

    suspend fun refreshNews(): Result<Nothing>
    fun loadNews(): Flow<List<News>>
}

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val newsDao: NewsDao
) : NewsRepository {

    override suspend fun refreshNews() =
        when (val result = apiCall { newsService.loadNews() }) {
            is Result.Success<List<News>> -> {
                newsDao.insert(result.data ?: emptyList())
                Result.Success<Nothing>()
            }
            is Result.Failure -> result
        }

    override fun loadNews() = newsDao.loadAll()
}