package g.takeru.renshu.architecture.mvp;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

/**
 * Created by takeru on 2017/11/13.
 */

public class NullViewDefaults {
    private static final Map<Class<?>, Object> DEFAULTS = unmodifiableMap(new HashMap<Class<?>, Object>() {
        {
            put(Boolean.TYPE, false);
            put(Byte.TYPE, (byte) 0);
            put(Character.TYPE, '\000');
            put(Double.TYPE, 0.0d);
            put(Float.TYPE, 0.0f);
            put(Integer.TYPE, 0);
            put(Long.TYPE, 0L);
            put(Short.TYPE, (short) 0);
        }
    });

    private NullViewDefaults() {
    }

    @SuppressWarnings("unchecked")
    static <T> T defaultValue(@NonNull Class<T> type) {
        return (T) DEFAULTS.get(type);
    }
}

