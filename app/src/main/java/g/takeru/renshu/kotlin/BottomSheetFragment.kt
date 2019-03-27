package g.takeru.renshu.kotlin

import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*

class BottomSheetFragment : Fragment() {

    fun newInstance() : BottomSheetFragment {
        val fragment = BottomSheetFragment()
        val args = Bundle()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(g.takeru.renshu.R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottomSheetBtn.setOnClickListener { showBottomSheet() }
    }

    private fun showBottomSheet() {
        val dialog = BottomSheetDialog(activity!!)
        val view = layoutInflater.inflate(g.takeru.renshu.R.layout.dialog_bottom_sheet, null)
        dialog.setContentView(view)
        dialog.show()
    }
}