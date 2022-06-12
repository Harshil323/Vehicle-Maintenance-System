package com.example.harshil.expensetracking.model;

public class Reminder {

    String reminderfor;
    String category;
    String repeat;
    String note;
    String date;
    int ID;

    public Reminder( String reminderfor,String date, String category, String repeat, String note) {
        this.reminderfor = reminderfor;
        this.category = category;
        this.repeat = repeat;
        this.note = note;
        this.date=date;
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

    public String getReminderfor() {
        return reminderfor;
    }

    public void setReminderfor(String reminderfor) {
        this.reminderfor = reminderfor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
