package pl.gmat.news.feature.news

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pl.gmat.news.common.Result
import pl.gmat.news.common.api.NewsService
import pl.gmat.news.common.dao.NewsDao
import pl.gmat.news.common.model.News
import pl.gmat.news.feature.testError

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryImplTest {

    @Mock
    private lateinit var newsServiceMock: NewsService

    @Mock
    private lateinit var newsDaoMock: NewsDao

    @InjectMocks
    private lateinit var repository: NewsRepositoryImpl

    private val news = News(1, "title", "body")

    @Test
    fun `when success on refresh news`() = runBlockingTest {
        whenever(newsServiceMock.loadAllNews()).thenReturn(listOf(news))

        assertEquals(Result.Success(listOf(news)), repository.refreshNews())
        verify(newsDaoMock).insert(listOf(news))
    }

    @Test
    fun `when failure on refresh news`() = runBlockingTest {
        val error = testError()
        whenever(newsServiceMock.loadAllNews()).thenThrow(error)

        assertEquals(Result.Failure(error), repository.refreshNews())
        verifyZeroInteractions(newsDaoMock)
    }

    @Test
    fun `when query empty on load news`() = runBlockingTest {
        val flow = flowOf(listOf(news))
        whenever(newsDaoMock.loadAll()).thenReturn(flow)

        repository.loadNews("").collect {
            assertEquals(listOf(news), it)
        }
    }

    @Test
    fun `when query not empty on load news`() = runBlockingTest {
        val flow = flowOf(listOf(news))
        whenever(newsDaoMock.search("test")).thenReturn(flow)

        repository.loadNews("test").collect {
            assertEquals(listOf(news), it)
        }
    }
}