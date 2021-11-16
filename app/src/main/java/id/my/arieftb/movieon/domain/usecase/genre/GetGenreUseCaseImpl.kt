package id.my.arieftb.movieon.domain.usecase.genre

import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.genre.GenreEntity
import id.my.arieftb.movieon.domain.model.request.genre.GenreRequest
import id.my.arieftb.movieon.domain.repo.genre.GenreRepository
import id.my.arieftb.movieon.domain.usecase.base.FlowableWithParamUseCaseImpl
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetGenreUseCaseImpl @Inject constructor(
    private val repository: GenreRepository
) : FlowableWithParamUseCaseImpl<GenreRequest, GenreEntity>() {

    override fun build(param: GenreRequest): Flowable<ResultEntity<GenreEntity>> {
        return repository.fetch(param)
    }
}