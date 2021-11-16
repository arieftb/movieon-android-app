package id.my.arieftb.movieon.domain.usecase.base

interface AsyncUseCase<R> {
    fun execute(
        onSuccess: ((result: R) -> Unit)? = null,
        onError: ((result: String) -> Unit)? = null,
        onFailure: ((throwable: Throwable) -> Unit)? = null
    )
    fun terminate()
}