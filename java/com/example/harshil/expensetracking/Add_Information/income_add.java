package com.example.harshil.expensetracking.Add_Information;

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

public class income_add extends AppCompatActivity {
	TextView date,time;
	DatePickerDialog datePickerDialog;
	TimePickerDialog timePickerDialog;
	EditText odometer,income,note;
	Spinner incomecategory;
	ImageView date_icon,odometer_icon,value_icon,income_icon,note_icon,addCategory;
	Button addincome;
	AlertDialog alertDialog;
	AlertDialog.Builder builder;
	DatabaseHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income_add);

		//Initialze the widgets
		initialize();

		ArrayList<String> category=new ArrayList<String>();
		category.add("Freight");
		category.add("Refund");
		category.add("Ride");
		category.add("Transport application");
		category.add("Vehicle Sale");

		final ArrayAdapter<String> categoryadapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,category);
		categoryadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		incomecategory.setAdapter(categoryadapter);

		date.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Calendar calendar=Calendar.getInstance();
				int dateInt=calendar.get(Calendar.DAY_OF_MONTH);
				int monthInt=calendar.get(Calendar.MONTH);
				int yearInt=calendar.get(Calendar.YEAR);
				datePickerDialog=new DatePickerDialog(income_add.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
						date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
					}
				},yearInt,monthInt,dateInt);

				datePickerDialog.show();

			}
		});


		time.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Calendar calendar=Calendar.getInstance();
				int hour=calendar.get(Calendar.HOUR_OF_DAY);
				int minute_=calendar.get(Calendar.MINUTE);
				timePickerDialog=new TimePickerDialog(income_add.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						time.setText(hourOfDay+":"+minute);
					}
				},hour,minute_,true);

				timePickerDialog.show();

			}
		});


		addCategory.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(income_add.this,com.example.harshil.expensetracking.category_add.addIncomeCategory.class);
				startActivity(intent);


			}
		});

		Intent intent = getIntent();
		Bundle bd = intent.getExtras();
		if (bd!=null){
			String newCategory = bd.get("Category").toString();
			category.add(newCategory);

		}



//		add the new category to the spinner



//		Set Onclick listener for addincome button

		addincome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				InsertIncome();

			}
		});

	}

//	InserIncome Method to create table and insert data to databse

	public void InsertIncome() {
		helper = new DatabaseHelper(income_add.this);

//		Ser Error for

		if (date.getText().length()==0){
			date.setError("Please Select Date");
			date.requestFocus();
		}

		else if (time.getText().length()==0){
			time.setError("Please Select Date");
			time.requestFocus();
		}

		else if (odometer.getText().length()==0){
			odometer.setError("Please Enter Odometer Value");
			odometer.requestFocus();
		}
		else if (income.getText().length()==0){
			income.setError("Please Enter Income Value");
			income.requestFocus();
		}

		else if (incomecategory.getSelectedItem().toString().length()==0){
			builder = new AlertDialog.Builder(income_add.this,R.style.ALeartDialogBoxtheme);
			builder.setMessage("Please Select Category");
			builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					incomecategory.requestFocus();
				}
			});

			alertDialog = builder.create();
			alertDialog.show();
		}

		else {
//		Get all the data entered into income_add activity

		final String selecteddate = date.getText().toString();
		String selectedTime = time.getText().toString();
		final String odometertext = odometer.getText().toString();
		final int odometer = Integer.parseInt(odometertext);
		final String valueText = income.getText().toString();
		final int value = Integer.parseInt(valueText);
		final String category = incomecategory.getSelectedItem().toString();
		final String noteString = note.getText().toString();
		System.out.println(selecteddate);
		System.out.println(selectedTime);
		System.out.println(odometertext);
		System.out.println(valueText);
		System.out.println(noteString);
		System.out.println(category);

//		Add values to content values
//		Create an sqlite database

		long  id = helper.insertIncome(selecteddate,odometer,value,category,noteString);
		if (id!=-1){
			Toast.makeText(this,"Income is added",Toast.LENGTH_SHORT).show();
		}
		else
			Toast.makeText(this,"Income is not added",Toast.LENGTH_SHORT).show();

		}

		date.setText("");
		time.setText("");
		odometer.setText("");
		income.setText("");
		note.setText("");

	}

	private void initialize() {
		date=findViewById(R.id.datetext);
		time=findViewById(R.id.timetext);
		odometer=findViewById(R.id.odometertext);
		income=findViewById(R.id.Value);
		note=findViewById(R.id.notetext);
		incomecategory=findViewById(R.id.categoryspinner);
		date_icon=findViewById(R.id.datetimeicon);
		odometer_icon=findViewById(R.id.odometerIcon);
		value_icon=findViewById(R.id.valueicon);
		income_icon=findViewById(R.id.category);
		note_icon=findViewById(R.id.noteIcon);
		addincome=findViewById(R.id.button);
		addCategory=findViewById(R.id.addcategory);

	}

	@Override
	public void onBackPressed() {

		if (odometer.getText().length()!=0||income.getText().length()!=0){

			builder=new AlertDialog.Builder(income_add.this,R.style.ALeartDialogBoxtheme);

			builder.setMessage("Do you want to save income?");

//			Positive Button

			builder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

//					Start the main Activity

					Intent intent = new Intent(income_add.this, MainActivity.class);
					startActivity(intent);

				}
			});

//			Negative Button

			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					System.out.println("Cancle Has hit");
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
