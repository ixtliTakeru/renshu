package g.takeru.renshu.kotlin.customview

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class NumberKeyBoardView : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    private var enable = true
//    private val keyIds = intArrayOf(
//            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
//            R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9,
//            R.id.button_dot)
//    private val containerIds = intArrayOf(
//            R.id.button_0_container, R.id.button_1_container, R.id.button_2_container,
//            R.id.button_3_container, R.id.button_4_container, R.id.button_5_container,
//            R.id.button_6_container, R.id.button_7_container, R.id.button_8_container,
//            R.id.button_9_container, R.id.button_dot_container, R.id.button_delete_container)


}

