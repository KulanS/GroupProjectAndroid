package com.example.tharindu.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetShopClass extends AsyncTask<Void, Void, Void> {
    static ArrayList<String> shopID;
    static ArrayList<String> shopName;
    static ArrayList<String> phone;
    static ArrayList<String> address;
    static ArrayList<String> descreption;


    @Override
    protected Void doInBackground(Void... voids) {      /* Backgroud thread */

        return null;

    }

    public void getJSON(final String urlWebService) {
        /*
        * As fetching the json string is a network operation
        * And we cannot perform a network operation in main thread
        * so we need an AsyncTask
        * The constrains defined here are
        * Void -> We are not passing anything
        * Void -> Nothing at progress update as well
        * String -> After completion it should return a string and it will be the json string
        * */
        class GetJSON extends AsyncTask<Void, Void, String> {

            //this method will be called before execution
            //you can display a progress bar or something
            //so that user can understand that he should wait
            //as network operation may take some time
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            //this method will be called after execution
            //so here we are displaying a toast with the json string
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //FetchResultsActivity.tvShowJason.setText(s);

                try {

                    loadShop(s);
                    Log.d("hi", "*******SHOPCLASS*****");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i = 0; i<shopID.size(); i++){
                    Log.d(shopID.get(i), "****SHOPCLASS****");
                }

            }

            //in this method we are fetching the json string
            @Override
            protected String doInBackground(Void... voids) {



                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    //FetchResultsActivity fra = new FetchResultsActivity();

    private void loadShop(String json)throws JSONException{
        JSONArray jsonArray = new JSONArray(json);
        shopID = new ArrayList<String>() ;
        shopName = new ArrayList<String> ();
        phone = new ArrayList<String>();
        address = new ArrayList<String>();
        descreption = new ArrayList<String>();

        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            String tmpshopId = obj.getString("shopId");
            String tmpshopName = obj.getString("shopName");
            String tmpphone = obj.getString("phone");
            String tmpadress = obj.getString("adress");
            String tmpdescreption = obj.getString("descreption");
            shopID.add(tmpshopId);
            shopName.add(tmpshopName);
            phone.add(tmpphone);
            address.add(tmpadress);
            descreption.add(tmpdescreption);


            //fra.mDatabaseHelper.addDatatoShopTable(shopID, shopName, phone, adress, descreption);
        }
    }

}
