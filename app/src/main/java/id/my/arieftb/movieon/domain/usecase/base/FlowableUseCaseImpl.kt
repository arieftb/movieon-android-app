package id.my.arieftb.movieon.domain.usecase.base

import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit

abstract class FlowableUseCaseImpl<R> : AsyncUseCase<R> {
    internal abstract fun build(): Flowable<ResultEntity<R>>

    private var job: Disposable? = null

    override fun execute(
        onSuccess: ((result: R) -> Unit)?,
        onError: ((result: String) -> Unit)?,
        onFailure: ((throwable: Throwable) -> Unit)?
    ) {
        job = build()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .throttleWithTimeout(30, TimeUnit.SECONDS)
            .subscribe({
                when (it) {
                    is ResultEntity.Success -> onSuccess?.invoke(it.data)
                    is ResultEntity.Error -> onError?.invoke(it.errorMessage)
                    else -> onFailure?.invoke(NullPointerException())
                }
            }, {
                it.printStackTrace()
                onFailure?.invoke(it)
            })
    }

    override fun terminate() {
        job?.dispose()
    }
}