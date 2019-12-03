package pl.gmat.news.feature.news.di

import dagger.Module
import dagger.Provides
import pl.gmat.news.common.di.ScreenScope
import pl.gmat.news.feature.news.NewsRepository
import pl.gmat.news.feature.news.NewsRepositoryImpl

@Module
class NewsModule {

    @ScreenScope
    @Provides
    fun provideNewsRepository(repository: NewsRepositoryImpl): NewsRepository = repository

}