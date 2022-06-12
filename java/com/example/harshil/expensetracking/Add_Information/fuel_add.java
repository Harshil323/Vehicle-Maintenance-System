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

public class fuel_add extends AppCompatActivity {

	ImageView datetime_icon,odometer_icon,fuel_add_icon,location_icon,location_add_icon,fuel_price_icon,add_fuel_icon,reason_icon,reson_add_icon,note_icon;
	EditText odometer,price,totalcost,liters,notes;
	TextView selecteddate,selectedtime,addfuel;
	Spinner reason,fuel_location,fuelspinner;
	DatePickerDialog datePickerDialog;
	TimePickerDialog timePickerDialog;
	Button addFuelbutton;
	Calendar calendar;

//	Alert Dialog

	AlertDialog alertDialog;
	AlertDialog.Builder builder;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fuel_add);

//		initializa whole widgets

		initialize();

//		Date and Time Picker

		selecteddate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				calendar=Calendar.getInstance();
				int year=calendar.get(Calendar.YEAR);
				int month=calendar.get(Calendar.MONTH);
				int day=calendar.get(Calendar.DAY_OF_MONTH);

				datePickerDialog=new DatePickerDialog(fuel_add.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
						selecteddate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
					}
				},year,month,day);
				datePickerDialog.show();
			}
		});

		selectedtime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				calendar=Calendar.getInstance();
				int hour= calendar.get(Calendar.HOUR_OF_DAY);
				int minute=calendar.get(Calendar.MINUTE);

				timePickerDialog=new TimePickerDialog(fuel_add.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						selectedtime.setText(hourOfDay+":"+minute);
					}
				},hour,minute,false);
				timePickerDialog.show();
			}
		});


//		Spinner for reason

		ArrayList<String> reasonArray=new ArrayList<>();
		reasonArray.add("Reason");
		reasonArray.add("Trip");
		reasonArray.add("Work");

		final ArrayAdapter<String> reasonadapter =new ArrayAdapter<>(fuel_add.this,R.layout.support_simple_spinner_dropdown_item,reasonArray);
		reasonadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		reason.setAdapter(reasonadapter);

//		fuel location spinner

		ArrayList<String> fuellocationlist=new ArrayList<>();
		fuellocationlist.add("Gas Location");
		fuellocationlist.add("HP");
		fuellocationlist.add("Indian OIL");
		fuellocationlist.add("Essar");
		fuellocationlist.add("Others");



		final ArrayAdapter<String> fuellocationadapter=new ArrayAdapter<>(fuel_add.this,R.layout.support_simple_spinner_dropdown_item,fuellocationlist);
		fuellocationadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		fuel_location.setAdapter(fuellocationadapter);

//		Fuel type spinner

		ArrayList<String> fuel = new ArrayList<>();
		fuel.add("Gasoline");
		fuel.add("Diesel");
		fuel.add("CNG");
		fuel.add("LPG");

		final ArrayAdapter<String> fuelAdpter = new ArrayAdapter<>(fuel_add.this,R.layout.support_simple_spinner_dropdown_item,fuel);
		fuelAdpter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		fuelspinner.setAdapter(fuelAdpter);


