package pl.gmat.news.feature.main

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
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
import pl.gmat.news.feature.main.api.NewsService
import pl.gmat.news.feature.main.dao.NewsDao
import pl.gmat.news.feature.main.dao.NewsEntity
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

    private val testDispatcher = TestCoroutineDispatcher()

    private val news = News(1, "title", "body")
    private val newsEntity = NewsEntity(1, "title", "body")

    @Test
    fun `when success on refresh news`() = runBlockingTest {
        whenever(newsServiceMock.loadNews()).thenReturn(listOf(news))
        whenever(mapperMock.toNewsEntity(news)).thenReturn(newsEntity)

        assertEquals(Result.Success(listOf(news)), repository.refreshNews())
        verify(newsDaoMock).insert(listOf(newsEntity))
    }

    @Test
    fun `when failure on refresh news`() = runBlockingTest {
        val throwable = HttpException(
            Response.error<List<News>>(
                404,
                ResponseBody.create(MediaType.get("text/plain"), "text")
            )
        )
        whenever(newsServiceMock.loadNews()).thenThrow(throwable)

        assertEquals(Result.Failure(throwable), repository.refreshNews())
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