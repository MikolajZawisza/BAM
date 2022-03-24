package com.example.laboratorium_3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.util.Log;
import android.widget.Button;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class MainActivity extends AppCompatActivity {

    Button get_request;
    private final NetworkReceiver NetworkReceiver = new NetworkReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(NetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        get_request = (Button) findViewById(R.id.get_request);
        get_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        // TODO Exceptions handling

                        try {
                            // From https://www.baeldung.com/java-http-request
                            // 1. Creating a request
                            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
                            HttpURLConnection con = (HttpURLConnection) url.openConnection();
                            con.setRequestMethod("GET");

                            // 5. Setting request headers
                            con.setRequestProperty("Content-Type", "application/json");

                            // 6. Configuring timeouts
                            con.setConnectTimeout(5000);
                            con.setReadTimeout(5000);

                            // 8. Reading the response
                            int status = con.getResponseCode();

                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(con.getInputStream()));
                            String inputLine;
                            StringBuffer content = new StringBuffer();
                            while ((inputLine = in.readLine()) != null) {
                                content.append(inputLine);
                            }
                            in.close();

                            con.disconnect();

                            Log.d("Zwrócone dane:", content.toString());
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
            }
        });
    }
}