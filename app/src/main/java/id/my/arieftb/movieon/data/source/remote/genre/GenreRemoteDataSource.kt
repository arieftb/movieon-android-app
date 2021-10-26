package id.my.arieftb.movieon.data.source.remote.genre

import id.my.arieftb.movieon.data.model.genres.GenreCollectionResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response

interface GenreRemoteDataSource {
    fun fetchAll(): Flowable<Response<GenreCollectionResponse>>
}