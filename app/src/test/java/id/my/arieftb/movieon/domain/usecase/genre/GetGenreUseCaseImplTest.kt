package id.my.arieftb.movieon.domain.usecase.genre

import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.genre.GenreEntity
import id.my.arieftb.movieon.domain.model.request.genre.GenreRequest
import id.my.arieftb.movieon.domain.repo.genre.GenreRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.kotlin.Flowables
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetGenreUseCaseImplTest {
    private lateinit var getGenreUseCaseImpl: GetGenreUseCaseImpl

    private val repository: GenreRepository = Mockito.mock(GenreRepository::class.java)

    @Before
    fun setUp() {
        getGenreUseCaseImpl = GetGenreUseCaseImpl(repository)
    }

    @Test
    fun buildUseCaseSuccess() {
//        Given
        val request = GenreRequest(28)
        val result: ResultEntity<GenreEntity> =
            ResultEntity.Success(data = GenreEntity(28, "Action"))
//        Stub
        Mockito.doReturn(Flowable.just(result)).`when`(repository).fetch(request)
//        When
        val subscriber: TestSubscriber<ResultEntity<GenreEntity>> =
            getGenreUseCaseImpl.build(request).test()
//        Then
        subscriber.assertValue {
            it is ResultEntity.Success
        }
        subscriber.assertValue {
            (it as ResultEntity.Success).data.id == 28
        }
    }
}