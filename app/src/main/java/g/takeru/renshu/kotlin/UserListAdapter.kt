package g.takeru.renshu.kotlin

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import g.takeru.renshu.R
import kotlinx.android.synthetic.main.listitem_user.view.*

/**
 * Created by takeru on 2017/9/12.
 */

internal class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private lateinit var userList: MutableList<User>
    private lateinit var itemClickListener: (User)->Unit

    constructor()

    constructor(userList: MutableList<User>,
                itemClickListener: (User)->Unit) {
        this.userList = userList
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.UserViewHolder {
        return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.listitem_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        holder.container.setOnClickListener{itemClickListener(user)}
        holder.nameTv.text = user.name
        holder.ageTv.text = user.age.toString()
    }

//    override fun getItemCount(): Int {
//        return userList.size
//    }

    // convert to expression body
    override fun getItemCount(): Int = userList.size

    class UserViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val container = itemView.listitem_user_container
        val nameTv = itemView.listitem_user_name
        val ageTv = itemView.listitem_user_age
    }

    fun addUser(user: User, index: Int) {
        userList.add(index, user)
        this.notifyItemInserted(index)
    }

    fun deleteUser(index: Int) {
        userList.removeAt(index)
        this.notifyItemRemoved(index)
    }

    fun updateUserList(newList : List<User>) {
        var diffCallback = UserDiffCallback(userList, newList)
        var diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)

        userList.clear()
        userList.addAll(newList)
    }
}
