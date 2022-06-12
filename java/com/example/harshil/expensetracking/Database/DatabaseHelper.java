package com.example.harshil.expensetracking.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.harshil.expensetracking.model.Income;
import com.example.harshil.expensetracking.model.Vehicle;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DB_name="VMC.db";
    static final int DB_version=2;
    private static final String table_1="Vehicle";
    private static final String table_2="Income";
    private static final String table_3="Expense";
    private static final String table_4="Route";
    private static final String table_5="reminder";
    private static final String table_6="Service";
    private static final String table_7="Fuel";

//    Column for table vehicle

    private static final String Col_Id="Id";
    private static final String V_Column_1="Vehicle_type";
    private static final String V_Column_2="Vehicle_name";
    private static final String V_Column_3="Vehicle_Manufacturar";
    private static final String V_Column_4="Vehicle_model";
    private static final String V_Column_5="Vehicle_plates";
    private static final String V_Column_6="Vehicle_year";
    private static final String V_Column_7="Vehicle_fuel";
    private static final String V_Column_8="Vehicle_fuel_capacity";
    private static final String V_Column_9="Vehicle_chassi";
    private static final String V_Column_10="Vehicle_idnumber";
    private static final String V_Column_11="Notes";

//    Column for income

    private static final String I_Column_0 = "ID";
    private static final String I_Column_1 = "Date";
    private static final String I_Column_2 = "Odometer";
    private static final String I_Column_3 = "Value";
    private static final String I_Column_4 = "Type_Income";
    private static final String I_Column_5 = "Notes";


//  Column for Expense Table


    public static final String E_Column_0 = "ID";
    private static final String E_Column_1 = "Date";
    private static final String E_Column_2 = "Odometer";
    private static final String E_Column_3 = "Category";
    private static final String E_Column_4 = "Value";
    private static final String E_Column_5 = "Location";
    private static final String E_Column_6 = "Reason";
    private static final String E_Column_7 = "Notes";


//    Column for Route tabel

    private static final String R_Column_0 = "ID";
    private static final String R_Column_1 = "StartLocation";
    private static final String R_Column_2 = "StartDate";
    private static final String R_Column_3 = "StartOdometer";
    private static final String R_Column_4 = "EndLocation";
    private static final String R_Column_5 = "EndDate";
    private static final String R_Column_6 = "FinalOdometer";
    private static final String R_Column_7 = "Reason";
    private static final String R_Column_8 = "Note";


//    Column for Service Table

    private static final String S_Column_0 = "ID";
    private static final String S_Column_1 = "Date";
    private static final String S_Column_2 = "Odometer";
    private static final String S_Column_3 = "Value";
    private static final String S_Column_4 = "Category";
    private static final String S_Column_5 = "Reason";
    private static final String S_Column_6 = "Note";

//    Column for Reminder Table

    private static final String Reminder_Column_0 = "ID";
    private static final String Reminder_Column_1 = "ReminderFor";
    private static final String Reminder_Column_2 = "Date";
    private static final String Reminder_Column_3 = "Category";
    private static final String Reminder_Column_4 = "Repeat";
    private static final String Reminder_Column_5 = "Note";


//    Column for Fuel Table

    private static final String Fuel_Column_0 = "ID";
    private static final String Fuel_Column_1 = "Date";
    private static final String Fuel_Column_2 = "Odometer";
    private static final String Fuel_Column_3 = "FuelType";
    private static final String Fuel_Column_4 = "Cost";
    private static final String Fuel_Column_5 = "Liters";
    private static final String Fuel_Column_6 = "Gas Station";
    private static final String Fuel_Column_7 = "Reason";
    private static final String Fuel_Column_8 = "Note";

//    Create table query for vehicle

    private static final String create_tabele_1;

//    Create Table Qury for income
    private static final String create_tabele_2;

//    Create table qury for expense

    private static final String create_tabele_3;

//    Create table query for Route

    private static final String create_talele_4;

