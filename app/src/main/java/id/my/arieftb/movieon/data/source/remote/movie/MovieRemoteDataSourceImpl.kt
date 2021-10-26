package id.my.arieftb.movieon.data.source.remote.movie

import id.my.arieftb.movieon.BuildConfig
import id.my.arieftb.movieon.data.model.movies.MovieCollectionResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSourceImpl @Inject constructor(
    private val service: MovieRemoteService
): MovieRemoteDataSource {
    override fun fetchAlL(): Flowable<Response<MovieCollectionResponse>> {
        return service.getMovieCollection(BuildConfig.API_KEY)
    }
}