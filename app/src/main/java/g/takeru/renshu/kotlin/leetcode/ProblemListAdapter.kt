package g.takeru.renshu.kotlin.leetcode;

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import g.takeru.renshu.R
import g.takeru.renshu.kotlin.leetcode.`object`.Problem
import kotlinx.android.synthetic.main.listitem_user.view.*

/**
 * Created by takeru on 2018/3/19.
 */

internal class ProblemListAdapter(private var problemList : MutableList<Problem>) :
        RecyclerView.Adapter<ProblemListAdapter.ProblemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ProblemViewHolder {
        return ProblemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.listitem_user,parent,false))
    }

    override fun onBindViewHolder(holder : ProblemViewHolder, position : Int){
        val problem = problemList[position]

        holder.container.setOnClickListener{ problem.itemClickListener() }
        holder.nameTv.text = "NO." + problem.index.toString()
        holder.ageTv.text = problem.name
    }

    override fun getItemCount() : Int = problemList.size

    class ProblemViewHolder (view : View): RecyclerView.ViewHolder(view){
        val container = itemView.listitem_user_container!!
        val nameTv = itemView.listitem_user_name!!
        val ageTv = itemView.listitem_user_age!!
    }
}
