package com.example.harshil.expensetracking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.harshil.expensetracking.Adapter.RecyclerFuelAdapter;
import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.model.Fuel;

import java.util.ArrayList;

public class refueling extends AppCompatActivity {

//	Alertdialog instances

	AlertDialog alertDialog;
	AlertDialog.Builder builder;

	RecyclerView recyclerView;
	RecyclerView.Adapter adapter;
	RecyclerView.LayoutManager layoutManager;
	ArrayList<Fuel> arrayList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refueling);

		recyclerView = findViewById(R.id.refuelingRecyclerView);
		layoutManager = new LinearLayoutManager(refueling.this);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);

		refuelingdisplay();
	}

	private void refuelingdisplay() {

		DatabaseHelper helper = new DatabaseHelper(refueling.this);
		SQLiteDatabase database = helper.getReadableDatabase();
		Cursor cursor = helper.getAllFuel(database);
		if (cursor!=null && cursor.getCount()>0){
			if (cursor.moveToFirst()){
				do {
					Fuel fuel = new Fuel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
					arrayList.add(fuel);
				}while (cursor.moveToNext());

			}
		}

		adapter = new RecyclerFuelAdapter(refueling.this,arrayList);
		recyclerView.setAdapter(adapter);


	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
