package pl.gmat.news.feature.main.di

import dagger.BindsInstance
import dagger.Subcomponent
import pl.gmat.news.di.ScreenScope
import pl.gmat.news.feature.main.MainActivity

@ScreenScope
@Subcomponent(
    modules = [
        MainModule::class
    ]
)
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(@BindsInstance activity: MainActivity): MainComponent
    }

    fun inject(activity: MainActivity)
}