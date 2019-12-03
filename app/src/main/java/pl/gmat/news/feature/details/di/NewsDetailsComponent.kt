package pl.gmat.news.feature.details.di

import dagger.BindsInstance
import dagger.Subcomponent
import pl.gmat.news.di.ScreenScope
import pl.gmat.news.feature.details.NewsDetailsActivity

@ScreenScope
@Subcomponent(
    modules = [
        NewsDetailsModule::class
    ]
)
interface NewsDetailsComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(@BindsInstance activity: NewsDetailsActivity): NewsDetailsComponent
    }

    fun inject(activity: NewsDetailsActivity)
}