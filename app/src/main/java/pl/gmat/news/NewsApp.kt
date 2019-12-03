package pl.gmat.news

import android.app.Application
import pl.gmat.news.common.di.Injector

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}