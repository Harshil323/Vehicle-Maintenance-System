package com.example.harshil.expensetracking.category_add;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.harshil.expensetracking.R;

public class addIncomeCategory extends AppCompatActivity {

    EditText category;
    Button addCategory;
    ImageView addIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income_category);

        category = findViewById(R.id.addCategory);
        addCategory = findViewById(R.id.button);
        addIcon = findViewById(R.id.expense_categories);

//        on click listener on button

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String categoryString = category.getText().toString().trim();
                if (categoryString.length()!=0){
                Intent intent = new Intent(addIncomeCategory.this,com.example.harshil.expensetracking.Add_Information.income_add.class);
                intent.putExtra("Category",categoryString);
                startActivity(intent);}
            }
        });


    }
}
