package g.takeru.renshu.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import g.takeru.renshu.R
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

/**
 * Created by takeru on 2017/6/13.
 */

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        text.setText("Using Kotlin Android Extensions")

        // set click listener
        text.setOnClickListener({view -> Timber.d("click: " + view.id)})
        text.setOnClickListener() {view -> Timber.d("click: " + view.id)}
        text.setOnClickListener{view -> Timber.d("click: " + view.id)}
        text.setOnClickListener(onClickListener1)
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
