package pl.gmat.news.feature.main.di

import dagger.Module
import dagger.Provides
import pl.gmat.news.database.AppDatabase
import pl.gmat.news.di.ScreenScope
import pl.gmat.news.feature.main.NewsMapper
import pl.gmat.news.feature.main.NewsMapperImpl
import pl.gmat.news.feature.main.NewsRepository
import pl.gmat.news.feature.main.NewsRepositoryImpl
import pl.gmat.news.feature.main.api.NewsService
import pl.gmat.news.feature.main.dao.NewsDao
import retrofit2.Retrofit

@Module
class MainModule {

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

    @ScreenScope
    @Provides
    fun provideNewsMapper(mapper: NewsMapperImpl): NewsMapper = mapper
}