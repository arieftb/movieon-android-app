package id.my.arieftb.movieon.data.source.remote.genre

import id.my.arieftb.movieon.data.model.genres.GenreCollectionResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

@JvmSuppressWildcards
interface GenreRemoteService {
    @GET("genre/movie/list")
    fun getGenreCollection(
        @QueryMap queryMap: HashMap<String, Any>
    ): Flowable<Response<GenreCollectionResponse>>
}