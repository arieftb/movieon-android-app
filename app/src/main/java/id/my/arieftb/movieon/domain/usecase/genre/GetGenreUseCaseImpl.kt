package id.my.arieftb.movieon.domain.usecase.genre

import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.genre.GenreEntity
import id.my.arieftb.movieon.domain.model.request.genre.GenreRequest
import id.my.arieftb.movieon.domain.repo.genre.GenreRepository
import id.my.arieftb.movieon.domain.usecase.base.FlowableUseCaseImpl
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Singleton

@Singleton
class GetGenreUseCaseImpl constructor(
    private val repository: GenreRepository
): FlowableUseCaseImpl<GenreEntity>() {

    private lateinit var request: GenreRequest

    fun setRequest(id: Int) {
        request = GenreRequest(
            id = id
        )
    }

    override fun build(): Flowable<ResultEntity<GenreEntity>> {
        return repository.fetch(request)
    }
}