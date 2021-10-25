package id.my.arieftb.movieon.data.di

import dagger.Module
import dagger.Provides
import id.my.arieftb.movieon.data.repo.MovieRepositoryImpl
import id.my.arieftb.movieon.data.source.remote.movie.MovieRemoteDataSource
import id.my.arieftb.movieon.domain.repo.movie.MovieRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(remoteDataSource: MovieRemoteDataSource): MovieRepository =
        MovieRepositoryImpl(remoteDataSource)
}