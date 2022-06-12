package com.example.harshil.expensetracking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class serviceChart extends AppCompatActivity {

    BarChart barChart;
    ArrayList<BarEntry> barEntries;
    ArrayList<String> labels;
    BarDataSet barDataSet;
    BarData barData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_chart);

        barChart = findViewById(R.id.serviceBarChart);


        DatabaseHelper helper = new DatabaseHelper(serviceChart.this);
        System.out.println(helper.getAllService());
        ArrayList<Integer> list = helper.getAllService();
        System.out.println(helper.getAllService());

        barEntries = new ArrayList<>();
        labels = new ArrayList<>();
        addvaluestobarentry();
        addvaluetolabel();

        barDataSet = new BarDataSet(barEntries,"Projects");
        barData = new BarData(labels,barDataSet);

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(barData);
        barChart.animateY(3000);
    }

    private void addvaluetolabel() {

        DatabaseHelper helper = new DatabaseHelper(serviceChart.this);
        ArrayList<Integer> list = helper.getAllService();
        for (int i=0;i<list.size();i++){
            barEntries.add(new BarEntry(list.get(i),i));
        }

    }

    private void addvaluestobarentry() {

        DatabaseHelper helper = new DatabaseHelper(serviceChart.this);
        ArrayList<Integer> list = helper.getAllService();

        for (int i=0;i<list.size();i++){
            labels.add(Integer.toString(i+1));
        }

    }
}
