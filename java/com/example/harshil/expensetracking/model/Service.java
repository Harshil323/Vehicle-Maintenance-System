package com.example.harshil.expensetracking.model;

public class Service {

    String date;
    String odometer;
    String value;
    String category;
    String reason;
    String note;
    int ID;
    public Service(String date, String odometer, String value, String category, String reason, String note) {
        this.date = date;
        this.odometer = odometer;
        this.value = value;
        this.category = category;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
