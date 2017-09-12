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

class KotlinActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindView()
    }

    private fun bindView() {
        // Basic View Binding
        var textView: TextView? = null
        textView = findViewById(R.id.text) as TextView
        textView.setText("Basic View Binding")

        // Using Kotlin Android Extensions
        text.setText("Using Kotlin Android Extensions")

        // set click listener
        text.setOnClickListener(onClickListener)
        text.setOnClickListener{view -> Timber.d("click: " + view.id)}
    }

    private val onClickListener = View.OnClickListener {
        view -> Timber.d("click: " + view.id)
    }
}
