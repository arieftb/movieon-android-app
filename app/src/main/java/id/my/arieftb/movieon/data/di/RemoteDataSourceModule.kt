package id.my.arieftb.movieon.data.di

import dagger.Module
import dagger.Provides
import id.my.arieftb.movieon.data.source.remote.genre.GenreRemoteDataSource
import id.my.arieftb.movieon.data.source.remote.genre.GenreRemoteDataSourceImpl
import id.my.arieftb.movieon.data.source.remote.genre.GenreRemoteService
import id.my.arieftb.movieon.data.source.remote.movie.MovieRemoteDataSource
import id.my.arieftb.movieon.data.source.remote.movie.MovieRemoteDataSourceImpl
import id.my.arieftb.movieon.data.source.remote.movie.MovieRemoteService

@Module
class RemoteDataSourceModule {

    @Provides
    fun provideGenreRemoteDataSource(genreRemoteService: GenreRemoteService): GenreRemoteDataSource =
        GenreRemoteDataSourceImpl(genreRemoteService)

    @Provides
    fun provideMovieRemoteDataSource(movieRemoteService: MovieRemoteService): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(movieRemoteService)
}