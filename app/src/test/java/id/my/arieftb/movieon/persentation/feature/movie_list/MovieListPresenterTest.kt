package id.my.arieftb.movieon.persentation.feature.movie_list

import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.movie.MovieEntity
import id.my.arieftb.movieon.domain.repo.genre.GenreRepository
import id.my.arieftb.movieon.domain.repo.movie.MovieRepository
import id.my.arieftb.movieon.domain.usecase.movie.GetMoviesUseCaseImpl
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

class MovieListPresenterTest {
    private lateinit var presenter: MovieListPresenter

    @Mock
    private lateinit var genreRepository: GenreRepository

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var view: MovieListContract.View

    private lateinit var getMovieListUseCase: GetMoviesUseCaseImpl

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        MockitoAnnotations.openMocks(this)
        getMovieListUseCase = GetMoviesUseCaseImpl(movieRepository, genreRepository)
        presenter = MovieListPresenter(getMovieListUseCase)
        presenter.attachView(view)
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
    }

    @Test
    fun doGetMoviesWithoutGenreSuccess() {
//        Give
        val result: ResultEntity.Success<List<MovieEntity>> = ResultEntity.Success(
            data = listOf(
                MovieEntity("566525", "Shang-Chi and the Legend of the Ten Rings", )
            )
        )

//        Stub
        Mockito.doReturn(Flowable.just(result)).`when`(movieRepository).fetchCollection()

//        When
        presenter.doGetMovies()

//        Then
        verify(view).onSuccessGetMovies(result.data)
        verify(view, never()).onEmptyGetMovies()
        verify(view, never()).onErrorGetMovies(any())
        verify(view, never()).onFailureGetMovies(any())
    }
}