package g.takeru.renshu.kotlin.rx

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class QuaryWikiFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(keyword: String) = QuaryWikiFragment().apply {
            arguments = Bundle().apply {
                putString("keyword", keyword)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}