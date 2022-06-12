package com.example.harshil.expensetracking;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class reports extends AppCompatActivity {

    BarChart barChart;
    ArrayList<BarEntry> barEntries;
    ArrayList<String> labels;
    BarDataSet barDataSet;
    BarData barData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        barChart = findViewById(R.id.incomeGraph);

        DatabaseHelper helper = new DatabaseHelper(reports.this);
        System.out.println(helper.getAllExpense());
        ArrayList<Integer> list = helper.getAllExpense();
        System.out.println(helper.getAllIncome());

        barEntries = new ArrayList<>();
        labels = new ArrayList<>();
        addvaluestobarentry();
        addvaluetolabel();

        barDataSet = new BarDataSet(barEntries,"Projects");
        barData = new BarData(labels,barDataSet);

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(barData);
        barChart.animateY(3000);

        barChart.setDescription("Bar chart of Expense");
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);


    }
    private void addvaluetolabel()
    {
        DatabaseHelper helper = new DatabaseHelper(reports.this);
        ArrayList<Integer> list = helper.getAllExpense();
        for (int i=0;i<list.size();i++){
            barEntries.add(new BarEntry(list.get(i),i));
        }

    }

    private void  addvaluestobarentry() {
        DatabaseHelper helper = new DatabaseHelper(reports.this);
        ArrayList<Integer> list = helper.getAllExpense();

        for (int i=0;i<list.size();i++){
            labels.add(Integer.toString(i+1));
        }


    }

}
