package id.my.arieftb.movieon.domain.usecase.movie

import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.genre.GenreEntity
import id.my.arieftb.movieon.domain.model.entity.movie.MovieEntity
import id.my.arieftb.movieon.domain.model.request.genre.GenreRequest
import id.my.arieftb.movieon.domain.repo.genre.GenreRepository
import id.my.arieftb.movieon.domain.repo.movie.MovieRepository
import id.my.arieftb.movieon.domain.usecase.base.FlowableUseCaseImpl
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMoviesUseCaseImpl @Inject constructor(
    private val movieRepo: MovieRepository,
    private val genreRepo: GenreRepository
) : FlowableUseCaseImpl<List<MovieEntity>>() {

    override fun build(): Flowable<ResultEntity<List<MovieEntity>>> {
        return movieRepo.fetchCollection().concatMap {
            if (it is ResultEntity.Success) {
                Flowable.just(it.data).concatMapIterable { movieList ->
                    movieList
                }.map { movie ->
                    movie.genres = Flowable.fromIterable(movie.genreIds).concatMap { genreId ->
                        genreRepo.fetch(GenreRequest(genreId)).map { genre ->
                            if (genre is ResultEntity.Success) {
                                genre.data
                            } else {
                                GenreEntity(-1, "Null")
                            }
                        }
                    }.filter { genre ->
                        genre.id != -1
                    }.toList().blockingGet()
                    movie
                }.toList().toFlowable().map { movieList ->
                    ResultEntity.Success(movieList)
                }
            } else {
                Flowable.just(it)
            }
        }
    }
}