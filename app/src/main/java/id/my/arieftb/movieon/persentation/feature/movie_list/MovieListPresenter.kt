package id.my.arieftb.movieon.persentation.feature.movie_list

import android.util.Log
import id.my.arieftb.movieon.data.model.genres.Genre
import id.my.arieftb.movieon.domain.model.entity.ResultEntity
import id.my.arieftb.movieon.domain.model.entity.genre.GenreEntity
import id.my.arieftb.movieon.domain.model.entity.movie.MovieEntity
import id.my.arieftb.movieon.domain.usecase.movie.GetMoviesUseCaseImpl
import id.my.arieftb.movieon.persentation.base.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.parallel.ParallelFlowable.from
import io.reactivex.rxjava3.schedulers.Schedulers
import java.sql.Time
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieListPresenter @Inject constructor(
    private val getMovieUseCase: GetMoviesUseCaseImpl
) : BasePresenter<MovieListContract.View>(), MovieListContract.Presenter {
    override fun doGetMovies() {
        getMovieUseCase.execute(
            onSuccess = {
                if (it.isNotEmpty()) {
                    view?.onSuccessGetMovies(it)
                } else {
                    view?.onEmptyGetMovies()
                }
            },
            onError = {
                view?.onFailureGetMovies(it)
            },
            onFailure = {
                view?.onFailureGetMovies(it)
            },
        )
    }

    override fun dettachView() {
        getMovieUseCase.terminate()
        super.dettachView()
    }
}