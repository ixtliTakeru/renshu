package g.takeru.renshu.architecture.mvp;

/**
 * Created by takeru on 2017/10/11.
 */

public interface BasePresenter<V> {
    void attachView(V view);
    void detachView();
}
