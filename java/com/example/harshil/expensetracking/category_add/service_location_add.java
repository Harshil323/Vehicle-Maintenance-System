package com.example.harshil.expensetracking.category_add;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.harshil.expensetracking.R;

public class service_location_add extends AppCompatActivity {

    EditText location;
    ImageView image;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_location_add);

        location = findViewById(R.id.addServicelocation);
        image = findViewById(R.id.expense_categories);
        add = findViewById(R.id.button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location1 = location.getText().toString();
                if (location1.length()!=0){
                    Intent intent = new Intent(service_location_add.this,com.example.harshil.expensetracking.Add_Information.service_add.class);
                    intent.putExtra("GasStation",location1);
                    startActivity(intent);
                }
            }
        });
    }
}
