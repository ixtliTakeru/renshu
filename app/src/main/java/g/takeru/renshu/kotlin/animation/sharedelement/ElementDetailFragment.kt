package g.takeru.renshu.kotlin.animation.sharedelement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import g.takeru.renshu.R

/**
 * Created by takeru on 2018/4/17.
 */
class ElementDetailFragment : Fragment(){

    fun newInstance() : ElementDetailFragment {
        val fragment = ElementDetailFragment()
        val args = Bundle()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_element_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}