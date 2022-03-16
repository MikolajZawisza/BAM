package com.example.laboratorium_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class NumberReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        int number = intent.getIntExtra("value",0);
        String number_value = String.valueOf(number);
        String user = intent.getStringExtra("data");

        Log.e("NumberReceiver", number_value);
        Log.e("Received message", user);

        throw new UnsupportedOperationException("Not yet implemented");
    }
}