package id.my.arieftb.movieon.domain.usecase.movie

import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.movie.MovieEntity
import id.my.arieftb.movieon.domain.repo.movie.MovieRepository
import id.my.arieftb.movieon.domain.usecase.base.FlowableUseCaseImpl
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMoviesUseCaseImpl @Inject constructor(
    private val movieRepo: MovieRepository
) : FlowableUseCaseImpl<List<MovieEntity>>() {

    override fun build(): Flowable<ResultEntity<List<MovieEntity>>> {
        return movieRepo.fetchCollection()
    }
}