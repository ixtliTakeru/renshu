package g.takeru.renshu.customview.waveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import g.takeru.renshu.R;

public class WaveView extends View {

    private Paint mLinePaint, mGradientPaint;
    private Shader mGradientShader;
    public int mViewWidth, mViewHeight;

    public float mZAngle = 0;
    public float mDefaultLineHeight = 600;
    public float startY = 0;
    public float endY = 0;

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLinePaint();
        setGradientPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = getWidth();
        mViewHeight = getHeight();
//        Log.d("WaveView", "mViewWidth: " + mViewWidth + " mViewHeight: " + mViewHeight);
    }

    public void setLinePaint(){
        mLinePaint = new Paint();
        mLinePaint.setColor(getResources().getColor(R.color.waveLine_80));
        mLinePaint.setStrokeWidth((float) 10.0);
    }

    public void setGradientPaint(){
        mGradientPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //set startY, endY
        startY= mDefaultLineHeight - mZAngle *5;
        endY= mDefaultLineHeight + mZAngle *5;

        // draw WaterLine
        canvas.drawLine(0, startY+10, 1080, endY+10, mLinePaint);

        // set gradient color
        float gradientStartY;
        if (startY<endY)
            gradientStartY = startY;
        else
            gradientStartY = endY;

        mGradientShader = new LinearGradient(0, gradientStartY, 0, 1920,
//                new int[]{Color.TRANSPARENT, R.color.waveLine_100},
                new int[]{R.color.waveLine_100, Color.TRANSPARENT},
                null,
                Shader.TileMode.REPEAT);
        mGradientPaint.setShader(mGradientShader);

        //DrawSquare
        Path path1=new Path();
        path1.moveTo(0, startY);
        path1.lineTo(mViewWidth, endY);
        path1.lineTo(mViewWidth, mViewHeight);
        path1.lineTo(0, mViewHeight);
        path1.close();
        canvas.drawPath(path1, mGradientPaint);
    }
}
