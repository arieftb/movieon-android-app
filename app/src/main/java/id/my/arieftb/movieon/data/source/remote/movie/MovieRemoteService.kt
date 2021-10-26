package id.my.arieftb.movieon.data.source.remote.movie

import id.my.arieftb.movieon.data.model.movies.MovieCollectionResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

@JvmSuppressWildcards
interface MovieRemoteService {
    @GET("discover/movie")
    fun getMovieCollection(
        @Query("api_key") apiKey: String
    ): Flowable<Response<MovieCollectionResponse>>
}