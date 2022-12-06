package g.takeru.renshu.kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import g.takeru.renshu.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : Fragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    val binding get() = _binding!!

    fun newInstance() : BottomSheetFragment {
        val fragment = BottomSheetFragment()
        val args = Bundle()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.bottomSheetBtn.setOnClickListener { showBottomSheet() }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun showBottomSheet() {
        val dialog = BottomSheetDialog(requireActivity())
        val view = layoutInflater.inflate(g.takeru.renshu.R.layout.dialog_bottom_sheet, null)
        dialog.setContentView(view)
        dialog.show()
    }
}