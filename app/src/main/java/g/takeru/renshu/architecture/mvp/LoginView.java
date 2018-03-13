package g.takeru.renshu.architecture.mvp;

import g.takeru.renshu.kotlin.User;

/**
 * Created by takeru on 2017/10/11.
 */

public interface LoginView extends BaseView{
    void loginSuccess(User user);
    void loginFailure(String errorMsg);
}
