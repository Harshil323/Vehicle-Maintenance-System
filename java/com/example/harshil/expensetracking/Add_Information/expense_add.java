package com.example.harshil.expensetracking.Add_Information;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.MainActivity;
import com.example.harshil.expensetracking.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class expense_add extends AppCompatActivity {
	ImageView datetime,odometer_icon,expense_type,location_icon,reason_icon,note_icon,icon_add_category,expense_icon,addlocation;
	TextView date,time;
	EditText odometertext,notes,value;
	Spinner expense,location,reason;
	Button addexpense;

	DatePickerDialog datePickerDialog;
	TimePickerDialog timePickerDialog;

	String todaydate;

	//Aleart DialogBox

	AlertDialog alertDialog;
	AlertDialog.Builder builder;
	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_add);

//		intialize

		datetime=findViewById(R.id.datetimeicon);
		odometer_icon=findViewById(R.id.odometerIcon);
		expense_type=findViewById(R.id.expense_spinner_icon);
		location_icon=findViewById(R.id.location_icon);
		reason_icon=findViewById(R.id.reasonicon);
		note_icon=findViewById(R.id.noteIcon);
		icon_add_category = findViewById(R.id.addCategory);
		expense_icon = findViewById(R.id.expenseIcon);
		addlocation = findViewById(R.id.addlocation);

		date=findViewById(R.id.datetext);
		time=findViewById(R.id.timetext);

		odometertext=findViewById(R.id.odometertext);
		notes=findViewById(R.id.notetext);
		value = findViewById(R.id.expensetext);

		expense=findViewById(R.id.expensespinner);
		location=findViewById(R.id.locationspinner);
		reason=findViewById(R.id.reasonspinner);

		addexpense=findViewById(R.id.button);

		final Calendar today=Calendar.getInstance();
		int tyear=today.get(Calendar.YEAR);
		int tmonth=today.get(Calendar.MONTH);
		int tday=today.get(Calendar.DAY_OF_MONTH);

		todaydate=new StringBuilder().append(tday).append("/").append(tmonth+1).append("/").append(tyear).toString();
		date.setText(todaydate);


		date.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Calendar calendar=Calendar.getInstance();
				int date_=calendar.get(Calendar.DAY_OF_MONTH);
				int month_=calendar.get(Calendar.MONTH);
				final int year_=calendar.get(Calendar.YEAR);

				datePickerDialog=new DatePickerDialog(expense_add.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

						date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
					}
				},year_,month_,date_);
				datePickerDialog.show();
			}
		});

		time.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Calendar calendar=Calendar.getInstance();
				int hour=calendar.get(Calendar.HOUR_OF_DAY);
				int minute_=calendar.get(Calendar.MINUTE);
				timePickerDialog=new TimePickerDialog(expense_add.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						time.setText(hourOfDay+":"+minute);
					}
				},hour,minute_,true);

				timePickerDialog.show();

			}
		});

//		Get the additional Data of category



//		set on click listener for add the category addition

		icon_add_category.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(expense_add.this,com.example.harshil.expensetracking.category_add.addExpenseCategory.class);
				startActivity(intent);

			}
		});




		//Arraylist for expense
		final ArrayList<String> expense_list=new ArrayList<String>();
		expense_list.add("Car Wash");
		expense_list.add("Financing");
		expense_list.add("Fine");
		expense_list.add("Parking");
		expense_list.add("Payment");
		expense_list.add("Registration");
		expense_list.add("Tax");
		expense_list.add("Tolls");
		expense_list.add("Others");

//		array adaoter for expense spinner

		final ArrayAdapter<String> expenseadapter=new ArrayAdapter<String>(expense_add.this,R.layout.support_simple_spinner_dropdown_item,expense_list);
		expenseadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		expense.setAdapter(expenseadapter);

//		get Extras category



		addlocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(expense_add.this,com.example.harshil.expensetracking.category_add.add_gas_station.class);
				startActivity(intent1);
			}
		});


		//Arraylist for Location

		final ArrayList<String> location_list=new ArrayList<String>();
		location_list.add("Ahmedabad-Baroda Highway");
		location_list.add("Tax");
		location_list.add("Parking at mall");
		location_list.add("Others");
		final ArrayAdapter<String> locationadapter =new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,location_list);
		locationadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		location.setAdapter(locationadapter);

		//Arraylist for Reason
		ArrayList<String> reason_list=new ArrayList<String>();
		reason_list.add("Reason");
		reason_list.add("Work");
		reason_list.add("Trip");
		reason_list.add("Others");
		final ArrayAdapter<String> resonadapter =new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,reason_list);
		resonadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		reason.setAdapter(resonadapter);

		Intent intent = getIntent();
		Bundle bd = intent.getExtras();
		if (bd!=null){
			String categoryname = bd.get("Category").toString();
			expense_list.add(categoryname);

		}




		addexpense.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addExpense();
			}
		});
	}

	private void addExpense() {
		if (date.getText().toString().length()==0){
			date.setError("Please Select Date!");
			date.requestFocus();
		}
		else if ( odometertext.getText().toString().length()==0){
				odometertext.setError("Please Enter odometer value");
				odometertext.requestFocus();
		}
		else if (value.getText().toString().length()==0){
			value.setError("Please Enter Value!");
			value.requestFocus();
		}
		else{


		final String Date = date.getText().toString();
		final String Time = time.getText().toString();
		final String odometerText = odometertext.getText().toString();
		final int odometer = Integer.parseInt(odometerText);
		final String category = expense.getSelectedItem().toString();
		final String ValueText = value.getText().toString();
		final int valueint= Integer.parseInt(ValueText);
		final String locationtext = location.getSelectedItem().toString();
		final String reasontext = reason.getSelectedItem().toString();
		final String note ;
		if (notes.getText().toString().length()==0){
			note="null";

		}
		else
			note=notes.getText().toString();

//		Insert data to sqlite database

		DatabaseHelper databaseHelper = new DatabaseHelper(expense_add.this);
		long id = databaseHelper.insertExpense(Date,odometer,category,valueint,locationtext,reasontext,note);
		if (id!=-1){
			Toast.makeText(expense_add.this,"Expense is added",Toast.LENGTH_SHORT).show();
		}
		else
			Toast.makeText(expense_add.this,"Expense not added",Toast.LENGTH_SHORT).show();


		date.setText("");
		time.setText("");
		odometertext.setText("");
		value.setText("");
		notes.setText("");

		}
	}

	@Override
	public void onBackPressed() {

		if (odometertext.getText().length()!=0||notes.getText().length()!=0){

//			Aleart dialog builder

			builder=new AlertDialog.Builder(expense_add.this,R.style.ALeartDialogBoxtheme);

			builder.setMessage("Do you want to exit?");

//			On click listerner to Discard Button

			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

					Intent intent=new Intent(expense_add.this, MainActivity.class);
					startActivity(intent);


				}
			});

//			Set OnclickListener to No button

			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

					System.out.println("No Has Hit");

				}
			});
			alertDialog=builder.create();
			alertDialog.show();
		}
		else {
			super.onBackPressed();
		}


	}
}
