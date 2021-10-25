package id.my.arieftb.movieon.domain.repo.movie

import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.movie.MovieEntity
import io.reactivex.rxjava3.core.Flowable

interface MovieRepository {
    fun fetchCollection(): Flowable<ResultEntity<List<MovieEntity>>>
}