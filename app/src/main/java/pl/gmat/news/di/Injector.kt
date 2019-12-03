package pl.gmat.news.di

import pl.gmat.news.NewsApp

object Injector {

    lateinit var appComponent: AppComponent
        private set

    fun init(application: NewsApp) {
        appComponent = DaggerAppComponent.factory().create(application)
        appComponent.inject(application)
    }
}