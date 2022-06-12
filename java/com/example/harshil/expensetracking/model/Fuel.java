package com.example.harshil.expensetracking.model;

public class Fuel {
    int ID;
    String date;
    String odometer;
    String fuelType;
    String cost;
    String liters;
    String gasstation;
    String reason;
    String note;

    public Fuel(String date, String odometer, String fuelType, String cost, String liters, String gasstation, String reason, String note) {
        this.date = date;
        this.odometer = odometer;
        this.fuelType = fuelType;
        this.cost = cost;
        this.liters = liters;
        this.gasstation = gasstation;
        this.reason = reason;
        this.note = note;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getLiters() {
        return liters;
    }

    public void setLiters(String liters) {
        this.liters = liters;
    }

    public String getGasstation() {
        return gasstation;
    }

    public void setGasstation(String gasstation) {
        this.gasstation = gasstation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
