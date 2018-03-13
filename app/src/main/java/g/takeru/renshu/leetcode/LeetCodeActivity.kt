package g.takeru.renshu.leetcode

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import g.takeru.renshu.R
import timber.log.Timber

/**
 * Created by takeru on 2018/3/13.
 */
class LeetCodeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // #1 Two Sum
        val testData = intArrayOf(2, 7, 11, 15)
        val target = 9
        var result = TwoSum().solution1(testData, target)
        Timber.d("[${result!![0]}, ${result!![1]}]")
        result = TwoSum().solution2(testData, target)
        Timber.d("[${result!![0]}, ${result!![1]}]")

        

    }
}