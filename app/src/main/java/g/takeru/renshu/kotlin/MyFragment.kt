package g.takeru.renshu.kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.jetbrains.anko.bundleOf

class MyFragment : Fragment() {

    // Using the NewInstance Pattern in Kotlin
    // ref: https://medium.com/@azjkjensen/using-the-newinstance-pattern-in-kotlin-e40c1b4ba1ef
    companion object {

        private val ARG_ID = "id"
        private val ARG_CODE = "code"
        private val ARG_NAME = "name"

        @JvmStatic
        fun newInstance(id: Int) : MyFragment {
            val args = Bundle()
            args.putInt(ARG_ID, id)
            val fragment = MyFragment()
            fragment.arguments = args
            return fragment
        }

        @JvmStatic
        fun newInstance(code: String) = MyFragment().apply {
            arguments = Bundle(1).apply {
                putString(ARG_CODE, code)
            }
        }

        @JvmStatic
        fun newInstance(code: String, name: String) = MyFragment().apply {
            arguments = bundleOf(
                    ARG_CODE to code,
                    ARG_NAME to name
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var id = arguments?.getInt(ARG_ID)
        var code = arguments?.getString(ARG_CODE)
        var name = arguments?.getString(ARG_NAME)
    }
}