//		Set onclick listener for

		addFuelbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				addfuelExpense();

			}
		});

		location_add_icon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(fuel_add.this,com.example.harshil.expensetracking.category_add.add_gas_station.class);
				startActivity(intent);
			}
		});
		Intent intent = getIntent();
		Bundle bundle = getIntent().getExtras();
		if (bundle!=null){
			String gas_tation = bundle.getString("GasStation");
			fuellocationlist.add(gas_tation);
		}



	}

	private void addfuelExpense() {

		if (selectedtime.getText().toString().length()==0){
			selectedtime.setError("Please Select Time");
			selectedtime.requestFocus();
		}
		else if (selecteddate.getText().toString().length()==0){
			selecteddate.setError("Please Select Date");
			selecteddate.requestFocus();
		}
		else if (odometer.getText().toString().length()==0){
			odometer.setError("Please Enter Odometer Value");
			odometer.requestFocus();
		}
		else if (price.getText().toString().length()==0){
			price.setError("Please Enter price of petrol!");
			price.requestFocus();
		}
		else if (totalcost.getText().toString().length()==0){
			totalcost.setError("Please Enter totalcost");
			totalcost.requestFocus();
		}
		else{

		final String dateText = selecteddate.getText().toString();
		final String timeText = selectedtime.getText().toString();
		final String odometertext = odometer.getText().toString();
		final int odometerint = Integer.parseInt(odometertext);
		final String priceText = price.getText().toString();
		final String fueltype = fuelspinner.getSelectedItem().toString();

		final int priceint = Integer.parseInt(priceText);
		final String totalcostText = totalcost.getText().toString();
		final int totalcostInt = Integer.parseInt(totalcostText);
		final String gasStation = fuel_location.getSelectedItem().toString();
		final String reasonText = reason.getSelectedItem().toString();
		String note ;
		if (notes.getText().toString().length()==0){
			note="Null";
		}
		else
			note=notes.getText().toString();


		DatabaseHelper databaseHelper = new DatabaseHelper(fuel_add.this);

		long id = databaseHelper.insertFuel(dateText,odometerint,fueltype,priceint,totalcostInt,gasStation,reasonText,note);
		if (id!=-1){
			Toast.makeText(fuel_add.this,"Refueling has been added",Toast.LENGTH_SHORT).show();
		}
		else
			Toast.makeText(fuel_add.this,"Refueling has not been added",Toast.LENGTH_SHORT).show();
		}
		odometer.setText("");
		price.setText("");
		totalcost.setText("");
		liters.setText("");
		notes.setText("");
	}

//	Initialization Block

	private void initialize() {

//		Initializa the ImageView

		datetime_icon=findViewById(R.id.datetime_icon);
		odometer_icon=findViewById(R.id.odometer_icon);
		fuel_add_icon=findViewById(R.id.addfuel_icon);
		location_icon=findViewById(R.id.gasstationlocation);
		location_add_icon=findViewById(R.id.addgasstation_icon);
		fuel_price_icon=findViewById(R.id.expense_icon);
		add_fuel_icon=findViewById(R.id.addfuelbutton);
		reason_icon=findViewById(R.id.reason_icon);
		reson_add_icon=findViewById(R.id.reasonadd);
		note_icon=findViewById(R.id.note_icon);

//		Initialize Edittext

		odometer=findViewById(R.id.odometer);
		price=findViewById(R.id.fuelprice);
		totalcost=findViewById(R.id.totalcost);
		liters=findViewById(R.id.liters);
		notes = findViewById(R.id.noteText);

//		TextView

		selecteddate=findViewById(R.id.datetext);
		selectedtime=findViewById(R.id.timetext);
		addfuel=findViewById(R.id.addfueltext);

//		Spinner

		reason=findViewById(R.id.reason_spinner);
		fuel_location=findViewById(R.id.gasstationspinner);
		fuelspinner=findViewById(R.id.fuelspinner);

//		Add Fuel Button

		addFuelbutton = findViewById(R.id.addfuelexpensebutton);





	}

	@Override
	public void onBackPressed() {
		if (odometer.getText().length()!=0||price.getText().length()!=0||liters.getText().length()!=0){
//			initializae alertdialog

			builder= new AlertDialog.Builder(fuel_add.this,R.style.ALeartDialogBoxtheme);

			builder.setMessage("Do you want to add fuel expenses?");

//			Set Positive Button

			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					System.out.println("Yes has been hit");
				}
			});

//			Set Negative Button

			builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
//					Start the Mainactivity

					Intent intent=new Intent(fuel_add.this, MainActivity.class);
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
