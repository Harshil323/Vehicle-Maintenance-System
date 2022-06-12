package com.example.harshil.expensetracking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.harshil.expensetracking.Adapter.RecyclerIncomeAdapter;
import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.model.Income;

import java.util.ArrayList;

public class income extends AppCompatActivity {

	RecyclerView recyclerView;
	RecyclerView.Adapter adapter;
	RecyclerView.LayoutManager layoutManager;
	ArrayList<Income> arrayList = new ArrayList<Income>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income);

		recyclerView = findViewById(R.id.incomeRecyclerView);
		layoutManager = new LinearLayoutManager(income.this);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);

		getIncomeList();

	}

	private void getIncomeList() {

		DatabaseHelper helper = new DatabaseHelper(income.this);
		SQLiteDatabase database = helper.getReadableDatabase();
		Cursor cursor = helper.getAllIncome(database);
		cursor.moveToFirst();
		if (cursor!=null && cursor.getCount()>0){
			if (cursor.moveToFirst()){
				do {
					Income income = new Income(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
					arrayList.add(income);
				}while (cursor.moveToNext());
			}
		}

		adapter = new RecyclerIncomeAdapter(income.this,arrayList);
		recyclerView.setAdapter(adapter);


	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
