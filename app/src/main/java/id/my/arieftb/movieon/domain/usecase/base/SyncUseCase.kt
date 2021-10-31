package id.my.arieftb.movieon.domain.usecase.base

interface SyncUseCase<R> {
    fun execute(): R
}