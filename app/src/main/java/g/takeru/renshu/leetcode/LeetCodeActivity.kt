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

        // 1. Two Sum
        val testData = intArrayOf(2, 7, 11, 15)
        val target = 9
        var no1Result = TwoSum().solution1(testData, target)
        Timber.d("[${no1Result!![0]}, ${no1Result!![1]}]")
        no1Result = TwoSum().solution2(testData, target)
        Timber.d("[${no1Result!![0]}, ${no1Result!![1]}]")

        // 2. Add Two Numbers


        // 7. Reverse Integer
        var no7Result = ReverseInteger().solution2(123)
        Timber.d("$no7Result")
        no7Result = ReverseInteger().solution(-123)
        Timber.d("$no7Result")
        no7Result = ReverseInteger().solution2(120)
        Timber.d("$no7Result")

        // 9. Palindrome Number
        var no9Result = PalindromeNumber().solution1(12311)
        Timber.d("$no9Result")
        no9Result = PalindromeNumber().solution2(12321)
        Timber.d("$no9Result")
        no9Result = PalindromeNumber().solution3(12321)
        Timber.d("$no9Result")
    }
}