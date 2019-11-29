package pl.gmat.news.feature.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repositoryMock: NewsRepository

    @Mock
    private lateinit var stateObserverMock: Observer<MainState>

    private lateinit var viewModel: MainViewModel

    private val news = News(1, "title", "body")

    @Test
    fun `on init`() {
        createViewModel()

        verify(stateObserverMock).onChanged(MainState(news = listOf(news)))
        verifyNoMoreInteractions(stateObserverMock)
    }

    private fun createViewModel() {
        whenever(repositoryMock.loadNews()).thenReturn(Flowable.just(listOf(news)))
        whenever(repositoryMock.refreshNews()).thenReturn(Completable.complete())

        viewModel = MainViewModel(repositoryMock)
        viewModel.state.observeForever(stateObserverMock)
    }
}