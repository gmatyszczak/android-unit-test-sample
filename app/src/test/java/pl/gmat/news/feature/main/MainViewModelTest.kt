package pl.gmat.news.feature.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pl.gmat.news.common.Result
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repositoryMock: NewsRepository

    @Mock
    private lateinit var stateObserverMock: Observer<MainState>

    @Mock
    private lateinit var effectObserverMock: Observer<MainEffect>

    private lateinit var viewModel: MainViewModel

    private val testDispatcher = TestCoroutineDispatcher()
    private val news = News(1, "title", "body")

    @Before
    fun setup() = runBlockingTest {
        Dispatchers.setMain(testDispatcher)
        whenever(repositoryMock.loadNews()).thenReturn(flowOf(listOf(news)))
        whenever(repositoryMock.refreshNews()).thenReturn(Result.Success())
        viewModel = MainViewModel(repositoryMock)
        viewModel.state.observeForever(stateObserverMock)
        viewModel.effect.observeForever(effectObserverMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `on init`() {
        verify(stateObserverMock).onChanged(MainState(news = listOf(news)))
        verifyNoMoreInteractions(stateObserverMock)
        verifyZeroInteractions(effectObserverMock)
    }

    @Test
    fun `when is success on refresh news`() {
        viewModel.onNewsRefresh()

        inOrder(stateObserverMock) {
            verify(stateObserverMock).onChanged(MainState(news = listOf(news)))
            verify(stateObserverMock).onChanged(MainState(news = listOf(news), isLoading = true))
            verify(stateObserverMock).onChanged(MainState(news = listOf(news)))
        }
        verifyNoMoreInteractions(stateObserverMock)
        verifyZeroInteractions(effectObserverMock)
    }

    @Test
    fun `when is failure on refresh news`() = runBlockingTest {
        val error = HttpException(
            Response.error<List<News>>(
                404,
                ResponseBody.create(MediaType.get("text/plain"), "error")
            )
        )
        whenever(repositoryMock.refreshNews()).thenReturn(Result.Failure(error))

        viewModel.onNewsRefresh()

        inOrder(stateObserverMock) {
            verify(stateObserverMock).onChanged(MainState(news = listOf(news)))
            verify(stateObserverMock).onChanged(MainState(news = listOf(news), isLoading = true))
            verify(stateObserverMock).onChanged(MainState(news = listOf(news)))
        }
        verify(effectObserverMock).onChanged(MainEffect.ShowRefreshError(error))
        verifyNoMoreInteractions(stateObserverMock)
        verifyNoMoreInteractions(effectObserverMock)
    }

    @Test
    fun `on news clicked`() {
        viewModel.onNewsClicked(news)

        verify(stateObserverMock).onChanged(MainState(news = listOf(news)))
        verify(effectObserverMock).onChanged(MainEffect.ShowNews(news.id))
        verifyNoMoreInteractions(effectObserverMock)
        verifyNoMoreInteractions(stateObserverMock)
    }
}