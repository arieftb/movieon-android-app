package id.my.arieftb.movieon.data.repo

import id.my.arieftb.movieon.data.source.remote.movie.MovieRemoteDataSource
import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.movie.MovieEntity
import id.my.arieftb.movieon.domain.repo.movie.MovieRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override fun fetchCollection(): Flowable<ResultEntity<List<MovieEntity>>> {
        return remoteDataSource.fetchAlL().map {
            if (it.isSuccessful) {
                ResultEntity.Success(it.body()?.results?.asSequence()?.map { response ->
                    MovieEntity(
                        response.id.toString(),
                        response.title,
                        response.overview
                    )
                }?.toList() ?: emptyList())
            } else {
                ResultEntity.Error(it.message())
            }
        }
    }
}