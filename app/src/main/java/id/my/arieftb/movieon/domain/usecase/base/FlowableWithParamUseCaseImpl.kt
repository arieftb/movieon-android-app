package id.my.arieftb.movieon.domain.usecase.base

import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

abstract class FlowableWithParamUseCaseImpl<P, R> : AsyncWithParamUseCase<P, R> {
    internal abstract fun build(param: P): Flowable<ResultEntity<R>>
    private var job: Disposable? = null

    override fun execute(
        param: P,
        onSuccess: (result: R) -> Unit,
        onError: (result: String) -> Unit,
        onFailure: (throwable: Throwable) -> Unit
    ) {
        job = build(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .throttleWithTimeout(30, TimeUnit.SECONDS)
            .subscribe({
                when (it) {
                    is ResultEntity.Success -> onSuccess(it.data)
                    is ResultEntity.Error -> onError(it.errorMessage)
                }
            }, {
                it.printStackTrace()
                onFailure(it)
            })
    }

    override fun terminate() {
        job?.dispose()
    }
}