package pl.gmat.news.common

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface SchedulerProvider {

    fun io(): Scheduler
    fun main(): Scheduler
}

class SchedulerProviderImpl @Inject constructor() : SchedulerProvider {

    override fun io(): Scheduler = Schedulers.io()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}