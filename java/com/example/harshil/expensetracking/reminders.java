package com.example.harshil.expensetracking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.harshil.expensetracking.Adapter.RecyclerReminderAdapter;
import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.model.Reminder;

import java.util.ArrayList;

public class reminders extends AppCompatActivity {

	RecyclerView recyclerView;
	RecyclerView.Adapter adapter;
	RecyclerView.LayoutManager layoutManager;
	ArrayList<Reminder> arrayList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminders);

		recyclerView = findViewById(R.id.reminderRecyclerview);
		layoutManager = new LinearLayoutManager(reminders.this);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);

		getreminder();
	}

	private void getreminder() {

		DatabaseHelper helper =new DatabaseHelper(reminders.this);
		SQLiteDatabase database = helper.getReadableDatabase();
		Cursor c = helper.getAllReminder(database);
		if (c!=null && c.getCount()>0){
			if (c.moveToFirst()){
				do {

					Reminder reminder = new Reminder(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
					arrayList.add(reminder);

				}while (c.moveToNext());


			}

			adapter = new RecyclerReminderAdapter(reminders.this,arrayList);
			recyclerView.setAdapter(adapter);

		}

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
