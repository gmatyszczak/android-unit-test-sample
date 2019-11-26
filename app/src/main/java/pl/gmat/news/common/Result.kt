package pl.gmat.news.common

sealed class Result<out T> {
    data class Success<out T>(val data: T? = null) : Result<T>()
    data class Failure(val error: Throwable) : Result<Nothing>()
}
