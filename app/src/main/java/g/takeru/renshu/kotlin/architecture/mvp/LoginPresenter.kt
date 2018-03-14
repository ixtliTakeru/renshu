package g.takeru.renshu.kotlin.architecture.mvp

import g.takeru.renshu.kotlin.User

/**
 * Created by takeru on 2017/10/11.
 */

class LoginPresenter : BasePresenter<LoginView> {

    private var view: LoginView? = null

    override fun attachView(view: LoginView) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    fun doLoginTask(name: String, password: String) {
        view?.loginSuccess(User("takeru", 20))
    }
}
