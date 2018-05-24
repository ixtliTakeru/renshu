package g.takeru.renshu;

import android.app.Application;
import android.support.annotation.Nullable;

import timber.log.Timber;

/**
 * Created by takeru on 2017/9/1.
 */

public class RenShuApplication extends Application{

    private static RenShuApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();

        // enable Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
        }

        instance = this;
    }

    @Nullable
    public static RenShuApplication getInstance() {
        return instance;
    }
}
