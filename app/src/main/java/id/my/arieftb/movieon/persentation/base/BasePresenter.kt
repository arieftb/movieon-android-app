package id.my.arieftb.movieon.persentation.base

abstract class BasePresenter<V>: BasePresenterContract.Presenter<V> {
    protected var view: V? = null

    override fun attachView(v: V?) {
        view = v
    }

    override fun dettachView() {
        view = null
    }
}