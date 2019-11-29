package pl.gmat.news.common

inline fun <T : Any> apiCall(request: () -> T) =
    try {
        Result.Success(request())
    } catch (e: Exception) {
        Result.Failure(e)
    }