package g.takeru.renshu.util;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;

import java.lang.reflect.Method;

import timber.log.Timber;

/**
 * Created by takeru on 2018/3/31.
 */

public class SoftKeyboardStatusUtil {

    private Activity activity;
    private View rootView;

    private Handler handler;
    private Runnable runnable;

    private boolean keyboardListenersAttached = false;
    private int navigationBarHeight;

    private StatusLister statusLister;

    public interface StatusLister {
        void onShow(int keyboardHeight);
        void onHide();
    }

    public SoftKeyboardStatusUtil(Activity activity, StatusLister lister){
        this.activity = activity;
        this.rootView = activity.getWindow().getDecorView();
        this.statusLister = lister;
        this.handler = new Handler();
    }

    public void attach(){
        if (keyboardListenersAttached) {
            return;
        }

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);
        keyboardListenersAttached = true;
    }

    public void detach(){
        if (keyboardListenersAttached) {
            rootView.getViewTreeObserver().removeOnGlobalLayoutListener(keyboardLayoutListener);
        }
    }

    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener = () -> {

        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }

        runnable = () -> {
            if (activity == null || activity.isFinishing()) {
                return;
            }
            int resourceId = 0;
            Resources resources = activity.getResources();

            // navigation bar height
            navigationBarHeight = 0;
            boolean hasNavigationBar = checkDeviceHasNavigationBar(activity);
            if (hasNavigationBar)
                resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                navigationBarHeight = resources.getDimensionPixelSize(resourceId);
            }
            Timber.d("checkDeviceHasNavigationBar: " + hasNavigationBar);

            // status bar height
            int statusBarHeight = 0;
            resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
            }

            // display window size for the app layout
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

            // screen height - (user app height + status + nav) ..... if non-zero,
            // then there is a soft keyboard
            int keyboardHeight = rootView.getRootView().getHeight() - (statusBarHeight + navigationBarHeight + rect.height());
            Timber.d("rootViewHeight: " +  rootView.getRootView().getHeight());
            Timber.d("statusBarHeight: " +  statusBarHeight);
            Timber.d("navigationBarHeight: " +  navigationBarHeight);
            Timber.d("rectHeight: " +  rect.height());
            Timber.d("keyboardHeight: " +  keyboardHeight);

            if (keyboardHeight <= 0) {
                statusLister.onHide();
            } else {
                /**
                 *  when api level >= 21 and device has navigation bar, this above calculated
                 *  keyboard height that included navigation bar height.
                 */
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && hasNavigationBar)
                    keyboardHeight = keyboardHeight + navigationBarHeight;

                statusLister.onShow(keyboardHeight);
            }
        };

        handler.postDelayed(runnable, 300);
    };

    public boolean checkDeviceHasNavigationBar(Activity activity) {
        boolean hasNavigationBar = false;

        /**
         * using android system properties
         * ref: https://gist.github.com/ZQiang94/cec97ced06b3a4a8a2dee6ad0a5b6112
         *
         * compare app display and screen size (api level >= 17)
         * ref: https://stackoverflow.com/questions/14853039/how-to-tell-whether-an-android-device-has-hard-keys/14871974#14871974
         */

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            Display d = activity.getWindowManager().getDefaultDisplay();

            DisplayMetrics realDisplayMetrics = new DisplayMetrics();
            d.getRealMetrics(realDisplayMetrics);

            int realHeight = realDisplayMetrics.heightPixels;
            int realWidth = realDisplayMetrics.widthPixels;
            Timber.d("realHeight: " + realHeight + " realWidth: " + realWidth);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            d.getMetrics(displayMetrics);

            int displayHeight = displayMetrics.heightPixels;
            int displayWidth = displayMetrics.widthPixels;
            Timber.d("displayHeight: " + displayHeight + " displayWidth: " + displayWidth);

            hasNavigationBar =  (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
        } else {
            Resources resources = activity.getResources();
            int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (id > 0) {
                hasNavigationBar = resources.getBoolean(id);
            }
            try {
                Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
                Method m = systemPropertiesClass.getMethod("get", String.class);
                String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
                if ("1".equals(navBarOverride)) {
                    hasNavigationBar = false;
                } else if ("0".equals(navBarOverride)) {
                    hasNavigationBar = true;
                }
            } catch (Exception e) {
            }
        }

        return hasNavigationBar;
    }

    public int getNavigationBarHeight(){
        return navigationBarHeight;
    }
}
