package com.example.harshil.expensetracking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.harshil.expensetracking.Adapter.RecyclerExpenseAdapter;
import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.model.Expense;

import java.util.ArrayList;

public class expenses extends AppCompatActivity {

	RecyclerView recyclerView;
	RecyclerView.Adapter adapter;
	RecyclerView.LayoutManager layoutManager;
	ArrayList<Expense> arrayList = new ArrayList<Expense>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expenses);

//		Set the recycler view in the screen

		recyclerView = findViewById(R.id.expenseRecyclerView);
		layoutManager = new LinearLayoutManager(expenses.this);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);

		getExpenseList();

	}

	private void getExpenseList() {

		DatabaseHelper helper = new DatabaseHelper(expenses.this);
		SQLiteDatabase database = helper.getReadableDatabase();
		Cursor cursor = helper.getAllExpense(database);
		cursor.moveToFirst();
		if (cursor!=null && cursor.getCount()>0) {
			do {

				Expense expense = new Expense(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
				arrayList.add(expense);
				System.out.println(arrayList);

			} while (cursor.moveToNext());

			adapter = new RecyclerExpenseAdapter(arrayList,expenses.this);
			recyclerView.setAdapter(adapter);
		}


	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
