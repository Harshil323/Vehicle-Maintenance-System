package com.example.harshil.expensetracking.category_add;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.harshil.expensetracking.R;

public class addExpenseCategory extends AppCompatActivity {

	EditText category;
	ImageView addImage;
	Button addButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_type_expense);

		category = findViewById(R.id.addCategory);
		addImage = findViewById(R.id.expense_categories);
		addButton = findViewById(R.id.button);

		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String categoryString = category.getText().toString().trim();
				if (categoryString.length()!=0){
					Intent intent = new Intent(addExpenseCategory.this,com.example.harshil.expensetracking.Add_Information.expense_add.class);
					intent.putExtra("Category",categoryString);
					startActivity(intent);
				}
			}
		});


	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
