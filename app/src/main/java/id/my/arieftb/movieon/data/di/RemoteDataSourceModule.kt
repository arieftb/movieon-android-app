package id.my.arieftb.movieon.data.di

import dagger.Module
import dagger.Provides
import id.my.arieftb.movieon.data.source.remote.movie.MovieRemoteDataSource
import id.my.arieftb.movieon.data.source.remote.movie.MovieRemoteDataSourceImpl
import id.my.arieftb.movieon.data.source.remote.movie.MovieRemoteService
import javax.inject.Singleton

@Module
class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(movieRemoteService: MovieRemoteService): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(movieRemoteService)
}