package pl.gmat.news.feature.details

import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pl.gmat.news.common.api.NewsService
import pl.gmat.news.common.model.Comment
import pl.gmat.news.feature.testComment
import pl.gmat.news.feature.testError

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsDetailsRepositoryImplTest {

    @Mock
    private lateinit var newsServiceMock: NewsService

    @InjectMocks
    private lateinit var repository: NewsDetailsRepositoryImpl

    private val comment = testComment()

    @Test
    fun `when successful on load comments`() = runBlockingTest {
        whenever(newsServiceMock.loadComments(1)).thenReturn(listOf(comment))

        assertEquals(listOf(comment), repository.loadComments(1))
    }

    @Test
    fun `when failure on load comments`() = runBlockingTest {
        whenever(newsServiceMock.loadComments(1)).thenThrow(testError())

        assertEquals(emptyList<Comment>(), repository.loadComments(1))
    }
}