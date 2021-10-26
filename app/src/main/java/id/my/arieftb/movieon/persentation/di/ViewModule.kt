package id.my.arieftb.movieon.persentation.di

import dagger.Binds
import dagger.Module
import id.my.arieftb.movieon.persentation.feature.movie_list.MovieListActivity
import id.my.arieftb.movieon.persentation.feature.movie_list.MovieListContract

@Module
abstract class ViewModule {

    @Binds
    abstract fun provideMovieListView(movieListActivity: MovieListActivity): MovieListContract.View
}