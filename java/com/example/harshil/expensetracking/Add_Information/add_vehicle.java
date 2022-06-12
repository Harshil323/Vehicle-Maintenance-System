package com.example.harshil.expensetracking.Add_Information;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.MainActivity;
import com.example.harshil.expensetracking.R;
import com.example.harshil.expensetracking.model.Vehicle;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class add_vehicle extends AppCompatActivity {

	public Spinner vehicle_type,vehicle_manufacture,fuel_type,options;
	EditText vehicle_name,vehicle_model,vehicle_plate,Vehicle_year,vehicle_fuelcapicity,vehicle_chassis,vehicle_identification,vehicle_notes;
	Button addvehicle;

//	Alert Dialog
	AlertDialog alertDialog;
	AlertDialog.Builder builder;

//	Create the dataabse Instance

	private DatabaseHelper databaseHelper;
	Vehicle vehicle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addvehicle);

		vehicle_type=findViewById(R.id.vehicletypespinner);
		vehicle_manufacture=findViewById(R.id.manufacturespinner);
		fuel_type=findViewById(R.id.fueloption);
		options=findViewById(R.id.optionspinner);

		vehicle_name=findViewById(R.id.editText2);
		vehicle_model=findViewById(R.id.vehiclemodel);
		vehicle_plate=findViewById(R.id.vehicle_plate);
		Vehicle_year=findViewById(R.id.vehicle_year);
		vehicle_fuelcapicity=findViewById(R.id.editText3);
		vehicle_chassis=findViewById(R.id.vehiclechassis);
		vehicle_identification=findViewById(R.id.identidication);
		addvehicle=findViewById(R.id.addvehicle);
		vehicle_notes=findViewById(R.id.noteText);

		ArrayList<String> vehicle_typearray=new ArrayList<String>();
		vehicle_typearray.add("Car");
		vehicle_typearray.add("Truck");
		vehicle_typearray.add("Bike");
		vehicle_typearray.add("Bus");
		final ArrayAdapter<String> vehicletypeadapter=new ArrayAdapter<String>(add_vehicle.this,R.layout.support_simple_spinner_dropdown_item,vehicle_typearray);
		vehicletypeadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		vehicle_type.setAdapter(vehicletypeadapter);


		final ArrayList<String> manufacture_bike=new ArrayList<String>();
		manufacture_bike.add("Honda");
		manufacture_bike.add("Bajaj");
		manufacture_bike.add("Hero");
		manufacture_bike.add("TVS motors");
		manufacture_bike.add("Yamaha");
		manufacture_bike.add("Royal Enfield");
		manufacture_bike.add("Suzuki");
		manufacture_bike.add("KTM");
		manufacture_bike.add("Ducati");
		manufacture_bike.add("Mahindra");
		manufacture_bike.add("Kawasaki");
		manufacture_bike.add("Others");

		final ArrayList<String> manufecture_car=new ArrayList<String>();
		manufecture_car.add("Hyndai");
		manufecture_car.add("Honda");
		manufecture_car.add("Maruti Suzuki");
		manufecture_car.add("TATA");
		manufecture_car.add("Ford");
		manufecture_car.add("Mahindra");
		manufecture_car.add("Skoda");
		manufecture_car.add("Audi");
		manufecture_car.add("Bentley");
		manufecture_car.add("BMW");
		manufecture_car.add("Others");

		final ArrayList<String> manufacture_truck=new ArrayList<String>();
		manufacture_truck.add("Tata Motors");
		manufacture_truck.add("Eicher Motors");
		manufacture_truck.add("Swaraj Mazda");
		manufacture_truck.add("Mahindra & Mahindra");
		manufacture_truck.add("Asia Motorworks");
		manufacture_truck.add("Hindustran Motors");
		manufacture_truck.add("Force Motors");

		final ArrayList<String> manufacture_bus=new ArrayList<String>();
		manufacture_truck.add("Tata Motors");
		manufacture_truck.add("Eicher Motors");
		manufacture_truck.add("Swaraj Mazda");
		manufacture_truck.add("Mahindra & Mahindra");
		manufacture_truck.add("Asia Motorworks");
		manufacture_truck.add("Hindustran Motors");
		manufacture_truck.add("Force Motors");

		final ArrayList<String> fueltype=new ArrayList<String>();
		fueltype.add("Gasoline");
		fueltype.add("Diesel");
		fueltype.add("CNG");
		fueltype.add("LPG");
		fueltype.add("SPEED");

		ArrayAdapter<String> fueladapter =new ArrayAdapter<String>(add_vehicle.this,R.layout.support_simple_spinner_dropdown_item,fueltype);
		fueladapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		fuel_type.setAdapter(fueladapter);

		final ArrayList<String> optionsarray=new ArrayList<String>();
		optionsarray.add("Kilometers");
		optionsarray.add("Miles");

		ArrayAdapter<String> optionadapter=new ArrayAdapter<String>(add_vehicle.this,R.layout.support_simple_spinner_dropdown_item,optionsarray);
		optionadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		options.setAdapter(optionadapter);


		vehicle_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				switch (position){
					case 0:
						vehicle_manufacture.setAdapter(new ArrayAdapter<String>(add_vehicle.this,R.layout.support_simple_spinner_dropdown_item,manufecture_car));
						break;
					case 1:
						vehicle_manufacture.setAdapter(new ArrayAdapter<String>(add_vehicle.this,R.layout.support_simple_spinner_dropdown_item,manufacture_truck));
						break;
					case 2:
						vehicle_manufacture.setAdapter(new ArrayAdapter<String>(add_vehicle.this,R.layout.support_simple_spinner_dropdown_item,manufacture_bike));
						break;
					case 3:
						vehicle_manufacture.setAdapter(new ArrayAdapter<String>(add_vehicle.this,R.layout.support_simple_spinner_dropdown_item,manufacture_bus));
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});


