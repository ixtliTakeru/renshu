package g.takeru.renshu.architecture.mvp;

import android.content.Context;

/**
 * Created by takeru on 2017/10/11.
 */

public interface BaseView {
    Context getContext();
    void displayLoading(boolean show);
}
