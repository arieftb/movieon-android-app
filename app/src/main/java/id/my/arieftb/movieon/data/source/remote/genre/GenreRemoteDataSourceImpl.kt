package id.my.arieftb.movieon.data.source.remote.genre

import id.my.arieftb.movieon.BuildConfig
import id.my.arieftb.movieon.data.model.genres.GenreCollectionResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRemoteDataSourceImpl @Inject constructor(
    private val remoteService: GenreRemoteService
): GenreRemoteDataSource {

    override fun fetchAll(): Flowable<Response<GenreCollectionResponse>> {
        val requestMap: HashMap<String, Any> = HashMap()
        requestMap["api_key"] = BuildConfig.API_KEY

        return remoteService.getGenreCollection(requestMap)
    }
}