//    Create table query for Reminder

    private static final String create_tablele_5;

//    Create table qury for Services
    private static final String create_tabele_6;

    /* Create table query for Fuel */

    private static final String create_tabele_7;

    static {
        create_tabele_7 = "CREATE TABLE " + table_7 + " ( " +
                Fuel_Column_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Fuel_Column_1 + " DATETIME NOT NULL, " +
                Fuel_Column_2 + " TEXT NOT NULL, " +
                Fuel_Column_3 + " TEXT NOT NULL, " +
                Fuel_Column_4 + " TEXT NOT NULL, " +
                Fuel_Column_5 + " TEXT NOT NULL, " +
                Fuel_Column_6 + " TEXT NOT NULL, " +
                Fuel_Column_7 + " TEXT NOT NULL, " +
                Fuel_Column_8 + " TEXT NOT NULL); ";

        create_tabele_6 = "CREATE TABLE " + table_6 + " ( " +
                S_Column_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                S_Column_1 + " TEXT NOT NULL, " +
                S_Column_2 + " TEXT NOT NULL, " +
                S_Column_3 + " TEXT NOT NULL, " +
                S_Column_4 + " TEXT NOT NULL, " +
                S_Column_5 + " TEXT NOT NULL, " +
                S_Column_6 + " TEXT ); ";

        create_tabele_3 = "CREATE TABLE " + table_3 + " ( " +
                E_Column_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                E_Column_1 + " DATETIME NOT NULL, " +
                E_Column_2 + " TEXT NOT NULL, " +
                E_Column_3 + " TEXT NOT NULL, " +
                E_Column_4 + " TEXT NOT NULL, " +
                E_Column_5 + " TEXT NOT NULL, " +
                E_Column_6 + " TEXT NOT NULL, " +
                E_Column_7 + " TEXT NOT NULL); ";

        create_tabele_2 = "CREATE TABLE " + table_2 + " (" +
                I_Column_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                I_Column_1 + " TEXT NOT NULL, " +
                I_Column_2 + " INTEGER NOT NULL, " +
                I_Column_3 + " INTEGER NOT NULL, " +
                I_Column_4 + " TEXT NOT NULL, " +
                I_Column_5 + " TEXT); ";

        create_tabele_1 = "CREATE TABLE " + table_1 + " ( " +
                Col_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                V_Column_1 + " TEXT NOT NULL, " +
                V_Column_2 + " TEXT NOT NULL, " +
                V_Column_3 + " TEXT NOT NULL, " +
                V_Column_4 + " TEXT NOT NULL, " +
                V_Column_5 + " TEXT NOT NULL, " +
                V_Column_6 + " INTEGER NOT NULL, " +
                V_Column_7 + " TEXT NOT NULL, " +
                V_Column_8 + " INTEGER NOT NULL, " +
                V_Column_9 + " TEXT , " +
                V_Column_10 + " TEXT); ";

        create_talele_4 = " CREATE TABLE " + table_4 + " (" +
                R_Column_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                R_Column_1 + " TEXT NOT NULL, " +
                R_Column_2 + " TEXT NOT NULL, " +
                R_Column_3 + " INTEGER NOT NULL, " +
                R_Column_4 + " TEXT NOT NULL, " +
                R_Column_5 + " TEXT NOT NULL, " +
                R_Column_6 + " INTEGER NOT NULL, " +
                R_Column_7 + " TEXT NOT NULL, " +
                R_Column_8 + " TEXT NOT NULL);";

        create_tablele_5 = " CREATE TABLE " + table_5 + " ( " +
                Reminder_Column_0 + " INTEGER PRIMARY KEY NOT NULL, " +
                Reminder_Column_1 + " TEXT NOT NULL, " +
                Reminder_Column_2 + " DATETIME NOT NULL, " +
                Reminder_Column_3 + " TEXT NOT NULL, " +
                Reminder_Column_4 + " TEXT NOT NULL, " +
                Reminder_Column_5 + " TEXT NOT NULL); ";

    }







    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(create_tabele_1);
        db.execSQL(create_tabele_2);
        db.execSQL(create_tabele_3);
        db.execSQL(create_talele_4);
        db.execSQL(create_tablele_5);
        db.execSQL(create_tabele_6);
        db.execSQL(create_tabele_7);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ table_1);
        db.execSQL("DROP TABLE IF EXISTS "+ table_2);
        db.execSQL("DROP TABLE IF EXISTS "+ table_3);
        db.execSQL("DROP TABLE IF EXISTS "+ table_4);
        db.execSQL("DROP TABLE IF EXISTS "+ table_5);
        db.execSQL("DROP TABLE IF EXISTS "+ table_6);
        db.execSQL("DROP TABLE IF EXISTS "+ table_7);


        this.onCreate(db);


    }

    public long insertVehicle(String type,String name,String manufacturer,String model,String plates,int year,String fuel,int capacity,String chassis,String idnumber){

        SQLiteDatabase database = getWritableDatabase();

        ContentValues vehicleValues= new ContentValues();
        vehicleValues.put(V_Column_1,type);
        vehicleValues.put(V_Column_2,name);
        vehicleValues.put(V_Column_3,manufacturer);
        vehicleValues.put(V_Column_4,model);
        vehicleValues.put(V_Column_5,plates);
        vehicleValues.put(V_Column_6,year);
        vehicleValues.put(V_Column_7,fuel);
        vehicleValues.put(V_Column_8,capacity);
        vehicleValues.put(V_Column_9,chassis);
        vehicleValues.put(V_Column_10,idnumber);


        return  database.insertWithOnConflict(table_1,Col_Id,vehicleValues,SQLiteDatabase.CONFLICT_REPLACE);

    }

    public long insertIncome(String date,int odometer,int values,String category,String note){

        SQLiteDatabase database = this.getWritableDatabase();


        ContentValues incomeValues= new ContentValues();
        incomeValues.put(I_Column_1,date);
        incomeValues.put(I_Column_2,odometer);
        incomeValues.put(I_Column_3,values);
        incomeValues.put(I_Column_4,category);
        incomeValues.put(I_Column_5,note);


        long insert = database.insertWithOnConflict(table_2,I_Column_0,incomeValues,SQLiteDatabase.CONFLICT_REPLACE);
        return insert;

    }

    public long insertExpense(String date,int Odometer,String category,int value,String location,String reason,String note){

        SQLiteDatabase database = getWritableDatabase();

        ContentValues expenseValue =  new ContentValues();
        expenseValue.put("Date",date);
        expenseValue.put("Odometer",Odometer);
        expenseValue.put("Category",category);
        expenseValue.put("Value",value);
        expenseValue.put("Location",location);
        expenseValue.put("Reason",reason);
        expenseValue.put("Notes",note);

        long id = database.insertWithOnConflict(table_3,E_Column_0,expenseValue,SQLiteDatabase.CONFLICT_REPLACE);
        return id;

    }

    public long insertRoute(String startlocation,String startDate,int startOdometer,String endLocation,String endDate,int endOdometer,String reason,String note){

        SQLiteDatabase database=getWritableDatabase();

        ContentValues routeValue = new ContentValues();
        routeValue.put(R_Column_1,startlocation);
        routeValue.put(R_Column_2,startDate);
        routeValue.put(R_Column_3,startOdometer);
        routeValue.put(R_Column_4,endLocation);
        routeValue.put(R_Column_5,endDate);
        routeValue.put(R_Column_6,endOdometer);
        routeValue.put(R_Column_7,reason);
        routeValue.put(R_Column_8,note);

        long id = database.insertWithOnConflict(table_4,R_Column_0,routeValue,SQLiteDatabase.CONFLICT_REPLACE);
        return id;
    }

    public long insertReminder(String ReminderFor,String Date,String Category,String Repeat,String Note){

        SQLiteDatabase database = getWritableDatabase();

        ContentValues reminderValues = new ContentValues();
        reminderValues.put("ReminderFor",ReminderFor);
        reminderValues.put("Date",Date);
        reminderValues.put("Category",Category);
        reminderValues.put("Repeat",Repeat);
        reminderValues.put("Note",Note);

        long id = database.insertWithOnConflict(table_5,Reminder_Column_0, reminderValues,SQLiteDatabase.CONFLICT_REPLACE);

        return id;

    }

    public long insertService(String date,int odometer,int value,String category,String reason,String note){

        SQLiteDatabase database = getWritableDatabase();

        ContentValues serviceValue = new ContentValues();
        serviceValue.put("Date",date);
        serviceValue.put("Odometer",odometer);
        serviceValue.put("Value",value);
        serviceValue.put("Category",category);
        serviceValue.put("Reason",reason);
        serviceValue.put("Note",note);

        long id = database.insertWithOnConflict(table_6,S_Column_0, serviceValue,SQLiteDatabase.CONFLICT_REPLACE);

        return id;
    }

    public long insertFuel(String date,int odometer,String fuelType,int cost,int liters,String gasStation,String reason,String note){

        SQLiteDatabase database = getWritableDatabase();

        ContentValues fuelValues = new ContentValues();
        fuelValues.put("Date",date);
        fuelValues.put("Odometer",odometer);
        fuelValues.put("FuelType",fuelType);
        fuelValues.put("Cost",cost);
        fuelValues.put("Liters",liters);
        fuelValues.put("Gas",gasStation);
        fuelValues.put("Reason",reason);
        fuelValues.put("Note",note);

        long id = database.insertWithOnConflict(table_7, Fuel_Column_0, fuelValues,SQLiteDatabase.CONFLICT_REPLACE);

        return id;

    }

