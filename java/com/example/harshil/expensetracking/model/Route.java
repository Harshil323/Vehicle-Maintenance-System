package com.example.harshil.expensetracking.model;

public class Route {

    String startLocation;
    String startDate;
    String startOdometer;
    String endLocation;
    String endDate;
    String endOdometer;
    String reason;
    String note;
    int ID;
    public Route(String startLocation, String startDate, String startOdometer, String endLocation, String endDate, String endOdometer, String reason, String note) {
        this.startLocation = startLocation;
        this.startDate = startDate;
        this.startOdometer = startOdometer;
        this.endLocation = endLocation;
        this.endDate = endDate;
        this.endOdometer = endOdometer;
        this.reason = reason;
        this.note = note;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartOdometer() {
        return startOdometer;
    }

    public void setStartOdometer(String startOdometer) {
        this.startOdometer = startOdometer;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndOdometer() {
        return endOdometer;
    }

    public void setEndOdometer(String endOdometer) {
        this.endOdometer = endOdometer;
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
