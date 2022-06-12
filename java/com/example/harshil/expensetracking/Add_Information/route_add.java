package com.example.harshil.expensetracking.Add_Information;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class route_add extends AppCompatActivity {

	ImageView start_location,last_lcoation,start_location_add,last_lcoation_add,start_date,final_date,start_odometer,end_odometer,reason_icon,note_add;
	EditText start_odometer_text,end_odometer_text,note;
	TextView start_date_text,start_time_text,end_date_text,end_time_text;
	Spinner origin,destination,reason;
	DatePickerDialog datePickerDialog;
	TimePickerDialog timePickerDialog;
	Calendar calendar;
	Button addRouteButton;

//	Alert Dialog
	AlertDialog alertDialog;
	AlertDialog.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route_add);

		start_location=findViewById(R.id.location);
		last_lcoation=findViewById(R.id.destination_icon);
		start_location_add=findViewById(R.id.location_add);
		last_lcoation_add=findViewById(R.id.destination_add);
		start_date=findViewById(R.id.datetime_icon);
		final_date=findViewById(R.id.datetime_icon1);
		start_odometer=findViewById(R.id.startodometer);
		end_odometer=findViewById(R.id.endodometer_icon);
		reason_icon=findViewById(R.id.reason_icon);
		note_add=findViewById(R.id.noteIcon);

		start_odometer_text=findViewById(R.id.odometer_text);
		end_odometer_text=findViewById(R.id.finalodometer_text);
		note=findViewById(R.id.notetext);

		origin=findViewById(R.id.location_spinner);
		destination=findViewById(R.id.destination_list);
		reason=findViewById(R.id.reason_list);

		start_date_text=findViewById(R.id.textView17);
		start_time_text=findViewById(R.id.textView18);
		end_date_text=findViewById(R.id.enddatetext);
		end_time_text=findViewById(R.id.endtimetext);

		addRouteButton = findViewById(R.id.addroute);

		//origin spinner

		ArrayList<String> originarray=new ArrayList<String>();
		originarray.add("Origin");
		originarray.add("Ahmedabad-Baroda Highway");
		originarray.add("Ghatlodia");
		originarray.add("K-k Nagar");

		final ArrayAdapter<String> originadapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,originarray);
		originadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		origin.setAdapter(originadapter);

//		Destination Spinner

		ArrayList<String> destinationlist=new ArrayList<String>();
		destinationlist.add("Desstination");
		destinationlist.add("Ahmedabad-Baroda-Highway");
		destinationlist.add("Ghatlodia");
		destinationlist.add("K-K Nagar");

		final ArrayAdapter<String> destinationadapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,destinationlist);
		destinationadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		destination.setAdapter(destinationadapter);

//		Reason Spinner

		ArrayList<String> reasonlist=new ArrayList<String>();
		reasonlist.add("Reason List");
		reasonlist.add("Work");
		reasonlist.add("Home");
		reasonlist.add("Others");

		final ArrayAdapter<String> reasonadapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,reasonlist);
		reasonadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		reason.setAdapter(reasonadapter);

//		add the start location list

		start_location_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(route_add.this,com.example.harshil.expensetracking.category_add.start_location_add.class);
				startActivity(intent);
			}
		});

//		Add the last location to list

		last_lcoation_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(route_add.this,com.example.harshil.expensetracking.category_add.start_location_add.class);
				startActivity(intent);

			}
		});
//		Open Date And Time For origin and destination

		start_date_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				calendar=Calendar.getInstance();
				int day=calendar.get(Calendar.DAY_OF_MONTH);
				int month=calendar.get(Calendar.MONTH);
				int year=calendar.get(Calendar.YEAR);
				datePickerDialog=new DatePickerDialog(route_add.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
						start_date_text.setText(dayOfMonth+"/"+(month+1)+"/"+year);
					}
				},year,month,day);
				datePickerDialog.show();
			}
		});

		start_time_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				calendar=Calendar.getInstance();
				final int hour=calendar.get(Calendar.HOUR_OF_DAY);
				int minute=calendar.get(Calendar.MINUTE);

				timePickerDialog=new TimePickerDialog(route_add.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						start_time_text.setText(hourOfDay+":"+minute);
					}
				},hour,minute,true);
				timePickerDialog.show();
			}
		});

		end_date_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				calendar=Calendar.getInstance();
				int day_=calendar.get(Calendar.DAY_OF_MONTH);
				int month_=calendar.get(Calendar.MONTH);
				int year_=calendar.get(Calendar.YEAR);

				datePickerDialog=new DatePickerDialog(route_add.this, new DatePickerDialog.OnDateSetListener() {
					@SuppressLint("SetTextI18n")
					@Override
					public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
						end_date_text.setText(dayOfMonth+"/"+(month+1)+"/"+year);
					}
				},year_,month_,day_);
				datePickerDialog.show();
			}
		});

		end_time_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				calendar=Calendar.getInstance();
				final int hour=calendar.get(Calendar.HOUR_OF_DAY);
				int minute=calendar.get(Calendar.MINUTE);

				timePickerDialog=new TimePickerDialog(route_add.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						end_time_text.setText(hourOfDay+":"+minute);
					}
				},hour,minute,true);
				timePickerDialog.show();
			}
		});

		addRouteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addRoute();
			}
		});

