package id.my.arieftb.movieon.data.repo

import id.my.arieftb.movieon.data.source.remote.genre.GenreRemoteDataSource
import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.genre.GenreEntity
import id.my.arieftb.movieon.domain.model.request.genre.GenreRequest
import id.my.arieftb.movieon.domain.repo.genre.GenreRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRepositoryImpl @Inject constructor(
    private val remoteDataSource: GenreRemoteDataSource
) : GenreRepository {
    override fun fetch(request: GenreRequest): Flowable<ResultEntity<GenreEntity>> {
        return remoteDataSource.fetchAll().map {
            if (it.isSuccessful) {
                val data = it.body()?.genres?.find { genre ->
                    genre.id == request.id
                }
                if (data != null) {
                    ResultEntity.Success(
                        GenreEntity(
                            id = data.id,
                            name = data.name
                        )
                    )
                } else {
                    ResultEntity.Error("No Data Found")
                }
            } else {
                ResultEntity.Error(it.message())
            }
        }
    }
}