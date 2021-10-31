package id.my.arieftb.movieon.domain.di

import dagger.Module
import dagger.Provides
import id.my.arieftb.movieon.domain.repo.genre.GenreRepository
import id.my.arieftb.movieon.domain.repo.movie.MovieRepository
import id.my.arieftb.movieon.domain.usecase.genre.GetGenreUseCaseImpl
import id.my.arieftb.movieon.domain.usecase.movie.GetMoviesUseCaseImpl
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    fun provideGetGenreUseCase(genreRepository: GenreRepository): GetGenreUseCaseImpl =
        GetGenreUseCaseImpl(genreRepository)

    @Provides
    fun provideGetMoviesUseCase(movieRepository: MovieRepository, getGenreUseCaseImpl: GetGenreUseCaseImpl): GetMoviesUseCaseImpl =
        GetMoviesUseCaseImpl(movieRepository, getGenreUseCaseImpl)
}