package id.my.arieftb.movieon.domain.di

import dagger.Module
import dagger.Provides
import id.my.arieftb.movieon.domain.repo.movie.MovieRepository
import id.my.arieftb.movieon.domain.usecase.movie.GetMoviesUseCaseImpl
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetMoviesUseCase(movieRepository: MovieRepository): GetMoviesUseCaseImpl =
        GetMoviesUseCaseImpl(movieRepository)
}