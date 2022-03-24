package com.example.laboratorium_3;

import android.util.Log;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Sieć", "Otrzymanie intencji przez receivera");

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null){
            Log.d("Sieć:", "Brak połączenia z internetem");
        }
        else {
            Log.d("Sieć", "Is connected: " + networkInfo.isConnected());
            if(networkInfo.getType() == 0)
                Log.d("Sieć", "Użycie: LTE");
            else
                Log.d("Sieć", "Użycie: WiFi");
        }
    }
}
