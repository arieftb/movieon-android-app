package id.my.arieftb.movieon.persentation.feature.movie_list

import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.movie.MovieEntity
import id.my.arieftb.movieon.domain.usecase.movie.GetMoviesUseCaseImpl
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.schedulers.TestScheduler
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.*

class MovieListPresenterTest {
    private lateinit var presenter: MovieListPresenter

    @Mock
    private lateinit var getMovieListUseCase: GetMoviesUseCaseImpl

    @Mock
    private lateinit var view: MovieListContract.View

    @Captor
    private lateinit var argCaptor: ArgumentCaptor<Any>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MovieListPresenter(getMovieListUseCase)
    }

    @Test
    fun doGetMoviesSuccess() {
//        Give
        val testScheduler: TestScheduler = TestScheduler()
        val result: ResultEntity.Success<List<MovieEntity>> = ResultEntity.Success(
            data = listOf(
                MovieEntity("566525", "Shang-Chi and the Legend of the Ten Rings")
            )
        )
//        Stub
        Mockito.doReturn(Flowable.just(result)).`when`(getMovieListUseCase).build()
//        When
        val testObserver: TestSubscriber<ResultEntity<List<MovieEntity>>> =
            getMovieListUseCase.build().subscribeOn(testScheduler).observeOn(testScheduler)
                .test()
//        Then
    }
}