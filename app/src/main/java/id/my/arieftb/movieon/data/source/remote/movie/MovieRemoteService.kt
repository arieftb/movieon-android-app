package id.my.arieftb.movieon.data.source.remote.movie

import id.my.arieftb.movieon.data.model.movies.MovieCollectionResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import retrofit2.http.GET

@JvmSuppressWildcards
interface MovieRemoteService {
    @GET("discover/movie")
    fun getMovieCollection(): Flowable<Response<MovieCollectionResponse>>
}