package com.example.tharindu.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataProcessClass {

    ViewListActivity vla = new ViewListActivity();
    ArrayList mismatchArray = new ArrayList();
    ArrayList matchArray = new ArrayList();
    JSONArray jsonArray;

    public boolean itemExists(JSONArray jsonArray, String usernameToFind){
        for(String item:vla.listData){
            boolean result = jsonArray.toString().contains("\"username\":\""+usernameToFind+"\"");

            if(!result){
                mismatchArray.add(item);
            }else{
                matchArray.add(item);
            }

        }

        if(mismatchArray.size()==0){
            return true;
        }else{
            return  false;
        }

    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] heroes = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes[i] = obj.getString("name");
        }
        /*ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, heroes);
        listView.setAdapter(arrayAdapter);*/
    }



    /*public static void main(String[] args){



    }*/
}
