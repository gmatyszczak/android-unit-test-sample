package pl.gmat.news.feature.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pl.gmat.news.common.model.Comment
import pl.gmat.news.feature.testComment
import pl.gmat.news.feature.testNews

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsDetailsViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repositoryMock: NewsDetailsRepository

    @Mock
    private lateinit var stateObserverMock: Observer<NewsDetailsState>

    @Mock
    private lateinit var effectObserverMock: Observer<NewsDetailsEffect>

    private val testDispatcher = TestCoroutineDispatcher()
    private val news = testNews()
    private val comment = testComment()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when comments not empty on init`() {
        createViewModel(listOf(comment))

        verify(stateObserverMock).onChanged(
            NewsDetailsState(
                news,
                listOf(comment),
                showLoader = false,
                showComments = true
            )
        )
    }

    @Test
    fun `when comments empty on init`() {
        createViewModel(emptyList())

        verify(stateObserverMock).onChanged(
            NewsDetailsState(
                news,
                emptyList(),
                showLoader = false,
                showComments = false
            )
        )
    }

    private fun createViewModel(comments: List<Comment>): NewsDetailsViewModel {
        runBlockingTest { whenever(repositoryMock.loadComments(1)).thenReturn(comments) }
        return NewsDetailsViewModel(news, repositoryMock).apply {
            state.observeForever(stateObserverMock)
            effect.observeForever(effectObserverMock)
        }
    }
}