package g.takeru.renshu.kotlin.animation.sharedelement

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import g.takeru.renshu.R
import g.takeru.renshu.databinding.ComponentRecycleViewBinding


/**
 * Created by takeru on 2018/4/17.
 */
class GridViewFragment : Fragment() {

    private var _binding: ComponentRecycleViewBinding? = null
    val binding get() = _binding!!

    fun newInstance() : GridViewFragment {
        val fragment = GridViewFragment()
        val args = Bundle()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComponentRecycleViewBinding.inflate(inflater, container, false)
            .also { _binding = it }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // prepare date
        val colorList : MutableList<Int> = ArrayList()
        colorList.add(android.R.color.holo_orange_light)
        colorList.add(android.R.color.holo_red_dark)
        colorList.add(android.R.color.darker_gray)
        colorList.add(android.R.color.holo_blue_light)
        colorList.add(android.R.color.black)
        colorList.add(android.R.color.holo_green_dark)
        colorList.add(android.R.color.holo_blue_dark)
        colorList.add(android.R.color.holo_green_light)
        colorList.add(android.R.color.holo_orange_dark)
        colorList.add(android.R.color.white)

        // setup grid view
        val gridLayoutManager = GridLayoutManager(activity, 2)
        binding.componentRecyclerView.layoutManager = gridLayoutManager
        binding.componentRecyclerView.adapter = ElementRecyclerViewAdapter(colorList, onClickListener)
    }

    private val onClickListener : ((ImageView) -> Unit) = {
//        activity!!.toast("$it")
        gotoElementDetail(it)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun gotoElementDetail(imageView : ImageView){
        this.fragmentManager
                ?.beginTransaction()
//                ?.setReorderingAllowed(true) // setAllowOptimization before 26.1.0
//                ?.addSharedElement(imageView, imageView.transitionName)
                ?.replace(R.id.fragment_container,
                        ElementDetailFragment().newInstance(),
                        ElementDetailFragment::class.java.name)
                ?.addToBackStack(null)
                ?.commit()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}