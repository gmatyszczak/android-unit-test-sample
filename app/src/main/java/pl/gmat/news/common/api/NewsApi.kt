package pl.gmat.news.common.api

import pl.gmat.news.common.model.Comment
import pl.gmat.news.common.model.News
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {

    @GET("/posts")
    suspend fun loadAllNews(): List<News>

    @GET("/posts/{id}")
    suspend fun loadNews(@Path("id") id: Int): News

    @GET("/posts/{id}/comments")
    suspend fun loadComments(@Path("id") id: Int): List<Comment>
}