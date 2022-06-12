package com.example.harshil.expensetracking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.harshil.expensetracking.Adapter.RecyclerRouteAdapter;
import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.model.Route;

import java.util.ArrayList;

public class routes extends AppCompatActivity {
	RecyclerView recyclerView;
	RecyclerView.Adapter adapter;
	RecyclerView.LayoutManager layoutManager;
	ArrayList<Route> arrayList = new ArrayList<Route>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_routes);

		recyclerView =findViewById(R.id.routeRecyclerView);
		layoutManager = new LinearLayoutManager(routes.this);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);


		getAllRoutes();

	}

	private void getAllRoutes() {

		DatabaseHelper helper = new DatabaseHelper(routes.this);
		SQLiteDatabase database = helper.getReadableDatabase();

		Cursor cursor = helper.getAllroute(database);

		if (cursor!=null && cursor.getCount()>0){

			if (cursor.moveToFirst()){
				do {
					Route route = new Route(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
					System.out.println(cursor.getString(1));
					System.out.println(cursor.getString(2));
					System.out.println(cursor.getString(3));
					System.out.println(cursor.getString(4));System.out.println(cursor.getString(5));
					System.out.println(cursor.getString(6));
					System.out.println(cursor.getString(7));


					arrayList.add(route);
				}while (cursor.moveToNext());

			}

		}

		adapter = new RecyclerRouteAdapter(arrayList,routes.this);
		recyclerView.setAdapter(adapter);

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