//  Retrieve the data from database

    public Cursor getAllVehicle(SQLiteDatabase database){

        String[] colums= {V_Column_1,V_Column_2,V_Column_3,V_Column_4,V_Column_5,V_Column_6,V_Column_7,V_Column_8,V_Column_9,V_Column_10};
        Cursor cursor = database.query(table_1,colums,null,null,null,null,null);
        return cursor;

    }

//   Retrieve the expense from database
    public Cursor getAllExpense(SQLiteDatabase database){

        String[] columns = {E_Column_1,E_Column_2,E_Column_3,E_Column_4,E_Column_5,E_Column_6,E_Column_7};
        Cursor cursor = database.query(table_3, columns, null, null, null, null, null);
        return  cursor;

    }

//   Retrieve the income from database
    public Cursor getAllIncome(SQLiteDatabase database){

        String[] colums= {I_Column_1,I_Column_2,I_Column_3,I_Column_4,I_Column_5};
        Cursor cursor = database.query(table_2, colums, null, null, null, null, null);
        return  cursor;

    }

//   Retrieve the service Data

    public Cursor getAllServices(SQLiteDatabase database){

        String[] columns= {S_Column_1,S_Column_2,S_Column_3,S_Column_4,S_Column_5,S_Column_6};
        Cursor cursor = database.query(table_6,columns,null,null,null,null,null);
        return  cursor;

    }

