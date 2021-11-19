package id.my.arieftb.movieon.domain.usecase.genre

import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.genre.GenreEntity
import id.my.arieftb.movieon.domain.model.request.genre.GenreRequest
import id.my.arieftb.movieon.domain.repo.genre.GenreRepository
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

class GetGenreUseCaseImplTest {
    private lateinit var getGenreUseCaseImpl: GetGenreUseCaseImpl

    private val repository: GenreRepository = Mockito.mock(GenreRepository::class.java)


    private var onSuccessMock = mock<(GenreEntity) -> Unit>()
    private var onErrorMock = mock<(String) -> Unit>()
    private var onFailureMock = mock<(Throwable) -> Unit>()

    @Before
    fun setUp() {
        //Setup RX Handler
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        getGenreUseCaseImpl = GetGenreUseCaseImpl(repository)
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
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

    @Test
    fun executeUseCaseSuccess() {
//        Given
        val request = GenreRequest(id = 28)
        val result: ResultEntity<GenreEntity> =
            ResultEntity.Success(data = GenreEntity(28, "Action"))

//        Stub
        Mockito.doReturn(Flowable.just(result)).`when`(repository).fetch(request)

//        When
        getGenreUseCaseImpl.execute(request, onSuccessMock, onErrorMock, onFailureMock)

//        Then
        verify(onSuccessMock).invoke((result as ResultEntity.Success).data)
    }

    @Test
    fun executeUseCaseError() {
//        Given
        val request = GenreRequest(id = 28)
        val result: ResultEntity<GenreEntity> =
            ResultEntity.Error(errorMessage = "Error")

//        Stub
        Mockito.doReturn(Flowable.just(result)).`when`(repository).fetch(request)

//        When
        getGenreUseCaseImpl.execute(request, onSuccessMock, onErrorMock, onFailureMock)

//        Then
        verify(onSuccessMock, never()).invoke(any())
        verify(onErrorMock).invoke((result as ResultEntity.Error).errorMessage)
    }

    @Test
    fun executeUseCaseFailure() {
//        Given
        val request = GenreRequest(id = 28)
        val result: Throwable = Mockito.mock(Throwable::class.java)

//        Stub
        Mockito.doReturn("Error").`when`(result).message
        Mockito.`when`(repository.fetch(request)).thenReturn(Flowable.error(result))

//        When
        getGenreUseCaseImpl.execute(request, onSuccessMock, onErrorMock, onFailureMock)

//        Then
        verify(onSuccessMock, never()).invoke(any())
        verify(onErrorMock, never()).invoke(any())
        verify(onFailureMock).invoke(result)
    }
}