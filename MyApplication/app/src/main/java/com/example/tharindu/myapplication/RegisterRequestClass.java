package com.example.tharindu.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class   RegisterRequestClass extends StringRequest{
    private static final String REGISTER_REQUEST_URL = "http://192.168.43.76/Tlog/register.php";
    private Map<String, String> params;

    public RegisterRequestClass(String name, String email, String password,Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
        //parms.put("compassword",compassword);
    }


    @Override
    public Map<String , String> getParams(){
        return params;
    }
}

