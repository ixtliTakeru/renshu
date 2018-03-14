package g.takeru.renshu.kotlin.architecture.mvp

/**
 * Created by takeru on 2017/10/11.
 */

interface BasePresenter<V> {
    fun attachView(view: V)
    fun detachView()
}
