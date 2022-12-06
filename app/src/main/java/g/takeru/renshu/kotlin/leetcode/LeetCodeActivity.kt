package g.takeru.renshu.kotlin.leetcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import g.takeru.renshu.databinding.ActivityLeetcodeBinding
import g.takeru.renshu.kotlin.leetcode.`object`.Problem

/**
 * Created by takeru on 2018/3/13.
 */
class LeetCodeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLeetcodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // create problem list
        val problemList : MutableList<Problem> = ArrayList()
        problemList.add(Problem(1, "Two Sum") { TwoSum().testing() })
        problemList.add(Problem(2, "Add Two Numbers") { AddTwoNumbers().testing() })
        problemList.add(Problem(7, "Reverse Integer") { ReverseInteger().testing() })
        problemList.add(Problem(9, "Palindrome Number") { PalindromeNumber().testing() })
        problemList.add(Problem(206, "Reverse Linked List") { ReverseLinkedList().testing() })
        problemList.add(Problem(334, "Reverse String") { ReverseString().testing() })

        // not belong to LeetCode
        problemList.add(Problem(99999, "Minimum Depth of a Binary Tree") { MinimumDepthOfBinaryTree().testing() })


        // initial adapter
        val listAdapter = ProblemListAdapter(problemList)
        binding.recyclerViewProblemList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewProblemList.adapter = listAdapter
    }
}