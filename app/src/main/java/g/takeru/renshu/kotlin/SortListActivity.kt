package g.takeru.renshu.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import g.takeru.renshu.R
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.anko.toast
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by takeru on 2017/9/11.
 */
class SortListActivity: AppCompatActivity() {

    private var userList : MutableList<User> = ArrayList()
    private var adapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

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

        sortUserList(userList)

        for (i: Int in 0 until userList.size){
            Timber.d("user: " + userList[i].name + " " + userList[i].age)
        }


//        val adapter = UserListAdapter(userList, {user -> Timber.d(user.name)})
//        val adapter = UserListAdapter(userList, onClickListener)
        adapter = UserListAdapter(userList, itemClickListener = {onClickListener})

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        addBtn.setOnClickListener(addUserClickListener)
        delBtn.setOnClickListener(delUserClickListener)
        changeBtn.setOnClickListener(changeUserClickListener)
    }

    private fun sortUserList(list: MutableList<User>): MutableList<User>{
        Collections.sort(list, countryComparator)
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