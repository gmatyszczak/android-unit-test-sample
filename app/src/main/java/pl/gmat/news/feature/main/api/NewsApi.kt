package pl.gmat.news.feature.main.api

import pl.gmat.news.feature.main.News
import retrofit2.http.GET

interface NewsService {

    @GET("/posts")
    suspend fun loadNews(): List<News>
}