//		Add the Vehicle When the add_vehicle Button is pressed
		addvehicle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				InsertVehicle();
			}
		});



	}

//	Insert a Vehicle

	public void InsertVehicle() {

		if (vehicle_name.getText().length() == 0) {
			vehicle_name.setError("Please Enter Vehicle Name");
			vehicle_name.requestFocus();
			return;
		} else if (vehicle_model.getText().length() == 0) {
			vehicle_model.setError("Enter Vehicle Model");
			vehicle_model.requestFocus();
			return;
		} else if (vehicle_plate.getText().length() == 0) {
			vehicle_plate.setError("Please Enter Vehicle Plate Number");
			vehicle_plate.requestFocus();
			return;
		} else if (Vehicle_year.getText().length() == 0) {
			Vehicle_year.setError("Please Enter Year of the vehicle");
			Vehicle_year.requestFocus();
			return;
		} else if (vehicle_fuelcapicity.getText().length() == 0) {
			vehicle_fuelcapicity.setError("Enter Fuel Capacity of the vehicle");
			vehicle_fuelcapicity.requestFocus();
			return;
		}
		else if (Integer.parseInt(Vehicle_year.getText().toString())<=2000){

			Vehicle_year.setError("Please Enter the year between 2000 and above");
			Vehicle_year.requestFocus();
			return;
		}

		else if (vehicle_plate.getText().toString().length()!=0){
			String plates= vehicle_plate.getText().toString();
			String regex="^[A-Z]{2}([ \\-])[0-9]{2}[ ,][A-Z0-9]{1,2}[A-Z]\\1[0-9]{4}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(plates);
			if (!matcher.matches()){
				vehicle_plate.setError("Enter proper Plate number Ex. GJ 01 AB 1234");
				vehicle_plate.requestFocus();
				return;
			}
			else {


				final String type = vehicle_type.getSelectedItem().toString();
				final String name = vehicle_name.getText().toString();
				final String manufacturer = vehicle_manufacture.getSelectedItem().toString();
				final String model = vehicle_model.getText().toString();
				final String plates1 = vehicle_plate.getText().toString();
				final String yearText = Vehicle_year.getText().toString();
				final int year = Integer.parseInt(yearText);
				final String fuel = fuel_type.getSelectedItem().toString();
				final String fuel_capacity = vehicle_fuelcapicity.getText().toString();
				int capacity = Integer.parseInt(fuel_capacity);
				final String Chassis = vehicle_chassis.getText().toString();
				final String identification = vehicle_identification.getText().toString();
//		String notes = vehicle_notes.getText().toString();

				databaseHelper = new DatabaseHelper(add_vehicle.this);



				long id = databaseHelper.insertVehicle(type, name, manufacturer, model, plates1, year, fuel, capacity, Chassis, identification);
				if (id == -1) {
					Toast.makeText(add_vehicle.this, "Vehicle is not added", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(add_vehicle.this, "Vehicle is added", Toast.LENGTH_SHORT).show();
				}

				vehicle_name.setText("");
				vehicle_model.setText("");
				vehicle_plate.setText("");
				Vehicle_year.setText("");
				vehicle_fuelcapicity.setText("");


			}
			}

		}


//		else {
//			final String type = vehicle_type.getSelectedItem().toString();
//			final String name = vehicle_name.getText().toString();
//			final String manufacturer = vehicle_manufacture.getSelectedItem().toString();
//			final String model = vehicle_model.getText().toString();
//			final String plates = vehicle_plate.getText().toString();
//			final String yearText = Vehicle_year.getText().toString();
//			final int year = Integer.parseInt(yearText);
//			final String fuel = fuel_type.getSelectedItem().toString();
//			final String fuel_capacity = vehicle_fuelcapicity.getText().toString();
//			int capacity = Integer.parseInt(fuel_capacity);
//			final String Chassis = vehicle_chassis.getText().toString();
//			final String identification = vehicle_identification.getText().toString();
////		String notes = vehicle_notes.getText().toString();
//
//			databaseHelper = new DatabaseHelper(add_vehicle.this);
//
////		 vehicle = new Vehicle();
////		 vehicle.setVehicletype(type);
////		 vehicle.setVehiclename(name);
////		 vehicle.setVehiclemanufacturer(manufacturer);
////		 vehicle.setVehiclemodel(model);
////		 vehicle.setVehicleplates(plates);
////		 vehicle.setYear(year);
////		 vehicle.setFuel(fuel);
////		 vehicle.setFuel_capacity(fuel_capacity);
////		 vehicle.setChassis(Chassis);
////		 vehicle.setIdnumber(identification);
//
//			long id = databaseHelper.insertVehicle(type, name, manufacturer, model, plates, year, fuel, capacity, Chassis, identification);
//			if (id == -1) {
//				Toast.makeText(add_vehicle.this, "Vehicle is not added", Toast.LENGTH_SHORT).show();
//			} else {
//				Toast.makeText(add_vehicle.this, "Vehicle is added", Toast.LENGTH_SHORT).show();
//			}
//
//			vehicle_name.setText("");
//			vehicle_model.setText("");
//			vehicle_plate.setText("");
//			Vehicle_year.setText("");
//			vehicle_fuelcapicity.setText("");
//
//
//		}


//		Add the vehcile data to database






	@Override
	public void onBackPressed() {

//		Alert dialog list out
		if (vehicle_name.getText().toString().length()!=0||vehicle_model.getText().toString().length()!=0||vehicle_plate.getText().toString().length()!=0){

			builder = new AlertDialog.Builder(add_vehicle.this,R.style.ALeartDialogBoxtheme);

			builder.setMessage("Do you want to add vehicle?");

			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

//					Back to the rwgular data
					System.out.println("Yes has been hit");
				}
			});

//			set Negative Button

			builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
//				Start the mainactivity

					Intent intent=new Intent(add_vehicle.this, MainActivity.class);
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
