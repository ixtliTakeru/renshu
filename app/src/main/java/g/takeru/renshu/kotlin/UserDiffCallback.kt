package g.takeru.renshu.kotlin

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by takeru on 2018/3/6.
 */
class UserDiffCallback(private val oldList: List<User>,
                       private val newList: List<User>) : DiffUtil.Callback() {


    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].age == newList[newItemPosition].age
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldRecipientName = oldList[oldItemPosition].name
        val newRecipientName = newList[newItemPosition].name

        return oldRecipientName.equals(newRecipientName, ignoreCase = true)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}