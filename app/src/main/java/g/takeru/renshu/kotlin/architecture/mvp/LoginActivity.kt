package g.takeru.renshu.kotlin.architecture.mvp

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import g.takeru.renshu.kotlin.User
import timber.log.Timber

/**
 * Created by takeru on 2017/10/11.
 */

class LoginActivity : AppCompatActivity(), LoginView {

    private var presenter: LoginPresenter? = null

    override val context: Context
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = LoginPresenter()
        presenter?.attachView(this)
        presenter?.doLoginTask("Takeru", "111111")
    }

    override fun displayLoading(show: Boolean) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    override fun loginSuccess(user: User) {
        Timber.d("Login Success " + user.name)
    }

    override fun loginFailure(errorMsg: String) {

    }
}
