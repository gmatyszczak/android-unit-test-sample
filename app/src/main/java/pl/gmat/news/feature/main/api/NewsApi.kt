package pl.gmat.news.feature.main.api

import io.reactivex.Single
import pl.gmat.news.feature.main.News
import retrofit2.http.GET

interface NewsService {

    @GET("/posts")
    fun loadNews(): Single<List<News>>
}