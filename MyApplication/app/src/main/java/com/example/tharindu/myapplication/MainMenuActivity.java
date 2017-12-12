package com.example.tharindu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;

public class MainMenuActivity extends AppCompatActivity {
    private Button btnCart, btnShops, btnProfile, btnSettings, btnLogOut;

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu_activity);

        init();
        clickCart();
       /* clickShops();
        clickProfile();
        clickSettings();*/
        clickLogOut();


    }

    private void init(){ //mapping xml and java
        btnCart = (Button)findViewById(R.id.btnCart);
        btnShops = (Button)findViewById(R.id.btnShops);
        //btnProfile = (Button)findViewById(R.id.btnProfile);
        //btnSettings = (Button)findViewById(R.id.btnSettings);
        btnLogOut = (Button)findViewById(R.id.btnLogOut);
    }

    private void clickCart(){
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenuActivity.this, CartActivity.class);
                startActivity(i);

            }
        });

    }

    /*private void clickShops(){
        btnShops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenuActivity.this, ShopsActivity.class);
                startActivity(i);

            }
        });
    }*/

    /*private void clickProfile(){
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenuActivity.this, ProfileActivity.class);
                startActivity(i);

            }
        });
    }

    private void clickSettings(){
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenuActivity.this, SettingsActivity.class);
                startActivity(i);

            }
        });
    }*/

    private void clickLogOut(){
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenuActivity.this, BeaconActivity.class);
                startActivity(i);
            }
        });
    }

}
