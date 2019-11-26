package pl.gmat.news.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatcherProvider {

    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}

class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {

    override fun io() = Dispatchers.IO

    override fun main() = Dispatchers.Main
}