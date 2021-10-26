package id.my.arieftb.movieon.persentation.feature.movie_list

import id.my.arieftb.movieon.domain.model.entity.movie.MovieEntity

class MovieListContract {
    interface Presenter {
        fun doGetMovies()
    }
    interface View {
        fun onSuccessGetMovies(list: List<MovieEntity>)
        fun onEmptyGetMovies()
        fun onFailureGetMovies(errorMessage: String)
        fun onFailureGetMovies(throwable: Throwable)
    }
}