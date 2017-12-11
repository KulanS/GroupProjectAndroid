package com.example.tharindu.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.estimote.coresdk.common.config.EstimoteSDK;
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.RegionUtils;
import com.estimote.coresdk.observation.utils.Proximity;
import com.estimote.coresdk.recognition.packets.EstimoteLocation;
import com.estimote.coresdk.service.BeaconManager;

import java.util.List;

public class BeaconActivity extends AppCompatActivity {

    BeaconManager beaconManager = new BeaconManager(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_activity);

        EstimoteSDK.initialize(getApplicationContext(),"kgks999-gmail-com-s-notifi-e97", "dcfebbbb49f18f3bc7cc935de13d24e3");

        /*beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override public void onServiceReady() {
                beaconManager.startLocationDiscovery();
            }
        });

        beaconManager.setLocationListener(new BeaconManager.LocationListener() {
            @Override
            public void onLocationsFound(List<EstimoteLocation> beacons) {
                Log.d("LocationListener", "Nearby beacons: " + beacons);
            }
        });*/

        // 2. Initialize the beacon manager by connecting to the scanning service

    }

    @Override
    protected void onResume() {//manual overide
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }

    //@Override
    /*protected void onDestroy() {
        super.onDestroy();
        //beaconManager.disconnect();
    }*/

    /*private boolean notificationAlreadyShown = false;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(String title, String message) {
        if (notificationAlreadyShown) { return; }

        Intent notifyIntent = new Intent(this, BeaconActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
        notificationAlreadyShown = true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onLocationsFound(List<EstimoteLocation> beacons) {
        Log.d("LocationListener", "Nearby beacons: " + beacons);

        // replace with an identifier of your own beacon
        // you can find the identifier on the Estimote Cloud "Beacons" dashboard
        // or in the Estimote app from the Google Play Store
        String beaconId = "d0c4542b336fc08e3655d92ab398683a";

        for (EstimoteLocation beacon : beacons) {
            if (beacon.id.toString().equals(beaconId)
                    && RegionUtils.computeProximity(beacon) == Proximity.NEAR) {
                showNotification("Hello world", "Looks like you're near a beacon.");
            }
        }
    }*/


}
