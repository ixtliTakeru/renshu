package g.takeru.renshu.leetcode

import timber.log.Timber

/**
 * Created by takeru on 2018/3/12.
 */
class TwoSum {

    /**
     * Given an array of integers, return indices of the two numbers such that they add up
     * to a specific target. You may assume that each input would have exactly one solution,
     * and you may not use the same element twice.
     *
     * Example:
     * Given nums = [2, 7, 11, 15], target = 9,
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     */

    fun testing() {
        val testData = intArrayOf(2, 7, 11, 15)
        val target = 9
        var result = TwoSum().solution1(testData, target)
        Timber.d("solution1 : [${result!![0]}, ${result!![1]}]")
        result = TwoSum().solution2(testData, target)
        Timber.d("solution2 : [${result!![0]}, ${result!![1]}]")
    }

    // Brute Force : Time complexity : O(n^2)
    fun solution1 (nums: IntArray, target: Int): IntArray? {
        for (i in nums.indices) {
            for (j in nums.indices) {
                if (nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }
        throw IllegalArgumentException("No two sum solution")
    }

    // One-pass Hash Table : Time complexity : O(n)
    fun solution2 (nums: IntArray, target: Int): IntArray? {
        var map = mutableMapOf<Int, Int?>()
        for (i in nums.indices) {
            var complement = target - nums[i]
            if (map.containsKey(complement)) {
                return intArrayOf(i, map[complement]!!)
            }
            map[nums[i]] = i
        }
        throw IllegalArgumentException("No two sum solution")
    }
}