package com.example.tharindu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.estimote.coresdk.common.config.EstimoteSDK;
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.recognition.packets.EstimoteLocation;
import com.estimote.coresdk.service.BeaconManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText etEmail, etPassword, etAreYouNotRegisterded;
    private Button btnLogIn, btnSignUp;
    BeaconManager beaconManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //EstimoteSDK.initialize(getApplicationContext(), "kgks999-gmail-com-s-notifi-e97", "dcfebbbb49f18f3bc7cc935de13d24e3");
        init();
        clickSignUp();
        clickLogin();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    protected void onResume(){
        super.onResume();
//        NotificationsClass app = (NotificationsClass) getApplication();
//
//        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
//            Log.e(TAG, "Can't scan for beacons, some pre-conditions were not met");
//            Log.e(TAG, "Read more about what's required at: http://estimote.github.io/Android-SDK/JavaDocs/com/estimote/sdk/SystemRequirementsChecker.html");
//            Log.e(TAG, "If this is fixable, you should see a popup on the app's screen right now, asking to enable what's necessary");
//        } else if (!app.isBeaconNotificationsEnabled()) {
//            Log.d(TAG, "Enabling beacon notifications");
//            app.enableBeaconNotifications();
//        }
    }


    /*private void scan(){
        beaconManager.startLocationDiscovery();
    }

    private void getBeacons(){
        beaconManager.setLocationListener(new BeaconManager.LocationListener() {
            @Override
            public void onLocationsFound(List<EstimoteLocation> beacons) {
                Log.d("LocationListener", "Nearby beacons: " + beacons);
            }
        });
    }*/


    private void init(){ //mapping xml and java
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnLogIn = (Button)findViewById(R.id.btnLogIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
    }

    private void clickSignUp(){ //what happens when click signup button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    private void clickLogin(){ //what happens when click login button
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainMenuActivity.class);
                startActivity(i);
            }
        });
    }
}
