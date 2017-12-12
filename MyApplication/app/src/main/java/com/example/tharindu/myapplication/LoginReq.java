package com.example.tharindu.myapplication;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class LoginReq extends StringRequest {
    private static  final String LOGIN_REQUEST_URL=" http://192.168.43.76/Tlog/login.php";
    private Map<String , String> params;


    public LoginReq(String name , String password , Response.Listener<String>listner){
        super(Request.Method.POST , LOGIN_REQUEST_URL , listner , null);
        params = new HashMap<>();
        params.put("name" , name);
        params.put("password" , password);


    }
    @Override
    public Map<String , String> getParams(){
        return params;
    }


}

