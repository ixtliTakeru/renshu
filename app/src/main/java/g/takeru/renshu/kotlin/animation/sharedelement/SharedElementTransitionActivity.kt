package g.takeru.renshu.kotlin.animation.sharedelement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import g.takeru.renshu.R

/**
 * Created by takeru on 2018/4/17.
 *
 * shared-element-transitions
 * ref: https://android-developers.googleblog.com/2018/02/continuous-shared-element-transitions.html
 */


class SharedElementTransitionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)

        replaceFragment(GridViewFragment().newInstance())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}