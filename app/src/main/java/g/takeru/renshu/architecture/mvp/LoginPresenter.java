package g.takeru.renshu.architecture.mvp;

import g.takeru.renshu.kotlin.User;

/**
 * Created by takeru on 2017/10/11.
 */

public class LoginPresenter extends NullCheckPresenter<LoginView>{

    public void doLoginTask(String name, String password) {
        getView().loginSuccess(new User("takeru", 20));

//        ApiManager.Companion.getInstance().getApi()
//                .getData()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        response ->{
//                            Timber.d("success: " + response);
//                        },
//                        throwable -> {
//                            Timber.d("failure: " + throwable.getMessage());
//                        }
//                        );
    }
}
