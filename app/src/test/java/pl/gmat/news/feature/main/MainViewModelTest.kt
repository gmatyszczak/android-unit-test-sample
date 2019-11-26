package pl.gmat.news.feature.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pl.gmat.news.common.DispatcherProvider

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repositoryMock: NewsRepository

    @Mock
    private lateinit var dispatcherProviderMock: DispatcherProvider

    @Mock
    private lateinit var stateObserverMock: Observer<MainState>

    private lateinit var viewModel: MainViewModel

    private val testDispatcher = TestCoroutineDispatcher()
    private val news = News(1, "title", "body")

    @Before
    fun setup() {
        val newsFlow = flowOf(listOf(news)).flowOn(testDispatcher)
        whenever(dispatcherProviderMock.main()).thenReturn(testDispatcher)
        whenever(dispatcherProviderMock.io()).thenReturn(testDispatcher)
        whenever(repositoryMock.loadNews()).thenReturn(newsFlow)

        viewModel = MainViewModel(repositoryMock, dispatcherProviderMock)
        viewModel.state.observeForever(stateObserverMock)
    }

    @Test
    fun `on init`() = testDispatcher.runBlockingTest {

        verify(stateObserverMock).onChanged(MainState(news = listOf(news)))
        verifyNoMoreInteractions(stateObserverMock)
    }
}