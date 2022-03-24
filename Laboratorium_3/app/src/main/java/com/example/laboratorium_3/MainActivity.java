package com.example.laboratorium_3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getRequest (View view) {
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
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }




}