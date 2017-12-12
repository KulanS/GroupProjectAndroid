package com.example.tharindu.myapplication;


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etEmail=(EditText)findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button btnSignup = (Button) findViewById(R.id.btnSignup);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = etUsername.getText().toString();
                final String email=etEmail.getText().toString();
                final String password = etPassword.getText().toString();



                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        Log.d("aaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaa");

                        try {
                            JSONObject jsonResponse = new JSONObject(response);


                            boolean success = jsonResponse.getBoolean("success");


                            if (success){
                                Intent intent = new Intent(SignUpActivity.this, MainMenuActivity.class);
                                SignUpActivity.this.startActivity(intent);
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                RegisterRequestClass registerRequest = new RegisterRequestClass(name, email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                queue.add(registerRequest);
            }
        });
    }





}
