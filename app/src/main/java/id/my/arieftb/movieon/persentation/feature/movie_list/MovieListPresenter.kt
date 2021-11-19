package id.my.arieftb.movieon.persentation.feature.movie_list

import id.my.arieftb.movieon.domain.usecase.movie.GetMoviesUseCaseImpl
import id.my.arieftb.movieon.persentation.base.BasePresenter
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
                view?.onErrorGetMovies(it)
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