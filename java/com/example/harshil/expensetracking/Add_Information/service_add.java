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

public class service_add extends AppCompatActivity {
	ImageView datetimepicker_icon,odometer_icon,servicetype_icon,location_icon,location_add_icon,note_icon,value_icon;
	TextView date,time;
	EditText odometer,note,value;
	Spinner service,place;
	DatePickerDialog datePickerDialog;
	TimePickerDialog timePickerDialog;
	Button addservice;

//	Alertdialog initialozation
	AlertDialog alertDialog;
	AlertDialog.Builder builder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_add);
		//find all the widgets of the screen

		datetimepicker_icon=findViewById(R.id.datetime);
		date=findViewById(R.id.datepicker);
		time=findViewById(R.id.timepicker);
		odometer_icon=findViewById(R.id.icon_odometer);
		odometer=findViewById(R.id.odometeredittext);
		servicetype_icon=findViewById(R.id.icon_service);
		location_icon=findViewById(R.id.icon_location);
		location_add_icon=findViewById(R.id.addlocation_icon);
		note_icon=findViewById(R.id.icon_notes);
		note=findViewById(R.id.editText);
		service=findViewById(R.id.servicespinner);
		place=findViewById(R.id.placespinner);
		addservice=findViewById(R.id.button2);
		value_icon = findViewById(R.id.icon_value);
		value = findViewById(R.id.valueedittext);

		date.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Calendar calendar=Calendar.getInstance();
				int date_=calendar.get(Calendar.DAY_OF_MONTH);
				int month_=calendar.get(Calendar.MONTH);
				final int year_=calendar.get(Calendar.YEAR);
				datePickerDialog=new DatePickerDialog(service_add.this, new DatePickerDialog.OnDateSetListener() {
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
				timePickerDialog=new TimePickerDialog(service_add.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						time.setText(hourOfDay+":"+minute);
					}
				},hour,minute_,true);

				timePickerDialog.show();

			}
		});

		//Spinner Service
		ArrayList<String> service_list=new ArrayList<String>();
		service_list.add("Select Service");
		service_list.add("Air COnditioning");
		service_list.add("Air Filter");
		service_list.add("Battery");
		service_list.add("Belts");
		service_list.add("Body/Chassis");
		service_list.add("Break Fluid");
		service_list.add("Break Pad");
		service_list.add("Brake Replacement");
		service_list.add("Cabin air filter");
		service_list.add("CLutch Fluid");
		service_list.add("Cooling System");

		final ArrayAdapter<String> serviceadapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,service_list);
		serviceadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		service.setAdapter(serviceadapter);

		//Spinner Location

		ArrayList<String> location_list=new ArrayList<String>();
		location_list.add("QS Auto services");
		location_list.add("Khyati Garage");
		location_list.add("Tyre Point");

		final ArrayAdapter<String> locationadapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,location_list);
		locationadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		place.setAdapter(locationadapter);


//		Set the onclick listener for add the data to databse


		addservice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				insertService();
			}
		});
		location_add_icon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(service_add.this,com.example.harshil.expensetracking.category_add.service_location_add.class);
				startActivity(intent);
			}
		});

		Intent intent = getIntent();
		Bundle bundle = getIntent().getExtras();
		if (bundle!=null){
			String gas_tation = bundle.getString("GasStation");
			location_list.add(gas_tation);
		}

	}

	private void insertService() {

		if (date.getText().toString().length() == 0) {

			date.setError("Please Select Date!");
			date.requestFocus();

		}

		else if (time.getText().toString().length()==0){

			time.setError("Please Select Time!");
			time.requestFocus();
		}
		else if (odometer.getText().toString().length()==0){

			odometer.setError("Please Enter Odometer");
			odometer.requestFocus();

		}
		else if (value.getText().toString().length()==0){
			value.setError("Please Enter Service Values!");
			value.requestFocus();
		}

		else {

			final String dateText = date.getText().toString();
			final String timeText = time.getText().toString();
			final String odometerText = odometer.getText().toString();
			final int ododmeterInt = Integer.parseInt(odometerText);
			final String serviceCategory = service.getSelectedItem().toString();
			final String serviceLocation = place.getSelectedItem().toString();

			final String valueString = value.getText().toString();
			final int values = Integer.parseInt(valueString);
			String noteText;
			if (note.getText().toString().length()==0){
				noteText="NULL";
			}
			else{
				noteText=note.getText().toString();
			}

			DatabaseHelper helper = new DatabaseHelper(service_add.this);

			long id = helper.insertService(dateText,ododmeterInt,values,serviceCategory,serviceLocation,noteText);
			if (id!=-1){
				Toast.makeText(service_add.this,"Route has added",Toast.LENGTH_SHORT).show();
				date.setText("");
				time.setText("");
				odometer.setText("");
				value.setText("");
				note.setText("");

			}
			else{
				Toast.makeText(service_add.this,"Route has not added",Toast.LENGTH_SHORT).show();
			}




		}

	}


	@Override
	public void onBackPressed() {

	    if (odometer.getText().length()!=0 ||note.getText().length()!=0){

//	        Alert Dialog Object
	        builder=new AlertDialog.Builder(service_add.this,R.style.ALeartDialogBoxtheme);

	        builder.setMessage("Add the Service?");

	        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    System.out.println("Yes has been hit");

                }
            });

	        builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent=new Intent(service_add.this, MainActivity.class);
                    startActivity(intent);

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
