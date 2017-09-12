package g.takeru.renshu.kotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import g.takeru.renshu.R
import kotlinx.android.synthetic.main.listitem_user.view.*

/**
 * Created by takeru on 2017/9/12.
 */

internal class UserListAdapter(private val context: Context, private val userList: List<User>,
                               private val itemClickListener: (User)->Unit)
    : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.UserViewHolder {
        return UserViewHolder(
                LayoutInflater.from(context).inflate(R.layout.listitem_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        holder.container.setOnClickListener{itemClickListener(user)}
        holder.nameTv.text = user.name
        holder.ageTv.text = user.age.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val container = itemView.listitem_user_container
        val nameTv = itemView.listitem_user_name
        val ageTv = itemView.listitem_user_age
    }
}
