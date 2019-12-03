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
import pl.gmat.news.feature.testError
import pl.gmat.news.feature.testNews

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryImplTest {

    @Mock
    private lateinit var daoMock: NewsDao

    @Mock
    private lateinit var serviceMock: NewsService

    @InjectMocks
    private lateinit var repository: NewsRepositoryImpl

    private val news = testNews()

    @Test
    fun `on load news`() = runBlockingTest {
        whenever(daoMock.loadAll()).thenReturn(flowOf(listOf(news)))

        repository.loadNews().collect {
            assertEquals(listOf(news), it)
        }
    }

    @Test
    fun `when success on refresh news`() = runBlockingTest {
        whenever(serviceMock.loadAllNews()).thenReturn(listOf(news))

        assertEquals(Result.Success(listOf(news)), repository.refreshNews())

        verify(daoMock).insert(listOf(news))
    }

    @Test
    fun `when failure on refresh news`() = runBlockingTest {
        val error = testError()
        whenever(serviceMock.loadAllNews()).thenThrow(error)

        assertEquals(Result.Failure(error), repository.refreshNews())

        verifyZeroInteractions(daoMock)
    }
}