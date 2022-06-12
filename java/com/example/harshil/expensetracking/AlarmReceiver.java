package com.example.harshil.expensetracking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.github.mikephil.charting.utils.Utils;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Log.e("alarm!!!!!! ", " fire");
    }
}



