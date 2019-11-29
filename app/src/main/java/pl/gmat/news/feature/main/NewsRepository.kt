package pl.gmat.news.feature.main

import io.reactivex.Completable
import io.reactivex.Flowable
import pl.gmat.news.common.SchedulerProvider
import pl.gmat.news.feature.main.api.NewsService
import pl.gmat.news.feature.main.dao.NewsDao
import javax.inject.Inject

interface NewsRepository {

    fun refreshNews(): Completable
    fun loadNews(): Flowable<List<News>>
}

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val newsDao: NewsDao,
    private val mapper: NewsMapper,
    private val schedulerProvider: SchedulerProvider
) : NewsRepository {

    override fun refreshNews(): Completable =
        newsService.loadNews()
            .flatMapCompletable { news -> newsDao.insert(news.map { mapper.toNewsEntity(it) }) }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.main())

    override fun loadNews(): Flowable<List<News>> =
        newsDao.loadAll().map { newsEntities -> newsEntities.map { mapper.toNews(it) } }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.main())
}