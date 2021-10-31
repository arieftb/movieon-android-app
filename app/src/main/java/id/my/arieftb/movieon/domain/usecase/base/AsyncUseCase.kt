package id.my.arieftb.movieon.domain.usecase.base

interface AsyncUseCase<R> {
    fun execute(
        onSuccess: ((result: R) -> Unit),
        onError: ((result: String) -> Unit),
        onFailure: ((throwable: Throwable) -> Unit)
    )
    fun terminate()
}