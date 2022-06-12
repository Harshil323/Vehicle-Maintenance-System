package com.example.harshil.expensetracking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;

import com.example.harshil.expensetracking.Adapter.RecyclevehicleAdapter;
import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.model.Vehicle;

import java.util.ArrayList;

public class Vehicle_List extends AppCompatActivity {

	RecyclerView recyclerView;
	RecyclerView.Adapter adapter;
	RecyclerView.LayoutManager layoutManager;
	ArrayList<Vehicle> arrayList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicle_list);

		recyclerView = findViewById(R.id.vehicleRecycleView);
		layoutManager = new LinearLayoutManager(Vehicle_List.this);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);

		getVehicleList();



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.recycleroptionmenu,menu);
		return  super.onCreateOptionsMenu(menu);

	}

	private void getVehicleList() {

		DatabaseHelper helper = new DatabaseHelper(Vehicle_List.this);
		SQLiteDatabase database = helper.getReadableDatabase();
		Cursor cursor =helper.getAllVehicle(database);
		cursor.moveToFirst();
		if (cursor!= null && cursor.getCount()>0) {
			do {
				Vehicle vehicle = new Vehicle(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9));
				arrayList.add(vehicle);
			} while (cursor.moveToNext());
		}
		adapter = new RecyclevehicleAdapter(arrayList,Vehicle_List.this);
		helper.close();
		recyclerView.setAdapter(adapter);



	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
