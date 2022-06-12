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

public class refuelingChart extends AppCompatActivity {

    BarChart barChart;
    ArrayList<BarEntry> barEntries;
    ArrayList<String> labels;
    BarDataSet dataSet;
    BarData barData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refueling_chart);

        barChart = findViewById(R.id.refuelingBarChart);
        DatabaseHelper databaseHelper = new DatabaseHelper(refuelingChart.this);
        System.out.println(databaseHelper.getAllRefueling());

        barEntries = new ArrayList<>();
        labels = new ArrayList<>();

        addvaluetoBarentry();
        addvaluetolabels();

        ArrayList<Integer> getAllfuelExpense = databaseHelper.getAllRefueling();
        dataSet = new BarDataSet(barEntries,"Refueling Cost");
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);

        barData = new BarData(labels,dataSet);
        barChart.setData(barData);
        barChart.animateY(4000);

        barChart.setDescription("Refueling Data");
    }

    private void addvaluetoBarentry() {

        DatabaseHelper helper = new DatabaseHelper(refuelingChart.this);
        ArrayList<Integer> entries = helper.getAllRefueling();
        for (int i=0;i<entries.size();i++){

            barEntries.add(new BarEntry(entries.get(i),i));

        }

    }

    private void addvaluetolabels() {

        DatabaseHelper helper = new DatabaseHelper(refuelingChart.this);
        ArrayList<Integer> entries = helper.getAllRefueling();
        for (int i=0;i<entries.size();i++){

            labels.add(Integer.toString(i+1));

        }

    }
}
