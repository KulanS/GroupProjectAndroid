package com.example.tharindu.myapplication;


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogIn, btnSignUp;
    String name,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        final EditText etPassword = (EditText)findViewById(R.id.etPassword);
        final  Button btnLogIn = (Button)findViewById(R.id.btnLogIn);
        final Button btnSignUp = (Button)findViewById(R.id.btnSignUp);
        final EditText etUsername=(EditText)findViewById(R.id.etUsername);


        btnLogIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final String name =etUsername.getText().toString();
                final String password=etPassword.getText().toString();

                Response.Listener<String> responseListener =new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(MainActivity.this ,MainMenuActivity.class );
                                MainActivity.this.startActivity(intent);

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LoginReq loginreq = new LoginReq(name, password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginreq);

            }

        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        NotificationsClass app = (NotificationsClass) getApplication();

        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            //Log.e(TAG, "Can't scan for beacons, some pre-conditions were not met");
            //Log.e(TAG, "Read more about what's required at: http://estimote.github.io/Android-SDK/JavaDocs/com/estimote/sdk/SystemRequirementsChecker.html");
            //Log.e(TAG, "If this is fixable, you should see a popup on the app's screen right now, asking to enable what's necessary");
        } else if (!app.isBeaconNotificationsEnabled()) {
            //Log.d(TAG, "Enabling beacon notifications");
            app.enableBeaconNotifications();
        }
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

}
