package com.delta.mpandroidcharttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView lineChart;
    private TextView horizontalBarChart;
    private TextView pieChart;
    private TextView stackedBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        lineChart = findViewById(R.id.line_chart);
        horizontalBarChart = findViewById(R.id.horizontal_bar_chart);
        pieChart = findViewById(R.id.pie_chart);
        stackedBarChart = findViewById(R.id.stacked_bar_chart);
        lineChart.setOnClickListener(this);
        horizontalBarChart.setOnClickListener(this);
        pieChart.setOnClickListener(this);
        stackedBarChart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.line_chart:
                startActivity(new Intent(MenuActivity.this, LineChartActivity.class));
                break;
            case R.id.horizontal_bar_chart:
                startActivity(new Intent(MenuActivity.this, HorizontalBarChartActivity.class));
                break;
            case R.id.pie_chart:
                startActivity(new Intent(MenuActivity.this, PieChartActivity.class));
                break;
            case R.id.stacked_bar_chart:
                startActivity(new Intent(MenuActivity.this, StackedBarChartActivity.class));
                break;
        }
    }
}
