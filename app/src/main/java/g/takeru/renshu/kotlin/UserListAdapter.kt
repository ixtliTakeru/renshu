package g.takeru.renshu.kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import g.takeru.renshu.R
import g.takeru.renshu.databinding.ListitemUserBinding

/**
 * Created by takeru on 2017/9/12.
 */

internal class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private lateinit var context: Context
    private lateinit var userList: MutableList<User>
    private lateinit var itemClickListener: (User)->Unit

    constructor()

    constructor(context: Context,
                userList: MutableList<User>,
                itemClickListener: (User)->Unit) {
        this.context = context
        this.userList = userList
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ListitemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        holder.itemView.setOnClickListener{itemClickListener(user)}
        holder.binding.apply {
            listitemUserName.text = user.name
            listitemUserAge.text = user.age.toString()
        }
    }

//    override fun getItemCount(): Int {
//        return userList.size
//    }

    // convert to expression body
    override fun getItemCount(): Int = userList.size

    class UserViewHolder (val binding: ListitemUserBinding)
        : RecyclerView.ViewHolder(binding.root)

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
