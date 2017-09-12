package g.takeru.renshu;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by takeru on 2017/9/1.
 */

public class RenShuApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // enable Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
        }
    }
}
