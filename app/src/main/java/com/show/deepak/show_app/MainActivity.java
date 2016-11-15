package com.show.deepak.show_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    StringBuffer storagee = new StringBuffer();
    public static String i1 = "a";
    TextView tv22;

//    public void onSaveInstanceState(Bundle outState) {
//        outState.putString(i1, tv22.getText().toString());
//        super.onSaveInstanceState(outState);
//    }

    private boolean check_internet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonn = (Button) findViewById(R.id.button);
        buttonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_internet()) {
                    new show_data().execute();
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class show_data extends AsyncTask<Void, Void, Void> {
        HttpURLConnection conn = null;
        BufferedReader readee = null;

        protected void onPreExecute() {
            super.onPreExecute();
            tv22 = (TextView)findViewById(R.id.loadd);
            tv22.setText("Loading");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("https://www.iiitd.ac.in/about");
                conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                InputStream stream = conn.getInputStream();  // Get HTML code in bytes

                readee = new BufferedReader(new InputStreamReader(stream));
                String line = "";

                while ((line = readee.readLine()) != null) {
                    //line = Html.fromHtml(line).toString();
                    storagee.append(line + "\n");
                    Log.d("TAG", line);
                }
                return null;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (conn != null) {
                    conn.disconnect();
                }
                try {
                    if (readee != null) {
                        readee.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tv22.setText("");
            super.onPostExecute(aVoid);
            startActivity( new Intent(MainActivity.this, Showing_Content.class).putExtra("TAGG", storagee.toString()) );
        }
    }
}