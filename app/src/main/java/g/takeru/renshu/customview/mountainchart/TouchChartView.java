package g.takeru.renshu.customview.mountainchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import g.takeru.renshu.R;

public class TouchChartView extends View {

    public static final int DEFAULT_MOUNTAIN_HEIGHT = 200;

    private int mViewWidth, mViewHeight;
    private Paint mLinePaint, mTrianglePaint;
    private int mChartType;
    private ArrayList<ChartData> mChartData;

    private int mSpaceWidth;
    private int mMaxMountainHeight;
    private int mTriangleStartX, mTriangleEndX;
    private int mTrianglePeakX, mTrianglePeakY;
    private Path mMountainPath;

    //move
    protected float mDownX;
    protected float mCurrentX;
    protected float mOffsetX;
    protected int mXAxisOffset, mTotalOffset;

    public TouchChartView(Context context) {
        super(context);
    }

    public TouchChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialView();
    }

    private void initialView(){
        setLinePaint();
        setTrianglePaint();
        mMaxMountainHeight = (int)(DEFAULT_MOUNTAIN_HEIGHT * getScreenDensity());
//        Log.d("MountainChartView", "mMaxMountainHeight: " + mMaxMountainHeight);
    }

    public void setChartType(int type){
        this.mChartType = type;
        mSpaceWidth = mViewWidth/mChartType;
    }

    public void setChartData(ArrayList<ChartData> chartData){
        this.mChartData = chartData;
        calculateMountainHeight();
        invalidate();
    }

    public void setLinePaint(){
        mLinePaint = new Paint();
        mLinePaint.setColor(getResources().getColor(android.R.color.black));
        mLinePaint.setStrokeWidth((float) 5.0);
    }

    public void setTrianglePaint(){
        mTrianglePaint = new Paint();
//        mTrianglePaint.setColor(getResources().getColor(R.color.waveLine_30));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = getWidth();
        mViewHeight = getHeight();
//        Log.d("MountainChartView", "mViewWidth: " + mViewWidth + " mViewHeight: " + mViewHeight);
        mSpaceWidth = mViewWidth/mChartType;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw triangle(Mountain)
        for (int i=0 ; i<mChartData.size() ; i++){
            mTriangleStartX = mTotalOffset + mSpaceWidth*i - mSpaceWidth/4;
            mTriangleEndX = mTotalOffset + mSpaceWidth*i + (mSpaceWidth + mSpaceWidth/4);
            mTrianglePeakX = (mTriangleEndX + mTriangleStartX)/2;
            mTrianglePeakY = mViewHeight - mChartData.get(i).getMountainHeight();

            //
            mMountainPath = new Path();
            mMountainPath.moveTo(mTriangleStartX, mViewHeight);
            mMountainPath.lineTo(mTrianglePeakX, mTrianglePeakY);
            mMountainPath.lineTo(mTriangleEndX, mViewHeight);
            mMountainPath.close();
            if (mChartData.get(i).isNow())
                mTrianglePaint.setColor(getResources().getColor(R.color.waveLine_80));
            else
                mTrianglePaint.setColor(getResources().getColor(R.color.waveLine_30));
            canvas.drawPath(mMountainPath, mTrianglePaint);

            //draw peak circle and value
            if (mChartData.get(i).getMountainHeight() == mMaxMountainHeight) {
                canvas.drawCircle(mTrianglePeakX, mTrianglePeakY, 8, mLinePaint);
                mLinePaint.setTextSize(24);
                mLinePaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText("Peak", mTrianglePeakX, mTrianglePeakY-20, mLinePaint);
            }
        }

        //draw x-axis
        canvas.drawLine(0 + mTotalOffset, mViewHeight, mViewWidth, mViewHeight, mLinePaint);
        //draw x-axis index indicator
        for (int i=1 ; i<mChartData.size() ; i++)
            canvas.drawLine(mSpaceWidth*i + mTotalOffset, mViewHeight-25, mSpaceWidth*i + mTotalOffset, mViewHeight, mLinePaint);
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try{
            switch (event.getActionMasked()){
                case MotionEvent.ACTION_DOWN:
                    mDownX = event.getX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCurrentX = event.getX();
                    mOffsetX = mCurrentX - mDownX;
                    mXAxisOffset = (int) mOffsetX;
                    mDownX = mCurrentX;
                    mTotalOffset += mXAxisOffset;
                    Log.d("", "mTotalOffset: " + mTotalOffset);

                    if ( mTotalOffset >= 0 )
                        mTotalOffset = 0;

                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
        }
        catch(IllegalArgumentException ex) {
        }

        return true;
    }

    private float getScreenDensity(){
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return metrics.density;
    }

    private void calculateMountainHeight(){
        int maxValue = 0;
        for (int i=0 ; i<mChartData.size() ; i++){
            if (mChartData.get(i).getValue() > maxValue)
                maxValue = mChartData.get(i).getValue();
        }

        for (int i=0 ; i<mChartData.size() ; i++){
            int mountainHeight = (int)((float)mMaxMountainHeight * ((float)mChartData.get(i).getValue()/(float)maxValue));
            mChartData.get(i).setMountainHeight(mountainHeight);
        }
    }


}
