package pl.gmat.news.feature.news.api

import pl.gmat.news.feature.news.News
import retrofit2.http.GET

interface NewsService {

    @GET("/posts")
    suspend fun loadNews(): List<News>
}