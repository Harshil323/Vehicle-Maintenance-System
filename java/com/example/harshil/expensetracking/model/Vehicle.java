package com.example.harshil.expensetracking.model;

public class Vehicle {

    private String vehicletype;
    private String vehiclename;
    private String vehiclemanufacturer;
    private String vehiclemodel;
    private String vehicleplates;
    private String year;
    private String fuel;
    private String fuel_capacity;
    private String chassis;
    private String idnumber;
    int ID;

    public Vehicle(String vehicletype, String vehiclename, String vehiclemanufacturer, String vehiclemodel, String vehicleplates, String year, String fuel, String fuel_capacity, String chassis) {

        this.vehicletype = vehicletype;
        this.vehiclename = vehiclename;
        this.vehiclemanufacturer = vehiclemanufacturer;
        this.vehiclemodel = vehiclemodel;
        this.vehicleplates = vehicleplates;
        this.year = year;
        this.fuel = fuel;
        this.fuel_capacity = fuel_capacity;
        this.chassis = chassis;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getVehiclename() {
        return vehiclename;
    }

    public void setVehiclename(String vehiclename) {
        this.vehiclename = vehiclename;
    }

    public String getVehiclemanufacturer() {
        return vehiclemanufacturer;
    }

    public void setVehiclemanufacturer(String vehiclemanufacturer) {
        this.vehiclemanufacturer = vehiclemanufacturer;
    }

    public String getVehiclemodel() {
        return vehiclemodel;
    }

    public void setVehiclemodel(String vehiclemodel) {
        this.vehiclemodel = vehiclemodel;
    }

    public String getVehicleplates() {
        return vehicleplates;
    }

    public void setVehicleplates(String vehicleplates) {
        this.vehicleplates = vehicleplates;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getFuel_capacity() {
        return fuel_capacity;
    }

    public void setFuel_capacity(String fuel_capacity) {
        this.fuel_capacity = fuel_capacity;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }


}
