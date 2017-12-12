package com.example.tharindu.myapplication;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.estimote.coresdk.common.config.EstimoteSDK;
import com.estimote.coresdk.observation.region.RegionUtils;
import com.estimote.coresdk.observation.utils.Proximity;
import com.estimote.coresdk.recognition.packets.EstimoteLocation;
import com.estimote.coresdk.service.BeaconManager;

import java.util.List;

public class NotificationsClass extends Application{
    private boolean beaconNotificationsEnabled = false;
    @Override
    public void onCreate() {
        super.onCreate();
        EstimoteSDK.initialize(getApplicationContext(), "kgks999-gmail-com-s-notifi-e97", "dcfebbbb49f18f3bc7cc935de13d24e3");

    }

    public void enableBeaconNotifications() {


        if (beaconNotificationsEnabled) { return; }

        BeaconNotificationsManager beaconNotificationsManager = new BeaconNotificationsManager(this);
        beaconNotificationsManager.addNotification(
                "d0c4542b336fc08e3655d92ab398683a",
                "Tap here to get notifications",
                "Goodbye");
        beaconNotificationsManager.startMonitoring();

        beaconNotificationsEnabled = true;
    }

    public boolean isBeaconNotificationsEnabled() {
        return beaconNotificationsEnabled;
    }


}
