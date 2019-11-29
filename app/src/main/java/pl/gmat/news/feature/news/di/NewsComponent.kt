package pl.gmat.news.feature.news.di

import dagger.BindsInstance
import dagger.Subcomponent
import pl.gmat.news.di.ScreenScope
import pl.gmat.news.feature.news.NewsActivity

@ScreenScope
@Subcomponent(
    modules = [
        NewsModule::class
    ]
)
interface NewsComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(@BindsInstance activity: NewsActivity): NewsComponent
    }

    fun inject(activity: NewsActivity)
}