package id.my.arieftb.movieon.persentation.feature.movie_list

import id.my.arieftb.movieon.domain.model.entity.movie.MovieEntity
import id.my.arieftb.movieon.domain.usecase.base.UseCase
import id.my.arieftb.movieon.persentation.base.BasePresenter

class MovieListPresenter(
    private val getMovieUseCase: UseCase<List<MovieEntity>>
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
}