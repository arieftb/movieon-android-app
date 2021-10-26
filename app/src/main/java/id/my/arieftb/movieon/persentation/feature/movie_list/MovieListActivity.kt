package id.my.arieftb.movieon.persentation.feature.movie_list

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import id.my.arieftb.movieon.core.AppApplication
import id.my.arieftb.movieon.core.di.DaggerApplicationComponent
import id.my.arieftb.movieon.databinding.ActivityMovieListBinding
import id.my.arieftb.movieon.domain.model.entity.movie.MovieEntity
import javax.inject.Inject

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private lateinit var binding: ActivityMovieListBinding

    @Inject
    lateinit var presenter: MovieListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerApplicationComponent.create().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.doGetMovies()
    }

    override fun onSuccessGetMovies(list: List<MovieEntity>) {

    }

    override fun onEmptyGetMovies() {

    }

    override fun onFailureGetMovies(errorMessage: String) {

    }

    override fun onFailureGetMovies(throwable: Throwable) {
        Log.d("MovieApp", "onFailureGetMovies: ${throwable.localizedMessage}")
    }
}