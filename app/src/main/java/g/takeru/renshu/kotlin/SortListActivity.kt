package g.takeru.renshu.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import g.takeru.renshu.R
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.anko.toast
import timber.log.Timber
import java.util.*


/**
 * Created by takeru on 2017/9/11.
 */
class SortListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val userList = ArrayList<User>()
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

//        val adapter = UserListAdapter(this, userList, {user -> Timber.d(user.name)})
        val adapter = UserListAdapter(userList, onClickListener)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun sortUserList(list: ArrayList<User>): ArrayList<User>{
        Collections.sort(list, countryComparator)
        return list
    }


    private val countryComparator = Comparator<User>{ lhs, rhs ->
        val compareName = lhs.name.compareTo(rhs.name)
        if (compareName == 0) {
            return@Comparator lhs.age.compareTo(rhs.age)
        }
        return@Comparator compareName
    }

    private val onClickListener : ((User) -> Unit) = {
        user -> toast(user.name)
    }
}