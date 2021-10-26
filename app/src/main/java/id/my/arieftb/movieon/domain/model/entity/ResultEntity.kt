package id.my.arieftb.movieon.domain.model.entity

sealed class ResultEntity<out D> {
    class Success<out D: Any>(val data: D): ResultEntity<D>()
    class Error<out D: Any>(val errorMessage: String): ResultEntity<D>()
}
