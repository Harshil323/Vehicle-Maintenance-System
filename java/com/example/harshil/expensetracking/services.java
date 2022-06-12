package com.example.harshil.expensetracking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.harshil.expensetracking.Adapter.RecyclerServiceAdapter;
import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.model.Service;

import java.util.ArrayList;

public class services extends AppCompatActivity {

	RecyclerView recyclerView;
	RecyclerView.LayoutManager layoutManager;
	RecyclerView.Adapter adapter;
	ArrayList<Service> arrayList = new ArrayList<Service>();
	Service service;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_services);

		recyclerView = findViewById(R.id.servicesRecyclerView);
		layoutManager = new LinearLayoutManager(services.this);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);

		getServices();


	}

	private void getServices() {

		DatabaseHelper helper = new DatabaseHelper(services.this);
		SQLiteDatabase database = helper.getReadableDatabase();
		Cursor cursor = helper.getAllServices(database);
		cursor.moveToFirst();
		if (cursor!= null && cursor.getCount()>0){

				do {
					Service service = new Service(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
					arrayList.add(service);
				}while (cursor.moveToNext());



		}
		adapter = new RecyclerServiceAdapter(arrayList,services.this);
		recyclerView.setAdapter(adapter);

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
