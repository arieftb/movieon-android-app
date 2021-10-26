package id.my.arieftb.movieon.domain.repo.genre

import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.genre.GenreEntity
import id.my.arieftb.movieon.domain.model.request.genre.GenreRequest
import io.reactivex.rxjava3.core.Flowable

interface GenreRepository {
    fun fetch(request: GenreRequest): Flowable<ResultEntity<GenreEntity>>
}