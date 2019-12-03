package pl.gmat.news.feature.news.di

import dagger.Module
import dagger.Provides
import pl.gmat.news.common.dao.NewsDao
import pl.gmat.news.database.AppDatabase
import pl.gmat.news.di.ScreenScope
import pl.gmat.news.feature.news.NewsRepository
import pl.gmat.news.feature.news.NewsRepositoryImpl

@Module
class NewsModule {

    @ScreenScope
    @Provides
    fun provideNewsRepository(repository: NewsRepositoryImpl): NewsRepository = repository

    @ScreenScope
    @Provides
    fun provideNewsDao(database: AppDatabase): NewsDao = database.newsDao()
}