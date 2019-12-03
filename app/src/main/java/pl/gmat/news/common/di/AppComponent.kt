package pl.gmat.news.common.di

import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.FlowPreview
import pl.gmat.news.NewsApp
import pl.gmat.news.feature.news.di.NewsComponent
import javax.inject.Singleton

@FlowPreview
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

    fun newsComponentFactory(): NewsComponent.Factory
//    fun newsDetailsComponentFactory(): NewsDetailsComponent.Factory
}