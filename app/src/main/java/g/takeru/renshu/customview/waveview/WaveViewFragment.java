package g.takeru.renshu.customview.waveview;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import g.takeru.renshu.R;

public class WaveViewFragment extends Fragment {

    private WaveView mWaveView;
    private SensorManager mSensorManager;

    private TextView mXValueView, mYValueView, mZValueView;

    public static WaveViewFragment newInstance() {
        WaveViewFragment fragment = new WaveViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public WaveViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wave_view, container, false);
        mWaveView = view.findViewById(R.id.waveview_WaveView);
        mXValueView = view.findViewById(R.id.waveview_sensor_x);
        mYValueView = view.findViewById(R.id.waveview_sensor_y);
        mZValueView = view.findViewById(R.id.waveview_sensor_z);

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // register sensor manager
        mSensorManager.registerListener(sensorEventListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onPause() {
        // unregister sensor manager
        mSensorManager.unregisterListener(sensorEventListener);
        super.onPause();
    }

    SensorEventListener sensorEventListener = new SensorEventListener(){
        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            //Display Value
            float[] values = event.values;
            mXValueView.setText("X：" + String.valueOf(values[0]));
            mYValueView.setText("Y：" + String.valueOf(values[1]));
            mZValueView.setText("Z：" + String.valueOf(values[2]));

            // get sensor type from real device
            int sensorType = event.sensor.getType();
            switch (sensorType) {
                case Sensor.TYPE_ACCELEROMETER:
                    //using z angle and redraw view
                    float zAngle = values[0];
                    mWaveView.mZAngle = zAngle;
                    mWaveView.postInvalidate();
                    break;
            }
        }
    };

}
