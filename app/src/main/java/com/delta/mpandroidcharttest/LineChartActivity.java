package com.delta.mpandroidcharttest;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class LineChartActivity extends AppCompatActivity {

    LineChart mChart;
    //数据
    float[] yDatas = new float[]{
            98, 99, 100, 93, 95, 96, 92, 93, 97, 96, 86, 89, 88, 95, 98, 98, 99, 98, 97, 96, 98, 98, 99, 97
    };
    //x轴label
    String[] xlabel = new String[]{
            "08/02 08:00",
            "08/02 09:00",
            "08/02 10:00",
            "08/02 11:00",
            "08/02 12:00",
            "08/02 13:00",
            "08/02 14:00",
            "08/02 15:00",
            "08/02 16:00",
            "08/02 17:00",
            "08/02 18:00",
            "08/02 19:00",
            "08/02 20:00",
            "08/02 21:00",
            "08/02 22:00",
            "08/02 23:00",
            "08/03 00:00",
            "08/03 01:00",
            "08/03 02:00",
            "08/03 03:00",
            "08/03 04:00",
            "08/03 05:00",
            "08/03 06:00",
            "08/03 07:00"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_linechart);
        mChart = findViewById(R.id.line_chart);
        //图表设置数据
        setData(yDatas, xlabel);
    }

    private void setData(float[] yDatas, final String[] xlabel) {
        LineDataSet set1;
        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 0; i < yDatas.length; i++) {
            values.add(new Entry(i, yDatas[i], getResources().getDrawable(R.mipmap.ic_launcher)));
        }

        set1 = new LineDataSet(values, "达成率(%)");
        set1.setDrawIcons(false);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setFormLineWidth(1f);
        set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        set1.setFormSize(15.f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(dataSets);

        // set data
        mChart.setData(data);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xlabel[(int) value % xlabel.length];
            }
        });
        xAxis.setAxisMaximum(data.getXMax() + 0.25f);
        xAxis.setTextSize(3f);
        Description description = new Description();
        description.setText("达成率");
        description.setTextSize(20);
        description.setTextColor(Color.BLACK);
        description.setPosition(500, 500);
        mChart.setDescription(description);
    }

}


