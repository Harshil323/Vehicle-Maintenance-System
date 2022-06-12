package com.example.harshil.expensetracking.category_add;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harshil.expensetracking.R;

public class start_location_add extends AppCompatActivity {

    EditText location;
    ImageView image;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_location_add);
        location = findViewById(R.id.addlocationone);
        image = findViewById(R.id.add_location);
        add = findViewById(R.id.locationaddbutton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location1 = location.getText().toString();
                if (location1.length()!=0){
                    Intent intent = new Intent(start_location_add.this,com.example.harshil.expensetracking.Add_Information.route_add.class);
                    intent.putExtra("StartLocation",location1);
                    intent.putExtra("LastLocation",location1);
                    startActivity(intent);
                }
            }
        });
    }
}
