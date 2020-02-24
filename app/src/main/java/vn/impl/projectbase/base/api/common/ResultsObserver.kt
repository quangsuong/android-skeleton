package vn.impl.projectbase.base.api.common

import io.reactivex.observers.DisposableObserver

abstract class ResultsObserver<T> : DisposableObserver<Result<T>>() {

    /**
     * Do not override this method in derived class
     */
    final override fun onComplete() {}

    /**
     * Do not override this method in derived class, just implement [onSuccess]
     * method to handle [T] response
     */
    final override fun onNext(t: Result<T>) {
        when {
            t.data != null -> onSuccess(t.data)
            t.error != null -> onError(t.error)
        }
    }

    /**
     * Callback for handling Http response success (statusCode == 200)
     */
    abstract fun onSuccess(success: T)
}
