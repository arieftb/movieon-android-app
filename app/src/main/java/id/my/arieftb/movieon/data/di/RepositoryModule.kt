package id.my.arieftb.movieon.data.di

import dagger.Module
import dagger.Provides
import id.my.arieftb.movieon.data.repo.GenreRepositoryImpl
import id.my.arieftb.movieon.data.repo.MovieRepositoryImpl
import id.my.arieftb.movieon.data.source.remote.genre.GenreRemoteDataSource
import id.my.arieftb.movieon.data.source.remote.movie.MovieRemoteDataSource
import id.my.arieftb.movieon.domain.repo.genre.GenreRepository
import id.my.arieftb.movieon.domain.repo.movie.MovieRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    fun provideGenreRepository(remoteDataSource: GenreRemoteDataSource): GenreRepository =
        GenreRepositoryImpl(remoteDataSource)

    @Provides
    fun provideMovieRepository(remoteDataSource: MovieRemoteDataSource): MovieRepository =
        MovieRepositoryImpl(remoteDataSource)
}