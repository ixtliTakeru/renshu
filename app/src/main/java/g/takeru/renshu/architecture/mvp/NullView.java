package g.takeru.renshu.architecture.mvp;

import androidx.annotation.NonNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static g.takeru.renshu.architecture.mvp.NullViewDefaults.defaultValue;
import static java.lang.reflect.Proxy.newProxyInstance;

/**
 * Created by takeru on 2017/11/13.
 */

public class NullView {
    private static final InvocationHandler DEFAULT_VALUE = new DefaultValueInvocationHandler();

    private NullView() {
    }

    @SuppressWarnings("unchecked")
    static <T> T of(@NonNull Class<T> interfaceClass) {
        return (T) newProxyInstance(
                interfaceClass.getClassLoader(), new Class[] { interfaceClass }, DEFAULT_VALUE);
    }

    private static class DefaultValueInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(@NonNull Object proxy, @NonNull Method method, @NonNull Object[] args) throws Throwable {
            return defaultValue(method.getReturnType());
        }
    }
}
