package g.takeru.renshu.kotlin.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View



/**
 *  ref: http://www.gcssloop.com/gebug/bubble-sample
 */

data class Bubble (val x: Int,
                   val y: Int,
                   val speedX: Int,
                   val speedY: Int,
                   val radius: Int,
                   val color: Int)

class BubbleView : View {

//    private val mBubbleMaxRadius = 30          // 气泡最大半径 px
//    private val mBubbleMinRadius = 5           // 气泡最小半径 px
//    private val mBubbleMaxSize = 30            // 气泡数量
//    private val mBubbleRefreshTime = 20        // 刷新间隔
//    private val mBubbleMaxSpeedY = 5           // 气泡速度
//    private val mBubbleAlpha = 128             // 气泡画笔
//
//    private val mBottleWidth: Float = 0.toFloat()                 // 瓶子宽度
//    private val mBottleHeight: Float = 0.toFloat()                // 瓶子高度
//    private val mBottleRadius: Float = 0.toFloat()                // 瓶子底部转角半径
//    private val mBottleBorder: Float = 0.toFloat()                // 瓶子边缘宽度
//    private val mBottleCapRadius: Float = 0.toFloat()             // 瓶子顶部转角半径
//    private val mWaterHeight: Float = 0.toFloat()                 // 水的高度
//
//    private val mContentRectF: RectF? = null                // 实际可用内容区域
//    private val mWaterRectF: RectF? = null                  // 水占用的区域
//
//    private val mBottlePath: Path? = null                   // 外部瓶子
//    private val mWaterPath: Path? = null                    // 水
//
//    private val mBottlePaint: Paint? = null                 // 瓶子画笔
//    private val mWaterPaint: Paint? = null                  // 水画笔
//    private val mBubblePaint: Paint? = null                 // 气泡画笔

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
//        mBottlePaint = Paint()
    }

    fun startBubble() {
//        cupPaint
    }

    fun stopBubble() {

    }

    fun drawBubble() {

    }
}