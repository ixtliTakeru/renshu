package g.takeru.renshu.kotlin.leetcode;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import g.takeru.renshu.databinding.ListitemUserBinding
import g.takeru.renshu.kotlin.leetcode.`object`.Problem

/**
 * Created by takeru on 2018/3/19.
 */

internal class ProblemListAdapter(private var problemList : MutableList<Problem>) :
        RecyclerView.Adapter<ProblemListAdapter.ProblemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ProblemViewHolder =
        ProblemViewHolder(ListitemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder : ProblemViewHolder, position : Int){
        val problem = problemList[position]

        holder.binding.apply {
            listitemUserContainer.setOnClickListener{ problem.itemClickListener() }
            listitemUserName.text = "NO." + problem.index.toString()
            listitemUserAge.text = problem.name
        }
    }

    override fun getItemCount() : Int = problemList.size

    class ProblemViewHolder (val binding : ListitemUserBinding):
        RecyclerView.ViewHolder(binding.root)
}