//    Retrieve the reminder data
     public  Cursor getAllReminder(SQLiteDatabase database){

        String[] columns = {Reminder_Column_1,Reminder_Column_2,Reminder_Column_3,Reminder_Column_4,Reminder_Column_5};
        Cursor cursor = database.query(table_5,columns,null,null,null,null,null);
        return cursor;

     }

//     Retrieve route database data
    public Cursor getAllroute(SQLiteDatabase sqLiteDatabase)
    {

        String[] columns = {R_Column_1,R_Column_2,R_Column_3,R_Column_4,R_Column_5,R_Column_6,R_Column_7,R_Column_8};
        Cursor cursor = sqLiteDatabase.query(table_4, columns, null, null, null, null, null);
        return  cursor;
    }
//    Retrieve fuel data
    public Cursor getAllFuel(SQLiteDatabase database){

        String[] columns = {Fuel_Column_1,Fuel_Column_2,Fuel_Column_3,Fuel_Column_4,Fuel_Column_5,Fuel_Column_6,Fuel_Column_7,Fuel_Column_8};
        Cursor cursor = database.query(table_7, columns, null, null, null, null, null);
        return cursor;
    }

//  Delete Service

    public boolean deleteService(int odometer,int value){

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("delete from "+table_6+" where "+S_Column_2+" = "+odometer+" AND  "+S_Column_3+" = "+value);
        return true;

    }


