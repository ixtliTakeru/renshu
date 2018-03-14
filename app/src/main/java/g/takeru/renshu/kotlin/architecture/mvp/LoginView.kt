package g.takeru.renshu.kotlin.architecture.mvp

import g.takeru.renshu.kotlin.User

/**
 * Created by takeru on 2017/10/11.
 */

interface LoginView : BaseView {
    fun loginSuccess(user: User)
    fun loginFailure(errorMsg: String)
}
