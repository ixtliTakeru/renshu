package g.takeru.renshu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import g.takeru.renshu.kotlin.FragmentContainerActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text) TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        Intent intent = new Intent(this, LeetCodeActivity.class);
//        Intent intent = new Intent(this, CustomViewActivity.class);
//        Intent intent = new Intent(this, DateTimeUtilSampleActivity.class);
//        Intent intent = new Intent(this, SharedElementTransitionActivity.class);
//        Intent intent = new Intent(this, KotlinActivity.class);
//        Intent intent = new Intent(this, RxJava2Activity.class);
//        Intent intent = new Intent(this, RxKotlinActivity.class);
//        Intent intent = new Intent(this, DataModelTestActivity.class);
//        Intent intent = new Intent(this, LottieActivity.class);
//        Intent intent = new Intent(this, SortListActivity.class);
//        Intent intent = new Intent(this, PinCodeActivity.class);
//        Intent intent = new Intent(this, RippleActivity.class);
        Intent intent = new Intent(this, FragmentContainerActivity.class);
        startActivity(intent);
    }
}
