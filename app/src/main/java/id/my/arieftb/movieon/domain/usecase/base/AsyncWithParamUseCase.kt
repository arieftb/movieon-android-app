package id.my.arieftb.movieon.domain.usecase.base

interface AsyncWithParamUseCase<P, R> {
    fun execute(
        param: P,
        onSuccess: ((result: R) -> Unit),
        onError: ((result: String) -> Unit),
        onFailure: ((result: Throwable) -> Unit)
    )

    fun terminate()
}