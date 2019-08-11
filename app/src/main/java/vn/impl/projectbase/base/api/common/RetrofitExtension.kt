package vn.impl.projectbase.base.api.common

import io.reactivex.Observable
import retrofit2.HttpException
import java.io.IOException

fun <T> Observable<T>.retrofitResponseToResult(): Observable<Result<T>> {
    return this.map { it.asResult() }
        .onErrorReturn {
            if (it is HttpException || it is IOException) {
                return@onErrorReturn it.asErrorResult<T>()
            } else {
                throw it
            }
        }
}

fun <T> T.asResult(): Result<T> {
    return Result(data = this, error = null)
}

fun <T> Throwable.asErrorResult(): Result<T> {
    return Result(data = null, error = this)
}
