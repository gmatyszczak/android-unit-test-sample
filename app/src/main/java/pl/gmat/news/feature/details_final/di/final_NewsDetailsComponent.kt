package pl.gmat.news.feature.details_final.di
//
//import dagger.BindsInstance
//import dagger.Subcomponent
//import pl.gmat.news.common.di.ScreenScope
//import pl.gmat.news.feature.details.NewsDetailsActivity
//
//@ScreenScope
//@Subcomponent(
//    modules = [
//        NewsDetailsModule::class
//    ]
//)
//interface NewsDetailsComponent {
//
//    @Subcomponent.Factory
//    interface Factory {
//
//        fun create(@BindsInstance activity: NewsDetailsActivity): NewsDetailsComponent
//    }
//
//    fun inject(activity: NewsDetailsActivity)
//}