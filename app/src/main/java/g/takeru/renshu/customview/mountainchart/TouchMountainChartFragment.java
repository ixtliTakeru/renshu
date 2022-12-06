package g.takeru.renshu.customview.mountainchart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import g.takeru.renshu.R;


public class TouchMountainChartFragment extends Fragment {

    private TouchChartView mChartView;

    public TouchMountainChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_touch_mountain_chart, container, false);
        mChartView = view.findViewById(R.id.touchMountainChartView_mainView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mChartView.setChartType(ChartData.TYPE_DAY);
        mChartView.setChartData(useDemoData(ChartData.TYPE_DAY));
    }

    private ArrayList<ChartData> useDemoData(int type){
        ArrayList<ChartData> demoData = new ArrayList<>();
        switch (type){
            case ChartData.TYPE_DAY:
                demoData.add(new ChartData(100, false, 0));
                demoData.add(new ChartData(200, false, 0));
                demoData.add(new ChartData(300, false, 0));
                demoData.add(new ChartData(150, true, 0));
                demoData.add(new ChartData(100, false, 0));
                demoData.add(new ChartData(200, false, 0));
                demoData.add(new ChartData(300, false, 0));
                demoData.add(new ChartData(150, false, 0));
                demoData.add(new ChartData(200, false, 0));
                demoData.add(new ChartData(230, false, 0));
                break;
        }

        return demoData;
    }
}
