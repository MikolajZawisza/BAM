package com.example.laboratorium_3;

// Normal
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

//Dangerous
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.Manifest;
import androidx.core.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.widget.Toast;
import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity {

    Button get_request;
    private final NetworkReceiver NetworkReceiver = new NetworkReceiver();
    int READ_CONTACTS_PERMISSION_REQUEST_CODE = 100;


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

    public void getContacts(View view) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},READ_CONTACTS_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_CONTACTS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

                while (cursor.moveToNext()) {
                    String contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                    String displayName =cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    Log.d("Kontakty", "Kontakt nr. " + contactId + ": " + displayName);
                }
                cursor.close();
            }

            else {
                Toast.makeText(this, "Brak uprawnień", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(NetworkReceiver);
    }
}