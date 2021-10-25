package id.my.arieftb.movieon.domain.model.entity

import java.lang.Exception

sealed class ResultEntity<out T> {
    class Success<R: Any>(val data: R): ResultEntity<R>()
    class Failure<out D: Any>(val exception: Exception): ResultEntity<D>()
}
