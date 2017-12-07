package com.delta.mpandroidcharttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class HorizontalBarChartActivity extends AppCompatActivity {

    HorizontalBarChart mChart;
    float[] xDatas = new float[]{
            45.89f, 99.7f, 25f
    };
    String[] xlabel = new String[]{
            "达成率",
            "直通率",
            "稼动率"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_horizontal_bar_chart);
        mChart = findViewById(R.id.horizontal_bar_chart);
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.getDescription().setEnabled(false);
        mChart.setMaxVisibleValueCount(60);
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);

        XAxis xl = mChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGranularity(10f);
        xl.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xlabel[(int) value % xlabel.length];
            }
        });

        YAxis yl = mChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis yr = mChart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);

        setData(xDatas, xlabel);
        mChart.setFitBars(true);
        mChart.animateY(2500);
    }

    private void setData(float[] xDatas, String[] xlabel) {
        float barWidth = 5f;
        float spaceForBar = 10f;
        ArrayList<BarEntry> xVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < xDatas.length; i++) {
            float val = xDatas[xDatas.length - i - 1];
            xVals1.add(new BarEntry(i * spaceForBar, val,
                    getResources().getDrawable(R.mipmap.ic_launcher)));
        }
        BarDataSet set1;


        set1 = new BarDataSet(xVals1, "DataSet 1");

        set1.setDrawIcons(false);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        //data.setValueTypeface(mTfLight);
        data.setBarWidth(barWidth);
        mChart.setData(data);
    }


}
