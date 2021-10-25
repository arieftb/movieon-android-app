package id.my.arieftb.movieon.domain.usecase.movie

import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.movie.MovieEntity
import id.my.arieftb.movieon.domain.repo.movie.MovieRepository
import id.my.arieftb.movieon.domain.usecase.base.FlowableUseCaseImpl
import io.reactivex.rxjava3.core.Flowable

class GetMoviesUseCaseImpl(
    private val movieRepo: MovieRepository
) : FlowableUseCaseImpl<ResultEntity<List<MovieEntity>>>() {

    override fun build(): Flowable<ResultEntity<List<MovieEntity>>> {
        return movieRepo.fetchCollection()
    }
}