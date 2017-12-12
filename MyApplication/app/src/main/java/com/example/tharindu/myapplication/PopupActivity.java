package com.example.tharindu.myapplication;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_activity);

        //DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
        //Cursor data = mDatabaseHelper.getNotification();
        //String message = data.getString(1);
        String message = GetShopClass.descreption.get(0);
        AlertDialog.Builder builder = new AlertDialog.Builder(PopupActivity.this);
        builder.setMessage(message)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .create()
                .show();
    }
}
