package g.takeru.renshu.customview.mountainchart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

import g.takeru.renshu.R;


public class MountainChartFragment extends Fragment {

    private ArrayList<ChartData> mChartData;
    private MountainChartView mChartView;
    private LinearLayout mXAxisIndexLayout;
    private Button mTypeDay, mTypeWeek, mTypeMonth, mTypeYear;

    public static MountainChartFragment newInstance(String param1, String param2) {
        MountainChartFragment fragment = new MountainChartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public MountainChartFragment() {
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
        View view = inflater.inflate(R.layout.fragment_mountain_chart, container, false);
        mTypeDay = view.findViewById(R.id.mountainChartView_btn_day);
        mTypeWeek = view.findViewById(R.id.mountainChartView_btn_week);
        mTypeMonth = view.findViewById(R.id.mountainChartView_btn_month);
        mTypeYear = view.findViewById(R.id.mountainChartView_btn_year);
        mChartView = view.findViewById(R.id.mountainChartView_mainView);
        mXAxisIndexLayout = view.findViewById(R.id.mountainChartView_axis_x);

        mTypeDay.setOnClickListener(changeChartType);
        mTypeWeek.setOnClickListener(changeChartType);
        mTypeMonth.setOnClickListener(changeChartType);
        mTypeYear.setOnClickListener(changeChartType);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setChartType(ChartData.TYPE_DAY);
    }

    private void setChartType(int type){
        mChartView.setChartType(type);
        mChartView.setChartData(useDemoData(type));
        setXAxisIndexLayout(type);
    }

    private void setXAxisIndexLayout(int type){

        String[] indexArray = new String[]{};
        switch (type){
            case ChartData.TYPE_DAY:
                indexArray = getResources().getStringArray(R.array.mountain_chart_day);
                break;
            case ChartData.TYPE_WEEK:
                indexArray = getResources().getStringArray(R.array.mountain_chart_week);
                break;
            case ChartData.TYPE_MONTH:
                indexArray = getResources().getStringArray(R.array.mountain_chart_month);
                break;
            case ChartData.TYPE_YEAR:
                indexArray = getResources().getStringArray(R.array.mountain_chart_year);
                break;
        }

        mXAxisIndexLayout.removeAllViews();
        LayoutParams layoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);
        for (int i=0 ; i<indexArray.length ; i++) {
            TextView indexTextView = new TextView(getActivity());
            indexTextView.setLayoutParams(layoutParams);
            indexTextView.setGravity(Gravity.CENTER);
            indexTextView.setTextColor(getResources().getColor(android.R.color.black));
            indexTextView.setText(indexArray[i]);
            mXAxisIndexLayout.addView(indexTextView);
        }
    }

    View.OnClickListener changeChartType = view -> {
        int chartType = 0;
        switch (view.getId()){
            case R.id.mountainChartView_btn_day:
                chartType = ChartData.TYPE_DAY;
                break;
            case R.id.mountainChartView_btn_week:
                chartType = ChartData.TYPE_WEEK;
                break;
            case R.id.mountainChartView_btn_month:
                chartType = ChartData.TYPE_MONTH;
                break;
            case R.id.mountainChartView_btn_year:
                chartType = ChartData.TYPE_YEAR;
                break;
        }

        setChartType(chartType);
    };

    private ArrayList<ChartData> useDemoData(int type){
        ArrayList<ChartData> demoData = new ArrayList<>();
        switch (type){
            case ChartData.TYPE_DAY:
                demoData.add(new ChartData(100, false, 0));
                demoData.add(new ChartData(200, false, 0));
                demoData.add(new ChartData(300, false, 0));
                demoData.add(new ChartData(150, true, 0));
                break;
            case ChartData.TYPE_WEEK:
                demoData.add(new ChartData(100, false, 0));
                demoData.add(new ChartData(250, false, 0));
                demoData.add(new ChartData(300, false, 0));
                demoData.add(new ChartData(150, true, 0));
                demoData.add(new ChartData(100, false, 0));
                demoData.add(new ChartData(200, false, 0));
                demoData.add(new ChartData(400, false, 0));
                break;
            case ChartData.TYPE_MONTH:
                demoData.add(new ChartData(150, false, 0));
                demoData.add(new ChartData(350, false, 0));
                demoData.add(new ChartData(300, false, 0));
                demoData.add(new ChartData(200, true, 0));
                demoData.add(new ChartData(100, false, 0));
                break;
            case ChartData.TYPE_YEAR:
                demoData.add(new ChartData(100, false, 0));
                demoData.add(new ChartData(250, false, 0));
                demoData.add(new ChartData(300, false, 0));
                demoData.add(new ChartData(300, false, 0));
                demoData.add(new ChartData(150, false, 0));
                demoData.add(new ChartData(100, false, 0));
                demoData.add(new ChartData(200, false, 0));
                demoData.add(new ChartData(400, false, 0));
                demoData.add(new ChartData(100, false, 0));
                demoData.add(new ChartData(250, false, 0));
                demoData.add(new ChartData(300, false, 0));
                demoData.add(new ChartData(150, true, 0));
                demoData.add(new ChartData(100, false, 0));
                break;
        }

        return demoData;
    }
}
