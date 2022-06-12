package com.example.harshil.expensetracking.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.harshil.expensetracking.R;

public class refueling_view extends AppCompatActivity {

    TextView date,category,location,odometer,reason,value,dateText,categoryText,odometerText,reasonText,valueText,locationText,reason1,reason1Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refueling_view);

        date = findViewById(R.id.dateexpense);
        category = findViewById(R.id.categoryexpense);
        location = findViewById(R.id.Locationexpense);
        odometer = findViewById(R.id.Odometerexpense);
        reason = findViewById(R.id.Reasonexpense);
        value = findViewById(R.id.valueexpense);
        reason1 = findViewById(R.id.valueexpense);

        dateText = findViewById(R.id.dateText);
        categoryText = findViewById(R.id.categoryText);
        locationText = findViewById(R.id.LocationText);
        odometerText = findViewById(R.id.OdometerText);
        reasonText = findViewById(R.id.ReasonText);
        valueText = findViewById(R.id.costText);
        reason1Text = findViewById(R.id.valueText);
        Intent intent = getIntent();
        Bundle b = getIntent().getExtras();
        if (b!=null){
            dateText.setText(b.getString("date"));
            categoryText.setText(b.getString("location"));
            locationText.setText(b.getString("value"));
            odometerText.setText(b.getString("Category"));
            reasonText.setText(b.getString("reason1"));
            valueText.setText(b.getString("odometer"));
            reason1Text.setText(b.getString("reason"));



        }
    }
}
