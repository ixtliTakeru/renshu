package g.takeru.renshu.kotlin

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import g.takeru.renshu.R
import g.takeru.renshu.databinding.ActivityMainBinding
import g.takeru.renshu.kotlin.data.Day
import g.takeru.renshu.kotlin.data.Months
import g.takeru.renshu.kotlin.data.model.WikiResult
import g.takeru.renshu.kotlin.data.retrofit.ApiManager
import g.takeru.renshu.util.JsonUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber

/**
 * Created by takeru on 2017/6/13.
 */

class KotlinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        bindView()
    }

    private fun bindView() {
        // Basic View Binding
        var textView: TextView?
        //  for API level 25 and below
        textView = findViewById(R.id.text) as TextView
        //  for API level 26
        textView = findViewById(R.id.text)
        textView.setText("Basic View Binding")

        // Using Kotlin Android Extensions
        binding.text.setText("Using Kotlin Android Extensions")

        // set click listener
        binding.text.setOnClickListener( { view -> Timber.d("click: " + view.id) } )
        binding.text.setOnClickListener() { view -> Timber.d("click: " + view.id) }
        binding.text.setOnClickListener { view -> Timber.d("click: " + view.id) }
        binding.text.setOnClickListener(onClickListener1)
        //text.setOnClickListener(onClickListener2)
        //text.setOnClickListener(onClickListener3)

        // object copy
        var takeru = User("takeru", 35)
        Timber.d(takeru.toString())
        var takeruCopy = takeru.copy(age=25)
        Timber.d(takeruCopy.toString())

        // object revert
        val jane = User("Jane", 35)
        val (name, age) = jane
        println("$name, $age years of age")               // prints "Jane, 35 years of age"
        println("${jane.name}, ${jane.age} years of age") // prints "Jane, 35 years of age"
        Timber.d(name)
        Timber.d("$age")

        // singleton
        var s1 = Singleton.instance
        s1.text = "test"
        var s2 = Singleton.instance
        Timber.d(s2.text)

        // api test
        var apiManager = ApiManager.instance
        ApiManager.instance.hostSelectionInterceptor.setHost("en.wikipedia.org")
        apiManager.api
                .getDataFromWiki("query", "json", "search", "taiwan")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {response -> Timber.d("result: ${response.body()!!.query.searchinfo.totalhits}")},
                        {throwable ->
                            Timber.d(throwable.message)
                            throwable.printStackTrace() })

        // api test (using flatMap)
        // ref: https://ithelp.ithome.com.tw/articles/10197221
        apiManager.api
                .getDataFromWiki("query", "json", "search", "taiwan")
                .subscribeOn(Schedulers.io())
                .flatMap { response ->
                    Timber.d("result: ${response.body()!!.query.searchinfo.totalhits}")
                    Timber.d("return next api")
                    return@flatMap apiManager.api.getDataFromWiki(
                            "query", "json", "search", "usa")
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {response -> Timber.d("result: ${response.body()!!.query.searchinfo.totalhits}")},
                        {throwable ->
                            Timber.d(throwable.message)
                            throwable.printStackTrace() })

        // api test (using zip)
        Observable.zip(
                apiManager.api.getDataFromWiki("query", "json", "search", "taiwan"),
                apiManager.api.getDataFromWiki("query", "json", "search", "usa"),
                BiFunction<Response<WikiResult>, Response<WikiResult>, Int> {
                    result1, result2 ->
                    var hit1 = result1.body()!!.query.searchinfo.totalhits
                    var hit2 = result2.body()!!.query.searchinfo.totalhits
                    Timber.d("Observable.zip $hit1 $hit2")
                    hit1 + hit2
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {response -> Timber.d("result sum: $response")},
                        {throwable ->
                            Timber.d(throwable.message)
                            throwable.printStackTrace() })

        // api test (using zip with Observable List)
        var observableList = mutableListOf<Observable<Response<WikiResult>>>()
        observableList.add(apiManager.api.getDataFromWiki("query", "json", "search", "taiwan"))
        observableList.add(apiManager.api.getDataFromWiki("query", "json", "search", "usa"))
        observableList.add(apiManager.api.getDataFromWiki("query", "json", "search", "japan"))

        Observable.zip(observableList) { it ->
            var resultList = mutableListOf<WikiResult>()
            for (i in it.indices) resultList.add((it[i] as Response<WikiResult>).body()!!)
            return@zip resultList
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {response ->
                            Timber.d("result sum: ${response.size}")},
                        {throwable ->
                            Timber.d(throwable.message)
                            throwable.printStackTrace() })

        // enum
        Timber.d("${Day.Morning}")                      // prints MORNING
        Timber.d("${Day.Morning.ordinal}")              // 0
        Timber.d("${Day.Morning.name}")                 // prints Morning
        Timber.d("${Day.values().size}")                // prints 3
        Timber.d("${Day.valueOf("Morning")}")      // prints MORNING

        for (enum in Day.values()) {
            println(enum.name)
        }

        Timber.d("${Months.January.shorthand}")           // prints JAN
        Months.January.shorthand = "J."
        Timber.d("${Months.January.shorthand}")           // prints J.
        Months.February.printSomething()                          // prints Second month of the year.
        Timber.d(Months.February.hello)

        // load json file
        val userList = JsonUtil.LoadListFromRaw(this, R.raw.user_array, Array<User>::class.java)
        Timber.d(userList.size.toString() + "")

        // get interaction from two list
        var aList = mutableListOf<User>()
        aList.add(User("abc", 10))
        aList.add(User("avc", 20))
        aList.add(User("dd", 15))
        var bList = mutableListOf<User>()
        bList.add(User("bc", 10))
        bList.add(User("avc", 20))
        bList.add(User("dd", 15))
        var intersectList = aList.intersect(bList)
        Timber.d("intersectList: ${intersectList.size}")
        Timber.d("intersectList: $intersectList")

        // groupby
        // result: {a=[User(name=abc, age=10), User(name=avc, age=20)], o=[User(name=dd, age=15)]
        var a = aList.groupBy {
            if (it.name.startsWith("a", true)) "A"
            else "o"
        }
        Timber.d("groupby: ${a.toString()}")
    }

    // basic onClickListener
    private var onClickListener1 : View.OnClickListener = object : View.OnClickListener {
        override fun onClick(view: View) {
            Timber.d("onClickListener1: " + view.id)
        }
    }

    // convent to lambda
    private val onClickListener2 = View.OnClickListener {
        view -> Timber.d("onClickListener2: " + view.id)
    }

    // In kotlin, use a function as a parameter in another function
    private val onClickListener3 : ((View) -> Unit) = {
        View -> Timber.d("onClickListener3: " + View.id)
    }
}
