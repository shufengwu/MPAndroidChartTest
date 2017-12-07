package com.delta.mpandroidcharttest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StackedBarChartActivity extends AppCompatActivity {

    List<ExceptionBean> list = new ArrayList<>();
    String[] xlabels = new String[]{
            "06:00",
            "07:00",
            "08:00",
            "09:00",
            "10:00",
            "11:00",
            "12:00"
    };
    private BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stacked_bar_chart);

        list.add(new ExceptionBean(3f, 10f, 4f));
        list.add(new ExceptionBean(5f, 16f, 6f));
        list.add(new ExceptionBean(3f, 16f, 4f));
        list.add(new ExceptionBean(3f, 10, 4f));
        list.add(new ExceptionBean(3f, 10, 4f));
        list.add(new ExceptionBean(3f, 10, 4f));
        list.add(new ExceptionBean(3f, 10, 4f));
        mChart = findViewById(R.id.stacked_bar_chart);
        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        mChart.setDrawValueAboveBar(false);
        mChart.setHighlightFullBarEnabled(false);

        // change the position of the y-labels
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setValueFormatter(new MyAxisValueFormatter());
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        mChart.getAxisRight().setEnabled(false);

        XAxis xLabels = mChart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.TOP);

        // mChart.setDrawXLabels(false);
        // mChart.setDrawYLabels(false);


        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);
        setData(list, xlabels);
    }

    public void setData(List<ExceptionBean> list, String[] xLabels) {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < list.size(); i++) {
            float val1 = list.get(i).getException_1();
            float val2 = list.get(i).getException_2();
            float val3 = list.get(i).getException_3();

            yVals1.add(new BarEntry(
                    i,
                    new float[]{val3, val2, val1},
                    getResources().getDrawable(R.mipmap.ic_launcher)));
        }

        BarDataSet set1;

        set1 = new BarDataSet(yVals1, "Statistics Vienna 2014");
        set1.setDrawIcons(false);
        set1.setColors(getColors());
        set1.setStackLabels(new String[]{"Births", "Divorces", "Marriages"});

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueFormatter(new MyValueFormatter());
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);

        mChart.setFitBars(true);
        mChart.invalidate();
    }

    private int[] getColors() {

        // have as many colors as stack-values per entry
        int[] colors = new int[]{
                Color.GREEN,
                Color.YELLOW,
                Color.BLUE
        };

        return colors;
    }

    public class MyAxisValueFormatter implements IAxisValueFormatter {

        private DecimalFormat mFormat;

        public MyAxisValueFormatter() {
            mFormat = new DecimalFormat("###,###,###,##0.0");
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mFormat.format(value) + " $";
        }
    }

    class ExceptionBean {
        float exception_1;
        float exception_2;
        float exception_3;

        public ExceptionBean(float exception_1, float exception_2, float exception_3) {
            this.exception_1 = exception_1;
            this.exception_2 = exception_2;
            this.exception_3 = exception_3;
        }

        public float getException_1() {
            return exception_1;
        }

        public void setException_1(float exception_1) {
            this.exception_1 = exception_1;
        }

        public float getException_2() {
            return exception_2;
        }

        public void setException_2(float exception_2) {
            this.exception_2 = exception_2;
        }

        public float getException_3() {
            return exception_3;
        }

        public void setException_3(float exception_3) {
            this.exception_3 = exception_3;
        }
    }

    public class MyValueFormatter implements IValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("###,###,###,##0");
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return mFormat.format(value);
        }
    }
}
