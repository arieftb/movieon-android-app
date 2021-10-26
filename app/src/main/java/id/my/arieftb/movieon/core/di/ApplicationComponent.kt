package id.my.arieftb.movieon.core.di

import dagger.Component
import id.my.arieftb.movieon.data.di.RemoteDataSourceModule
import id.my.arieftb.movieon.data.di.RepositoryModule
import id.my.arieftb.movieon.data.di.RetrofitModule
import id.my.arieftb.movieon.domain.di.UseCaseModule
import id.my.arieftb.movieon.persentation.di.PresenterModule
import id.my.arieftb.movieon.persentation.di.ViewModule
import id.my.arieftb.movieon.persentation.feature.movie_list.MovieListActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteDataSourceModule::class, RepositoryModule::class, RetrofitModule::class, UseCaseModule::class, PresenterModule::class, ViewModule::class])
interface ApplicationComponent {
    fun inject(activity: MovieListActivity)
}