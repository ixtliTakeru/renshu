package g.takeru.renshu.customview.mountainchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;

import g.takeru.renshu.R;

public class MountainChartView extends View {

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

    public MountainChartView(Context context) {
        super(context);
    }

    public MountainChartView(Context context, AttributeSet attrs) {
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
        for (int i=0 ; i<mChartType ; i++){
            mTriangleStartX = mSpaceWidth*i - mSpaceWidth/4;
            mTriangleEndX = mSpaceWidth*i + (mSpaceWidth + mSpaceWidth/4);
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
        canvas.drawLine(0, mViewHeight, mViewWidth, mViewHeight, mLinePaint);
        //draw x-axis index indicator
        for (int i=1 ; i<mChartType ; i++)
            canvas.drawLine(mSpaceWidth*i, mViewHeight-25, mSpaceWidth*i, mViewHeight, mLinePaint);
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
