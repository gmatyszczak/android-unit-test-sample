package pl.gmat.news.feature.news

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pl.gmat.news.common.Result
import pl.gmat.news.feature.news.api.NewsService
import pl.gmat.news.feature.news.dao.NewsDao
import pl.gmat.news.feature.news.dao.NewsEntity
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryImplTest {

    @Mock
    private lateinit var newsServiceMock: NewsService

    @Mock
    private lateinit var newsDaoMock: NewsDao

    @Mock
    private lateinit var mapperMock: NewsMapper

    @InjectMocks
    private lateinit var repository: NewsRepositoryImpl

    private val news = News(1, "title", "body")
    private val newsEntity = NewsEntity(1, "title", "body")

    @Test
    fun `when success on refresh news`() = runBlockingTest {
        whenever(newsServiceMock.loadNews()).thenReturn(listOf(news))
        whenever(mapperMock.toNewsEntity(news)).thenReturn(newsEntity)

        assertEquals(Result.Success<Nothing>(), repository.refreshNews())
        verify(newsDaoMock).insert(listOf(newsEntity))
    }

    @Test
    fun `when failure on refresh news`() = runBlockingTest {
        val error = HttpException(
            Response.error<List<News>>(
                404,
                ResponseBody.create(MediaType.get("text/plain"), "error")
            )
        )
        whenever(newsServiceMock.loadNews()).thenThrow(error)

        assertEquals(Result.Failure(error), repository.refreshNews())
        verifyZeroInteractions(newsDaoMock)
        verifyZeroInteractions(mapperMock)
    }

    @Test
    fun `on load news`() = runBlockingTest {
        val flow = flowOf(listOf(newsEntity))
        whenever(mapperMock.toNews(newsEntity)).thenReturn(news)
        whenever(newsDaoMock.loadAll()).thenReturn(flow)

        repository.loadNews().collect {
            assertEquals(listOf(news), it)
        }
    }
}