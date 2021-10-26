package id.my.arieftb.movieon.persentation.di

import dagger.Module
import dagger.Provides
import id.my.arieftb.movieon.domain.usecase.movie.GetMoviesUseCaseImpl
import id.my.arieftb.movieon.persentation.feature.movie_list.MovieListPresenter
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun provideMovieListPresenter(
        getMoviesUseCaseImpl: GetMoviesUseCaseImpl
    ): MovieListPresenter = MovieListPresenter(getMoviesUseCaseImpl)
}