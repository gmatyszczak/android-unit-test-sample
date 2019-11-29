package pl.gmat.news.feature.main

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pl.gmat.news.common.SchedulerProvider
import pl.gmat.news.feature.main.api.NewsService
import pl.gmat.news.feature.main.dao.NewsDao
import pl.gmat.news.feature.main.dao.NewsEntity

@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryImplTest {

    @Mock
    private lateinit var newsServiceMock: NewsService

    @Mock
    private lateinit var newsDaoMock: NewsDao

    @Mock
    private lateinit var mapperMock: NewsMapper

    @Mock
    private lateinit var schedulerProviderMock: SchedulerProvider

    @InjectMocks
    private lateinit var repository: NewsRepositoryImpl

    private val news = News(1, "title", "body")
    private val newsEntity = NewsEntity(1, "title", "body")

    @Before
    fun setUp() {
        whenever(schedulerProviderMock.main()).thenReturn(Schedulers.trampoline())
        whenever(schedulerProviderMock.io()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun `when success on refresh news`() {
        whenever(newsServiceMock.loadNews()).thenReturn(Single.just(listOf(news)))
        whenever(mapperMock.toNewsEntity(news)).thenReturn(newsEntity)
        whenever(newsDaoMock.insert(listOf(newsEntity))).thenReturn(Completable.complete())

        repository.refreshNews().test().assertComplete()

        verify(newsDaoMock).insert(listOf(newsEntity))
    }

    @Test
    fun `when failure on refresh news`() {
        val throwable = Throwable()
        whenever(newsServiceMock.loadNews()).thenReturn(Single.error(throwable))

        repository.refreshNews().test().assertError(throwable)

        verifyZeroInteractions(newsDaoMock)
        verifyZeroInteractions(mapperMock)
    }

    @Test
    fun `on load news`() {
        whenever(mapperMock.toNews(newsEntity)).thenReturn(news)
        whenever(newsDaoMock.loadAll()).thenReturn(Flowable.just(listOf(newsEntity)))

        repository.loadNews().test().assertValue(listOf(news))
    }
}