//		Add to spinner

		Intent intent = getIntent();
		Bundle bd = intent.getExtras();
		if (bd!=null)
		{
			String startlocation = bd.getString("StartLocation").toString();
			originarray.add(startlocation);

			String startlocation1 = bd.getString("LastLocation").toString();
			destinationlist.add(startlocation1);
		}



	}

	private void addRoute() {

//		Add the error for non entered field

		if (start_date_text.getText().toString().length() == 0) {

			start_date_text.setError("Please Enter Date!");
			start_date_text.requestFocus();
		}

		else if (start_time_text.getText().toString().length()==0){
			start_time_text.setError("Please Enter Time!");
			start_time_text.requestFocus();
		}
		else if (start_odometer_text.getText().toString().length()==0){
			start_odometer_text.setError("Please Enter Odometer Value!");
			start_odometer_text.requestFocus();
		}
		else if (end_date_text.getText().toString().length()==0){

			end_date_text.setError("Please Select Date!");
			end_date_text.requestFocus();

		}

		else if (end_time_text.getText().toString().length()==0){

			end_time_text.setError("Please Select Time!");
			end_time_text.requestFocus();

		}

		else if (end_odometer_text.getText().toString().length()==0){

			end_odometer_text.setError("Please Enter Odometer Value!");
			end_odometer_text.requestFocus();

		}
		else if (Integer.parseInt(start_odometer_text.getText().toString())>Integer.parseInt(end_odometer_text.getText().toString())){

			end_odometer_text.setError("End Odometer can not less than start odometer");
			end_odometer_text.requestFocus();
		}

		else {

			final String startlocation = origin.getSelectedItem().toString();
			final String startDate = start_date_text.getText().toString();
			final String startTime = start_time_text.getText().toString();
			final String startOdometerText = start_odometer_text.getText().toString();
			final int startOdometer = Integer.parseInt(startOdometerText);
			final String endlocation = destination.getSelectedItem().toString();
			final String endDate = end_date_text.getText().toString();
			final String endTime = end_time_text.getText().toString();
			final String endOdometerText = end_odometer_text.getText().toString();
			final int endOdometer = Integer.parseInt(endOdometerText);
			final String reasonText = reason.getSelectedItem().toString();
			final String noteText = note.getText().toString();

			DatabaseHelper helper = new DatabaseHelper(route_add.this);

			long id = helper.insertRoute(startlocation,startDate,startOdometer,endlocation,endDate,endOdometer,reasonText,noteText);
			if (id!=-1){
				Toast.makeText(route_add.this,"Route has added",Toast.LENGTH_SHORT).show();
				start_odometer_text.setText("");
				start_date_text.setText("");
				start_time_text.setText("");
				end_odometer_text.setText("");
				end_time_text.setText("");
				end_date_text.setText("");
				note.setText("");}

			else
				Toast.makeText(route_add.this,"Route has not added",Toast.LENGTH_SHORT).show();
		}

	}


	@Override
	public void onBackPressed() {
		if (start_time_text.getText().toString().length()!=0||start_date_text.getText().toString().length()!=0||start_odometer_text.getText().toString().length()!=0||end_time_text.getText().toString().length()!=0||end_date_text.getText().toString().length()!=0||end_odometer_text.getText().toString().length()!=0)
		{
//		Add the alert dialog at the back button pressed

		builder=new AlertDialog.Builder(route_add.this,R.style.ALeartDialogBoxtheme);

//		Set the message for the dialog

		builder.setMessage("Do you want to save route?");

//		Set the positive Button for alert dialog

		builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				System.out.println("Save has been hit");

			}
		});

//		set Negative Button

		builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

//				Start a new Activity

				Intent intent=new Intent(route_add.this, MainActivity.class);
				startActivity(intent);
			}
		});

		alertDialog=builder.create();
		alertDialog.show();}
		else {
			super.onBackPressed();
		}


	}

}
