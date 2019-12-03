package pl.gmat.news.feature.details_final.di

//import dagger.Module
//import dagger.Provides
//import pl.gmat.news.common.model.News
//import pl.gmat.news.common.di.ScreenScope
//import pl.gmat.news.feature.details.NewsDetailsActivity
//import pl.gmat.news.feature.details.NewsDetailsRepository
//import pl.gmat.news.feature.details.NewsDetailsRepositoryImpl
//
//@Module
//class NewsDetailsModule {
//
//    @ScreenScope
//    @Provides
//    fun provideNews(activity: NewsDetailsActivity): News =
//        activity.intent.getParcelableExtra(NewsDetailsActivity.EXTRA_NEWS) ?: News(0, "", "")
//
//    @ScreenScope
//    @Provides
//    fun provideNewsDetailsRepository(repository: NewsDetailsRepositoryImpl): NewsDetailsRepository =
//        repository
//}