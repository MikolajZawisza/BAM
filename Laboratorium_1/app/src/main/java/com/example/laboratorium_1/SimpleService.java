package com.example.laboratorium_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class SimpleService extends Service {

    Timer t;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service is Working", Toast.LENGTH_LONG).show();

        t = new Timer();
        BetterTimer();
        return super.onStartCommand(intent, flags, startId);
    }

    public void BetterTimer()
    {
        t.scheduleAtFixedRate(new TimerTask() {
            int time = 0;

            @Override
            public void run()    {
                time += 1;
                String time_value = String.valueOf(time);
                Log.e("BetterTimer value", time_value);
            }
        }, 0, 1000);
    }

    public void onDestroy(){
            Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
            t.cancel();
            t.purge();
        super.onDestroy();
    }
}
