package pl.gmat.news.feature.main

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.gmat.news.common.Result
import pl.gmat.news.common.apiCall
import pl.gmat.news.feature.main.api.NewsService
import pl.gmat.news.feature.main.dao.NewsDao
import javax.inject.Inject

interface NewsRepository {

    suspend fun refreshNews(): Result<List<News>>
    fun loadNews(): Flow<List<News>>
}

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val newsDao: NewsDao,
    private val mapper: NewsMapper
) : NewsRepository {

    override suspend fun refreshNews(): Result<List<News>> {
        val result = apiCall { newsService.loadNews() }
        if (result is Result.Success<List<News>>) {
            val news = result.data ?: emptyList()
            newsDao.insert(news.map { mapper.toNewsEntity(it) })
        }
        return result
    }

    override fun loadNews() =
        newsDao.loadAll().map { newsEntities ->
            newsEntities.map { mapper.toNews(it) }
        }
}