//    Delete Route

    public boolean deleteRoute(int start,int end){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("delete from "+table_4+" where "+R_Column_3+" = "+start+" AND "+R_Column_6+" = "+end);
        return true;
    }

//    Delete income
    public boolean deleteIncome(int odometer,int value)
    {
        SQLiteDatabase database =getWritableDatabase();
        database.execSQL("delete from "+table_2+" where "+I_Column_2+" = "+odometer+" AND "+I_Column_3+" = "+value );
        return true;
    }

//    Delete expense

    public boolean deleteExpense(int odometer,int value)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("delete from "+table_3+" where "+E_Column_2+" = "+odometer+" AND "+E_Column_4+" = "+value);
        return true;
    }

//    Delete fuel
    public boolean deleteFuel(int odometer,int Cost){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("delete from "+table_7+" where "+Fuel_Column_2+" = "+odometer+" AND "+Fuel_Column_5+" = "+Cost);
        return true;
    }

//    Delete Vehicle

    public boolean deleteVehicle(String plates)
    {
        SQLiteDatabase database = getWritableDatabase();
        String whereClause = "Vehicle_plates = ?";
        String whereArgs[] = {plates};
        int id = database.delete(table_1,whereClause,whereArgs);
        if (id>0){
            return true;
        }
        else {
            return true;
        }
    }

//    Delete Reminder
    public boolean deleteReminder(String category,String repeat)
    {
        SQLiteDatabase database = getWritableDatabase();
        String whereClause = " Category = ? AND Repeat = ?";
        String whereArgs[] = {category,repeat};
        int id = database.delete(table_5,whereClause,whereArgs);
        if (id>0){
            return  true;

        }
        else {
            return false;
        }
    }

//    GET ALL INCOME DATA
    public ArrayList<Integer> getAllIncome(){

        ArrayList<Integer> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select Value From " +table_2, null);
        while (cursor.moveToNext()){
            list.add(cursor.getInt(cursor.getColumnIndex("Value")));
        }
        return  list;
    }

    public ArrayList<Integer> getAllExpense(){

        ArrayList<Integer> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ArrayList data = new ArrayList();
        Cursor cursor = sqLiteDatabase.rawQuery("Select Value  From " +table_3, null);
        while (cursor.moveToNext()){
            list.add(cursor.getInt(cursor.getColumnIndex("Value")));

        }
        return list;
    }

    public ArrayList<Integer> getAllRefueling(){

        ArrayList<Integer> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ArrayList data = new ArrayList();
        Cursor cursor = sqLiteDatabase.rawQuery("Select Liters  From " +table_7, null);
        while (cursor.moveToNext()){
            list.add(cursor.getInt(cursor.getColumnIndex("Liters")));

        }
        return list;
    }

    public ArrayList<Integer> getAllService(){

        ArrayList<Integer> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ArrayList data = new ArrayList();
        Cursor cursor = sqLiteDatabase.rawQuery("Select Value  From " +table_6, null);
        while (cursor.moveToNext()){
            list.add(cursor.getInt(cursor.getColumnIndex("Value")));

        }
        return list;
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        db.setVersion(oldVersion);
    }
}
