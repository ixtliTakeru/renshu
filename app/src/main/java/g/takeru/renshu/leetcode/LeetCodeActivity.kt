package g.takeru.renshu.leetcode

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import g.takeru.renshu.R
import kotlinx.android.synthetic.main.activity_leetcode.*

/**
 * Created by takeru on 2018/3/13.
 */
class LeetCodeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leetcode)

        var problemList : MutableList<Problem> = ArrayList()
        problemList.add(Problem(1, "Two Sum", { TwoSum().testing() }))
        problemList.add(Problem(7, "Reverse Integer", { ReverseInteger().testing() }))
        problemList.add(Problem(9, "Palindrome Number", { PalindromeNumber().testing() }))
        problemList.add(Problem(334, "Reverse String", { ReverseString().testing() }))


        val listAdapter = ProblemListAdapter(problemList)
        recyclerViewProblemList.layoutManager = LinearLayoutManager(this)
        recyclerViewProblemList.adapter = listAdapter
    }
}