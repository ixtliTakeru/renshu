package g.takeru.renshu.kotlin

import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import g.takeru.renshu.R
import g.takeru.renshu.databinding.ActivityListBinding
import org.jetbrains.anko.toast
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by takeru on 2017/9/11.
 */
class SortListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    private var userList : MutableList<User> = ArrayList()
    private var adapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        binding = ActivityListBinding.inflate(layoutInflater)

        userList.add(User("Takeru", 20))
        userList.add(User("Emi", 22))
        userList.add(User("Takumi", 18))
        userList.add(User("Fukuyama", 19))
        userList.add(User("Rae", 40))
        userList.add(User("Naomi", 35))
        userList.add(User("Aria", 30))
        userList.add(User("Kimura", 37))
        userList.add(User("Aoi", 21))
        userList.add(User("Emi", 32))
        userList.add(User("Takeru", 31))

        sortUserList2(userList)

        for (i: Int in 0 until userList.size){
            Timber.d("user: " + userList[i].name + " " + userList[i].age)
        }

        adapter = UserListAdapter(this, userList, itemClickListener = onClickListener)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // add divider with horizontal margin
        val divider = ContextCompat.getDrawable(this, R.drawable.divider_line_dark)!!
        val dividerWithMargin = InsetDrawable(divider, 40, 0, 40, 0)
        val itemDecor = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecor.setDrawable(dividerWithMargin)
        binding.recyclerView.addItemDecoration(itemDecor)

        // set btn click event
        binding.addBtn.setOnClickListener(addUserClickListener)
        binding.delBtn.setOnClickListener(delUserClickListener)
        binding.changeBtn.setOnClickListener(changeUserClickListener)
    }

    private fun sortUserList(list: MutableList<User>): MutableList<User>{
        Collections.sort(list, countryComparator)
        return list
    }

    private fun sortUserList2(list: MutableList<User>): MutableList<User>{
        list.sortBy { it.age }
        return list
    }


    private val countryComparator = Comparator<User>{lhs, rhs ->
        val compareName = lhs.name.compareTo(rhs.name)
        if (compareName == 0) {
            return@Comparator lhs.age.compareTo(rhs.age)
        }
        return@Comparator compareName
    }

    private val onClickListener : ((User) -> Unit) = {
        toast(it.name)
    }

    private val changeUserClickListener : ((View) -> Unit) = {
        val newList = ArrayList<User>()
        if (adapter.itemCount < 4) {
            newList.add(User("Naomi", 35))
            newList.add(User("Aria", 30))
            newList.add(User("Takumi", 18))
            newList.add(User("Fukuyama", 19))
            newList.add(User("Rae", 40))
        }
        else {
            newList.add(User("Takumi", 18))
            newList.add(User("Takeru", 20))
            newList.add(User("Emi", 22))
        }
        adapter.updateUserList(newList)
    }

    private val addUserClickListener : ((View) -> Unit) = {
        adapter.addUser(User("aaa", 22), 3)
    }

    private val delUserClickListener : ((View) -> Unit) = {
        if (adapter.itemCount > 3)
            adapter.deleteUser(3)
    }


}