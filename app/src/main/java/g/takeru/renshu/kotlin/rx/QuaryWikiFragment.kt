package g.takeru.renshu.kotlin.rx

import android.os.Bundle
import androidx.fragment.app.Fragment

class QuaryWikiFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(keyword: String) = QuaryWikiFragment().apply {
            arguments = Bundle().apply {
                putString("keyword", keyword)
            }
        }
    }
}