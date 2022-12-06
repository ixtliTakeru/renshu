package g.takeru.renshu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import g.takeru.renshu.databinding.ActivityMainBinding
import g.takeru.renshu.kotlin.leetcode.LeetCodeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, LeetCodeActivity::class.java)
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
//        Intent intent = new Intent(this, FragmentContainerActivity.class);
        startActivity(intent)
    }
}