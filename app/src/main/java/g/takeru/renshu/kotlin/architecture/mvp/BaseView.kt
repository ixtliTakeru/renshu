package g.takeru.renshu.kotlin.architecture.mvp

import android.content.Context

/**
 * Created by takeru on 2017/10/11.
 */

interface BaseView {
    val context: Context
    fun displayLoading(show: Boolean)
}
