package id.my.arieftb.movieon.domain.model.entity.movie

import id.my.arieftb.movieon.domain.model.entity.genre.GenreEntity

data class MovieEntity(
    var id: String,
    var title: String,
    var overview: String? = "-",
    var genres: MutableList<GenreEntity>? = mutableListOf(),
    var genreIds: List<Int>? = emptyList(),
)