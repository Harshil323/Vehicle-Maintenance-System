package com.example.harshil.expensetracking.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.harshil.expensetracking.R;

public class reminder_view extends AppCompatActivity {

    TextView date,category,location,odometer,dateText,categoryText,odometerText,reasonText,valueText,locationText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_view);

        date = findViewById(R.id.dateexpense);
        category = findViewById(R.id.categoryexpense);
        location = findViewById(R.id.Locationexpense);
        odometer = findViewById(R.id.Odometerexpense);

        dateText = findViewById(R.id.dateText);
        categoryText = findViewById(R.id.categoryText);
        locationText = findViewById(R.id.LocationText);
        odometerText = findViewById(R.id.OdometerText);

        Intent intent = getIntent();
        Bundle b = getIntent().getExtras();
        if (b!=null){
            dateText.setText(b.getString("date"));
            categoryText.setText(b.getString("Category"));
            locationText.setText(b.getString("location"));
            odometerText.setText(b.getString("odometer"));

        }
    }
}
