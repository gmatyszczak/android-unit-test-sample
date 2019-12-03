package pl.gmat.news.feature.news

import kotlinx.coroutines.flow.Flow
import pl.gmat.news.common.Result
import pl.gmat.news.common.api.NewsService
import pl.gmat.news.common.apiCall
import pl.gmat.news.common.dao.NewsDao
import pl.gmat.news.common.model.News
import javax.inject.Inject

interface NewsRepository {

    fun loadNews(): Flow<List<News>>
    suspend fun refreshNews(): Result<List<News>>
}

class NewsRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val newsService: NewsService
) : NewsRepository {

    override fun loadNews(): Flow<List<News>> = newsDao.loadAll()

    override suspend fun refreshNews(): Result<List<News>> {
        val result = apiCall { newsService.loadAllNews() }
        if (result is Result.Success<List<News>>) {
            newsDao.insert(result.data)
        }
        return result
    }
}









































































//
//import kotlinx.coroutines.flow.Flow
//import pl.gmat.news.common.Result
//import pl.gmat.news.common.api.NewsService
//import pl.gmat.news.common.apiCall
//import pl.gmat.news.common.dao.NewsDao
//import pl.gmat.news.common.model.News
//import javax.inject.Inject
//
//interface NewsRepository {
//
//    fun loadNews(query: String): Flow<List<News>>
//    suspend fun refreshNews(): Result<List<News>>
//}
//
//class NewsRepositoryImpl @Inject constructor(
//    private val newsService: NewsService,
//    private val newsDao: NewsDao
//) : NewsRepository {
//
//    override fun loadNews(query: String) = if (query.isEmpty()) {
//        newsDao.loadAll()
//    } else {
//        newsDao.search(query)
//    }
//
//    override suspend fun refreshNews(): Result<List<News>> {
//        val result = apiCall { newsService.loadAllNews() }
//        if (result is Result.Success<List<News>>) {
//            newsDao.insert(result.data)
//        }
//        return result
//    }
//}