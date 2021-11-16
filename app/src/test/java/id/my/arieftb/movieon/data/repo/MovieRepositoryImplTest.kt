package id.my.arieftb.movieon.data.repo

import id.my.arieftb.movieon.data.model.movies.MovieCollectionResponse
import id.my.arieftb.movieon.data.source.remote.movie.MovieRemoteDataSource
import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.movie.MovieEntity
import id.my.arieftb.movieon.utils.ResponseHelper
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

class MovieRepositoryImplTest {
    private lateinit var repository: MovieRepositoryImpl

    private val remoteDataSource: MovieRemoteDataSource =
        Mockito.mock(MovieRemoteDataSource::class.java)

    @Before
    fun setUp() {
        repository = MovieRepositoryImpl(remoteDataSource)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun fetchCollectionSuccess() {
//        Given
        val response: Response<MovieCollectionResponse> = ResponseHelper.createDummySuccessResponse(
            "movie_collection/movie_collection_response.json",
            MovieCollectionResponse::class.java
        )

//        Stub
        Mockito.doReturn(Flowable.just(response)).`when`(remoteDataSource).fetchAlL()

//        When
        val subscriber: TestSubscriber<ResultEntity<List<MovieEntity>>> =
            repository.fetchCollection().test()

//        Then
        subscriber.assertValue {
            it is ResultEntity.Success
        }
    }
}