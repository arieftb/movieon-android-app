package id.my.arieftb.movieon.domain.usecase.base

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class FlowableUseCaseImpl<R> : UseCase<R> {
    internal abstract fun build(): Flowable<R>

    private var job: Disposable? = null

    override fun execute(onSuccess: (result: R) -> Unit, onError: (throwable: Throwable) -> Unit) {
        job = build()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
    }

    override fun terminate() {
        job?.dispose()
    }
}