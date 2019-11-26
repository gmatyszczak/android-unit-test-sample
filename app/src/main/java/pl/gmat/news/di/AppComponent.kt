package pl.gmat.news.di

import dagger.BindsInstance
import dagger.Component
import pl.gmat.news.NewsApp
import pl.gmat.news.feature.main.di.MainComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: NewsApp): AppComponent
    }

    fun inject(application: NewsApp)

    fun mainComponentFactory(): MainComponent.Factory
}