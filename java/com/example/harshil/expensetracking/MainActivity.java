package com.example.harshil.expensetracking;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.harshil.expensetracking.Add_Information.add_vehicle;
import com.github.clans.fab.FloatingActionMenu;


public class MainActivity extends AppCompatActivity {
	DrawerLayout drawerLayout;
	ActionBarDrawerToggle toggle;
	NavigationView navigationView;
	com.github.clans.fab.FloatingActionButton fuel_add,expense_add,income_add,route_add,reminder_add,service_add;
	FloatingActionMenu floatingActionMenu;

//	AlertDialog
	AlertDialog alertDialog;
	AlertDialog.Builder builder;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//find the drawer layout and initiate Actiondrawertoggle to the screen
		drawerLayout=findViewById(R.id.Drawer_layout);
		toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

		//add the listener to drawer layout
		drawerLayout.addDrawerListener(toggle);
		toggle.syncState();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		//create an instance of floating point
		floatingActionMenu=findViewById(R.id.floating_menu);

		//findvalue of the floatingpointbutton
		fuel_add=findViewById(R.id.floatingActionButton_fuel_add);
		expense_add=findViewById(R.id.floatingAction_expense_add);
		income_add=findViewById(R.id.floatingAction_income_add);
		route_add=findViewById(R.id.floatingAction_route_add);
		reminder_add=findViewById(R.id.floatingAction_reminder_add);
		service_add=findViewById(R.id.floatingAction_service_add);

		//set onclick listener for floating buttons
		//fuel addting button
		fuel_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this, com.example.harshil.expensetracking.Add_Information.fuel_add.class);
				startActivity(intent);
			}
		});
		//income add button
		income_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,com.example.harshil.expensetracking.Add_Information.income_add.class);
				startActivity(intent);
			}
		});
		expense_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,com.example.harshil.expensetracking.Add_Information.expense_add.class);
				startActivity(intent);
			}
		});
		route_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,com.example.harshil.expensetracking.Add_Information.route_add.class);
				startActivity(intent);
			}
		});
		reminder_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,com.example.harshil.expensetracking.Add_Information.reminder_add.class);
				startActivity(intent);
			}
		});
		service_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,com.example.harshil.expensetracking.Add_Information.service_add.class);
				startActivity(intent);
			}
		});

		//find navigationview in the screen
		navigationView=findViewById(R.id.navigation_view);

		//set onclicklistender to each item in drawer
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				int id=item.getItemId();
				switch (id)
				{

					case R.id.Reports:
						Intent intent1=new Intent(MainActivity.this,reports.class);
						startActivity(intent1);
						break;
					case R.id.refuel:
						Intent intent2 = new Intent(MainActivity.this,where_to_refuel.class);
						startActivity(intent2);
						break;
					case R.id.incomeChart:
						Intent intent3=new Intent(MainActivity.this,My_places.class);
						startActivity(intent3);
						break;
					case R.id.refuelingChart:
						Intent intent17 = new Intent(MainActivity.this,refuelingChart.class);
						startActivity(intent17);
						break;
					case R.id.serviceChart:
						Intent intent18 = new Intent(MainActivity.this,serviceChart.class);
						startActivity(intent18);
						break;
					case R.id.Refueling:
						Intent intent4=new Intent(MainActivity.this,refueling.class);
						startActivity(intent4);
						break;
					case R.id.Expenses:
						Intent intent5=new Intent(MainActivity.this,expenses.class);
						startActivity(intent5);
						break;
					case R.id.Income:
						Intent intent6=new Intent(MainActivity.this,income.class);
						startActivity(intent6);
						break;
					case R.id.Services:
						Intent intent7=new Intent(MainActivity.this,services.class);
						startActivity(intent7);
						break;
					case R.id.Routes:
						Intent intent8=new Intent(MainActivity.this,routes.class);
						startActivity(intent8);
						break;
					case R.id.Reminders:
						Intent intent9=new Intent(MainActivity.this,reminders.class);
						startActivity(intent9);
						break;
					case R.id.F_calculator:
						Intent intent10=new Intent(MainActivity.this, Vehicle_List.class);
						startActivity(intent10);
						break;
//					case R.id.Achievements:
//						Intent intent11=new Intent(MainActivity.this,achievements.class);
//						startActivity(intent11);
//						break;
//					case R.id.Contact:
//						Intent intent14=new Intent(MainActivity.this,Contact.class);
//						startActivity(intent14);
//						break;
					case R.id.about:
						Intent intent15=new Intent(MainActivity.this,about.class);
						startActivity(intent15);
						break;
					case R.id.addvehicle:
						Intent intent16=new Intent(MainActivity.this, add_vehicle.class);
						startActivity(intent16);
						break;

				}
				return true;
			}
		});
	}

//	On Back Preseesed Finish()

	@Override
	public void onBackPressed() {


//		Initialize AlertDialog
		builder = new AlertDialog.Builder(MainActivity.this,R.style.ALeartDialogBoxtheme);

//		set message

		builder.setMessage("Do you want to exit?");

//		set positive button

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});

		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent=new Intent(MainActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});

		alertDialog = builder.create();
		alertDialog.show();
	}
}
