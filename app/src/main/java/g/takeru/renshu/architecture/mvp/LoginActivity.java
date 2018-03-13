package g.takeru.renshu.architecture.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import g.takeru.renshu.R;
import g.takeru.renshu.kotlin.User;
import timber.log.Timber;

/**
 * Created by takeru on 2017/10/11.
 */

public class LoginActivity extends AppCompatActivity implements LoginView{

    LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new LoginPresenter();
        presenter.attachView(this);

        presenter.doLoginTask("Takeru", "111111");

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void displayLoading(boolean show) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void loginSuccess(User user) {
        Timber.d("Login Success " + user.getName());
    }

    @Override
    public void loginFailure(String errorMsg) {

    }
}
