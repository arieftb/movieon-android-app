package id.my.arieftb.movieon.persentation.base

class BasePresenterContract {
    interface Presenter<V> {
        fun attachView(v: V?)
        fun dettachView()
    }
}