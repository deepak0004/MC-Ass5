package com.show.deepak.show_app;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Showing_Content extends AppCompatActivity {

    public static String i1 = "a";    // used in savedInstance
    TextView tv2;

    // Pressing back button takes us to first activity
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(i1, tv2.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing__content);
        tv2 = (TextView)findViewById(R.id.show_data);
        if( savedInstanceState!=null ) {
//            String gg = savedInstanceState.getString( i1 );
//            tv2.setText(gg);
            String gg = getIntent().getExtras().getString("TAGG");
            tv2.setText(gg);
        }
        else {
            String gg = getIntent().getExtras().getString("TAGG");
            tv2.setText(gg);
        }
    }
}