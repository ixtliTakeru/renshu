package g.takeru.renshu.architecture.mvp;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import timber.log.Timber;

/**
 * Created by takeru on 2017/11/13.
 */

public abstract class NullCheckPresenter<V extends BaseView> {

    private WeakReference<V> view;
    private V nullView;

    protected NullCheckPresenter(){
        try {
            nullView = NullView.of(internalGetViewInterfaceClass());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @SuppressWarnings("unchecked") @NonNull
    private Class<V> internalGetViewInterfaceClass() {
        Class clazz = getClass();
        Type genericSuperclass;
        for (;;) {
            genericSuperclass = clazz.getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                break;
            }
            clazz = clazz.getSuperclass();
        }
        return (Class<V>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }

    public void attachView(V view){
        this.view = new WeakReference<>(view);
    }

    public void detachView(){
        view = null;
    }

    protected V getView(){
        if(view == null){
            Timber.e("getView: view is null, returning dummy view.");
            return nullView;
        } else {
            return view.get();
        }
    }
}


