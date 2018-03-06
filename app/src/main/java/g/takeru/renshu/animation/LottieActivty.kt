package g.takeru.renshu.animation

import android.animation.Animator
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.airbnb.lottie.SimpleColorFilter
import g.takeru.renshu.R
import g.takeru.renshu.kotlin.User
import kotlinx.android.synthetic.main.activity_lottie.*


/**
 * Created by takeru on 2017/11/21.
 */
class LottieActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)

        // Adding a color filter to the whole view
        var colorFilter = SimpleColorFilter(Color.RED)

        // control speed
//        val animator = ValueAnimator.ofFloat(0f, 1f).setDuration(10000)

        // lottie animation
        lottieAnimationView2.setAnimation("lottie/unlock.json")
        lottieAnimationView2.loop(true)
//        lottieAnimationView2.scale = minOf(scale, 1f)
        lottieAnimationView2.addColorFilter(colorFilter)
        lottieAnimationView2.playAnimation()

//        Timber.d("Frame: " + lottieAnimationView2.scale);

        // TODO progress seekBar
        scaleSeekBar.progress = (lottieAnimationView2.scale * 50F).toInt()
        scaleSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                lottieAnimationView2.scale = progress / 50F
            }
        })

//        lottieAnimationView2.addAnimatorListener(AnimatorListenerAdapter(
//                startRecordingDroppedFrames(),
//                onEnd = {
//                    recordDroppedFrames()
//                    postUpdatePlayButtonText()
//                    animationView.performanceTracker?.logRenderTimes()
//                },
//                onCancel = { postUpdatePlayButtonText() },
//                onRepeat =  {
//                    animationView.performanceTracker?.logRenderTimes()
//                    animationView.performanceTracker?.clearRenderTimes()
//                    recordDroppedFrames()
//                    startRecordingDroppedFrames()
//                }
//        ))

        var tim : User = User("tim", 20)
        var jack = tim.copy(age = 11)

        var a : String = "aaa"
        a = "bbb"
    }

    private var listener: SeekBar.OnSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {

        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {

        }
    }

    private open class AnimatorListenerAdapter(
            val onStart: ((Animator) -> Unit)? = null,
            val onRepeat: ((Animator) -> Unit)? = null,
            val onEnd: ((Animator) -> Unit)? = null,
            val onCancel: ((Animator) -> Unit)? = null
    ): Animator.AnimatorListener {

        override fun onAnimationStart(animation: Animator) = onStart?.invoke(animation) ?: Unit
        override fun onAnimationRepeat(animation: Animator) = onRepeat?.invoke(animation) ?: Unit
        override fun onAnimationEnd(animation: Animator) = onEnd?.invoke(animation) ?: Unit
        override fun onAnimationCancel(animation: Animator) = onCancel?.invoke(animation) ?: Unit
    }

    private var startRecordingDroppedFrames : ((Animator) -> Unit) = {

    }

    private open class test : Animator.AnimatorListener {

        override fun onAnimationStart(animator: Animator) {

        }

        override fun onAnimationEnd(animator: Animator) {

        }

        override fun onAnimationCancel(animator: Animator) {

        }

        override fun onAnimationRepeat(animator: Animator) {

        }
    }
}