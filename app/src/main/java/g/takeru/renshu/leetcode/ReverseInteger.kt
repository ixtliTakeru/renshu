package g.takeru.renshu.leetcode

import timber.log.Timber

/**
 * Created by takeru on 2018/3/13.
 */
class ReverseInteger {

    /**
     * Reverse Integer
     *
     * Given a 32-bit signed integer, reverse digits of an integer.
     *
     * Example 1:
     * Input: 123
     * Output:  321
     *
     * Example 2:
     * Input: -123
     * Output: -321
     *
     * Example 3:
     * Input: 120
     * Output: 21
     */

    // hint: using long to save 32-bit integer

    fun solution(x: Int): Int {
        val sign = if (x > 0) 1 else -1
        var originString : String = Math.abs(x).toString()
        var reverseString = StringBuilder(originString).reverse().toString()
        var reverseNum = reverseString.toLong() * sign

        if (reverseNum > Integer.MAX_VALUE || reverseNum < Integer.MIN_VALUE) {
            return 0
        } else {
            return reverseNum.toInt()
        }
    }

    fun solution2(y: Int): Int {
        var result = 0
        var x = y

        while (x != 0) {
            Timber.d("-- while start")
            var tail = x % 10
            var newResult = result * 10 + tail
            Timber.d("tail $tail, result $result, newResult $newResult")

            if ((newResult - tail) / 10 != result)
                return 0

            result = newResult
            x /= 10
        }

        return result
    }
}