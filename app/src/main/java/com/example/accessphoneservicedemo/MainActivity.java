package com.example.accessphoneservicedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CALL = 1;
    private static final String[] PERMISSIONS = {
            Manifest.permission.CALL_PHONE
    };

    public void verifyThatWeCanCallSomeone(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);

        if(permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS, CALL);
        }
    }


    private Button websiteButton, searchButton, someoneButton, dialPadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyThatWeCanCallSomeone(MainActivity.this);

        websiteButton = findViewById(R.id.button_website);
        searchButton = findViewById(R.id.button_search);
        someoneButton = findViewById(R.id.button_call_someone);
        dialPadButton = findViewById(R.id.button_dial_pad);

        websiteButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        someoneButton.setOnClickListener(this);
        dialPadButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_website:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.programmingmasters.in"));
                startActivity(intent);
                break;
            case R.id.button_search:
                Intent intent1 = new Intent(Intent.ACTION_WEB_SEARCH);
                startActivity(intent1);
                Log.i("TAG", "This is the web search");
                break;
            case R.id.button_call_someone:
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL);
                } else {
                    Intent intent2 = new Intent(Intent.ACTION_CALL);
                    intent2.setData(Uri.parse("tel:08135669272"));
                    startActivity(intent2);
                }
                break;
            case R.id.button_dial_pad:
                Intent intent4 = new Intent(Intent.ACTION_DIAL);
                startActivity(intent4);
                break;

        }
    }
}