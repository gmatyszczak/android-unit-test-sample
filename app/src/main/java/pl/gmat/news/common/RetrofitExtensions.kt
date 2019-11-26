package pl.gmat.news.common

suspend fun <T : Any> apiCall(request: suspend () -> T) =
    try {
        Result.Success(request())
    } catch (e: Exception) {
        Result.Failure(e)
    }