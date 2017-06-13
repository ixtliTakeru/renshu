package g.takeru.renshu.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * Created by takeru on 2017/6/13.
 */

class KotlinActvity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun ld() {
        var view: View
        view.setOnClickListener({v -> v.bringToFront()})

    }
}
