package pl.gmat.news.feature

import okhttp3.MediaType
import okhttp3.ResponseBody
import pl.gmat.news.common.model.Comment
import pl.gmat.news.common.model.News
import retrofit2.HttpException
import retrofit2.Response

fun testNews() = News(1, "title", "body")

fun testComment() = Comment(1, "email", "body")

fun testError() = HttpException(
    Response.error<List<News>>(
        404,
        ResponseBody.create(MediaType.get("text/plain"), "error")
    )
)