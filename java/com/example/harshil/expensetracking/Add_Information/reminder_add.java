package com.example.harshil.expensetracking.Add_Information;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harshil.expensetracking.AlarmReceiver;
import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.MainActivity;
import com.example.harshil.expensetracking.R;
import com.example.harshil.expensetracking.category_add.addExpenseCategory;

import java.util.ArrayList;
import java.util.Calendar;

public class reminder_add extends AppCompatActivity {

	Spinner  reminder_option,type_expense,option_reminder_,remeberby;
	ImageView expense_icon,type_expense_icon,option_icon,add_typeexpense_icon,addreminder;
	TextView reminderText;
	EditText odometer,date,note;
	DatePicker datePicker;
	Calendar calendar1=Calendar.getInstance();
	DatePickerDialog datePickerDialog;
	Button addreminderButton;
	int day,month1,year1,hour1,minute;

//	Alert Dialog instances
	AlertDialog alertDialog;
	AlertDialog.Builder builder;
	private final  static String Channel_ID = "Personal_Notification";
	private final int NOTIFICATION_ID = 001;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder_add);

		//find value of spinner of reminder

		reminder_option=findViewById(R.id.expense_spinner);
		type_expense=findViewById(R.id.typeexpense_spinner);
		option_reminder_=findViewById(R.id.option_reminder_spinner);


		//icon of reminder add layout

		expense_icon=findViewById(R.id.expense_spinner_icon);
		type_expense_icon=findViewById(R.id.typeexpense_icon);
		add_typeexpense_icon=findViewById(R.id.imageView4);
		option_icon=findViewById(R.id.reminder_option_icon);

		//Add edittext

		odometer=findViewById(R.id.editTextOdometer);
		note= findViewById(R.id.notesReminder);

		//Find text view

		reminderText=findViewById(R.id.ReminderText);

		//Datepicket find value
		date=findViewById(R.id.editTextDate);

//		Button for reminder

		addreminderButton = findViewById(R.id.addreminderid);


		//Reminder option  Spinner Item
		ArrayList<String> reminderoptionList=new ArrayList<String>();
		reminderoptionList.add("Expense");
		reminderoptionList.add("Service");

		//set dateformat for date picker



		final ArrayAdapter<String> expenseSpinnerAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,reminderoptionList);
		expenseSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		reminder_option.setAdapter(expenseSpinnerAdapter);



		//service type option

		final ArrayList<String> serviceList=new ArrayList<String>();
		serviceList.add("Type of Services");
		serviceList.add("Air Conitioning");
		serviceList.add("Air Filter");
		serviceList.add("Battery");
		serviceList.add("Belts");
		serviceList.add("Body");
		serviceList.add("Brake");
		serviceList.add("Brake Replacement");
		serviceList.add("Clutch filter");
		serviceList.add("Seat Belt");
		serviceList.add("Route Tires");
		serviceList.add("Cooling System");


		// Expense type list

		final ArrayList<String> expenseList=new ArrayList<String>();
		expenseList.add("Type of Expense");
		expenseList.add("Car Wash");
		expenseList.add("Financing");
		expenseList.add("Fine");
		expenseList.add("Parking");
		expenseList.add("Payment");
		expenseList.add("Registration");
		expenseList.add("Tax");
		expenseList.add("Toll");

		//set the expense and service adapter acoording to option spinner

		reminder_option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

				switch (position){
					case 0:
						type_expense.setAdapter(new ArrayAdapter<String >(reminder_add.this,R.layout.support_simple_spinner_dropdown_item,expenseList));
						break;
					case 1:
						type_expense.setAdapter(new ArrayAdapter<String>(reminder_add.this,R.layout.support_simple_spinner_dropdown_item,serviceList));
						break;
				}


			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});


		add_typeexpense_icon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(reminder_add.this,addExpenseCategory.class);
				startActivity(intent);
			}
		});

		//set spinner adapter for options

		ArrayList<String> option=new ArrayList<String>();
		option.add("Add for one time");
		option.add("Repeat Each day");
		option.add("Repeat Each Week");
		option.add("Repeat Each Month");
		option.add("Repeat Each Year");
		ArrayAdapter<String> option_reminder=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,option);

		option_reminder_.setAdapter(option_reminder);


		//On click Listerner to Date Edit text using Datetime picket
		date.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//calander Instance


			final Calendar calendar=Calendar.getInstance();
			int Day=calendar.get(Calendar.DAY_OF_MONTH);
			int Month=calendar.get(Calendar.MONTH);
			int Year=calendar.get(Calendar.YEAR);

			datePickerDialog=new DatePickerDialog(reminder_add.this, new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
					date.setText(dayOfMonth+"/"+(month+1+"/")+year);
					day=dayOfMonth;
					month1=month;
					year1=year;
				}
			},Year,Month,Day

			);

			datePickerDialog.show();
			}
		});
		System.out.println(day);
		System.out.println(month1);
		System.out.println(year1);
		System.out.println(System.currentTimeMillis());
//		System.out.println(day);



//		On click for add reminder

		addreminderButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				insertReminder();
				System.out.println(day);
				System.out.println(month1+1);
				System.out.println(year1);
				calendar1.set(year1,month1,day,8,28);
				setreminder();

			}
		});

	}

	private void setreminder() {

		NotificationCompat.Builder builder = new NotificationCompat.Builder(reminder_add.this,Channel_ID);
		builder.setSmallIcon(R.drawable.ic_notifications_black_24dp);
		final String reminderOption = reminder_option.getSelectedItem().toString();
		final String category = type_expense.getSelectedItem().toString();
		builder.setContentTitle("Reminder For "+reminderOption );
		builder.setContentText("Reminder "+category);
		builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.remindericon));

		NotificationManagerCompat notificationManager = NotificationManagerCompat.from(reminder_add.this);
		notificationManager.notify(NOTIFICATION_ID,builder.build());

	}

	private void insertReminder() {

		 if (date.getText().toString().length()==0){

		 	date.setError("Please Enter Date!");
		 	date.requestFocus();

		 }
		 else{
		 final String reminderOption = reminder_option.getSelectedItem().toString();
		 final String category = type_expense.getSelectedItem().toString();
		 final String repeat = option_reminder_.getSelectedItem().toString();
		 final String dateString = date.getText().toString();
		 final String noteString = note.getText().toString();

//		DatabaseHelper Instance
		DatabaseHelper helper = new DatabaseHelper(reminder_add.this);

		long id = helper.insertReminder(reminderOption,dateString,category,repeat,noteString);
		if (id!=-1)
			Toast.makeText(reminder_add.this,"Reminder has added",Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(reminder_add.this,"Reminder has not added",Toast.LENGTH_SHORT).show();}

		odometer.setText("");
		date.setText("");
		note.setText("");
	}


	@Override
	public void onBackPressed() {

		if (odometer.getText().toString().length()!=0||date.getText().toString().length()!=0){

//		Alertdialog theme
		builder = new AlertDialog.Builder(reminder_add.this,R.style.ALeartDialogBoxtheme);

//		Alert dialog message

		builder.setMessage("Do you want to save reminder?");

//		Ser negative Button

		builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

//				Start the main Activity

				Intent intent=new Intent(reminder_add.this, MainActivity.class);
				startActivity(intent);

			}
		});

//		Set Positive Button

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				System.out.println("Yes Has been hit");
			}
		});

		alertDialog=builder.create();
		alertDialog.show();}
		else {
			super.onBackPressed();
		}


	}
}
