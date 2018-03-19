package g.takeru.renshu.leetcode

import timber.log.Timber


/**
 * Created by takeru on 2018/3/15.
 */
class PalindromeNumber {

    /**
     * Palindrome Number
     *
     * Determine whether an integer is a palindrome. Do this without extra space.
     *
     * hint:
     * Could negative integers be palindromes? (ie, -1)
     * If you are thinking of converting the integer to string, note the restriction of using
     * extra space.
     * You could also try reversing an integer. However, if you have solved the problem
     * "Reverse Integer", you know that the reversed integer might overflow. How would you
     * handle such case?
     * There is a more generic way of solving this problem.
     */

    fun testing() {
        var result = PalindromeNumber().solution1(12311)
        Timber.d("solution1 : $result")
        result = PalindromeNumber().solution2(12321)
        Timber.d("solution2 : $result")
        result = PalindromeNumber().solution3(12321)
        Timber.d("solution3 : $result")
    }


    // 0 is palindrome, negative integers not be palindromes
    fun solution1(x: Int): Boolean {
        val reverseInteger = ReverseInteger().solution(x)
        return x == reverseInteger
    }

    fun solution2(x: Int): Boolean {
        if (x<0 || x>Integer.MAX_VALUE) return false
        if (x<10) return true

        for (i in 0..x.toString().length/2) {
            if (x.toString()[i] != x.toString()[x.toString().length - i - 1]) {
                return false
            }
        }

        return true
    }

    fun solution3(x: Int): Boolean {
        if (x<0 || x>Integer.MAX_VALUE) return false
        if (x<10) return true

        // y is reverse integer
        var tmp = x
        var y = 0
        while (tmp>0) {
            y = y*10 + tmp%10
            tmp /= 10
        }

        return x == y
    }
}