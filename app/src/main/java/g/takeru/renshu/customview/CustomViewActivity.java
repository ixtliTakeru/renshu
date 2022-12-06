package g.takeru.renshu.customview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import g.takeru.renshu.R;
import g.takeru.renshu.customview.blur.BlurFragment;
import g.takeru.renshu.customview.mountainchart.MountainChartFragment;
import g.takeru.renshu.customview.mountainchart.TouchMountainChartFragment;
import g.takeru.renshu.customview.waveview.WaveViewFragment;
import timber.log.Timber;

/**
 * Created by takeru on 2018/3/22.
 */

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.custom_view_more:
                Timber.d("custom_view_more");
                View view = findViewById(R.id.custom_view_more);
                showListPopupMenu(view);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showListPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.custom_view_list_popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            Fragment fragment = null;
            int titleResId = 0;
            switch (item.getItemId()) {
                case R.id.custom_view_list_image_blur:
                    fragment = new BlurFragment();
                    titleResId = R.string.custom_view_image_blur;
                    break;
                case R.id.custom_view_list_wave_view:
                    fragment = new WaveViewFragment();
                    titleResId = R.string.custom_view_wave_view;
                    break;
                case R.id.custom_view_list_mountain_chart:
                    fragment = new MountainChartFragment();
                    titleResId = R.string.custom_view_mountain_chart;
                    break;
                case R.id.custom_view_list_touch_mountain_chart:
                    fragment = new TouchMountainChartFragment();
                    titleResId = R.string.custom_view_touch_mountain_chart;
                    break;
            }

            if (fragment != null) {
                replaceFragment(fragment);
                getSupportActionBar().setTitle(titleResId);
            }
            return true;
        });
        popupMenu.show();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
