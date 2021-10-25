package id.my.arieftb.movieon.data.model.movies

data class MovieCollectionResponse(
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int
)