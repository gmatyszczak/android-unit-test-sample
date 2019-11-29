package pl.gmat.news.feature.news.di

import dagger.Module
import dagger.Provides
import pl.gmat.news.database.AppDatabase
import pl.gmat.news.di.ScreenScope
import pl.gmat.news.feature.news.NewsRepository
import pl.gmat.news.feature.news.NewsRepositoryImpl
import pl.gmat.news.feature.news.api.NewsService
import pl.gmat.news.feature.news.dao.NewsDao
import retrofit2.Retrofit

@Module
class NewsModule {

    @ScreenScope
    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)

    @ScreenScope
    @Provides
    fun provideNewsRepository(repository: NewsRepositoryImpl): NewsRepository = repository

    @ScreenScope
    @Provides
    fun provideNewsDao(database: AppDatabase): NewsDao = database.newsDao()
}