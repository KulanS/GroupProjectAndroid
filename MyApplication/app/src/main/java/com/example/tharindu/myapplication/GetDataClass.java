package com.example.tharindu.myapplication;

import android.Manifest;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static android.widget.Toast.LENGTH_SHORT;

public class GetDataClass extends AsyncTask<Void, Void, Void> {       /*this class is used to perform jason operations*/
    String data;
    String jsonString;
    static String[] heroes;
    static String[] matches;
    static String[] notMatches;
    static ArrayList<String> matchAL;
    static HashMap<String,ArrayList<String>> map;
    String test;


    @Override
    protected Void doInBackground(Void... voids) {      /* Backgroud thread */

        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {      /*UI thread*/
        super.onPostExecute(aVoid);
        //FetchResultsActivity.tvShowJason.setText(data);     /*pass data to the textview in fetchresultactivity activity*/


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
                FetchResultsActivity.tvShowJason.setText(s);
                jsonString = s;
                try {
                    loadIntoListView(s);
                    findShop(s);
                } catch (JSONException e) {
                    e.printStackTrace();
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
    private void loadIntoListView(String json) throws JSONException {       /*methods to put jason in to attays*/

        JSONArray jsonArray = new JSONArray(json);
        heroes = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes[i] = obj.getString("name");

            }

        findMatches();
        String notFound = "";
        String temp = ", ";
        for(int i = 0; i<notMatches.length; i++){
            notFound = notMatches[i] + temp;
            //Log.d(notFound, "***********");
        }


    }

    public void findShop(String json) throws JSONException{
        JSONArray jsonArray = new JSONArray(json);
        map = new HashMap<String,ArrayList<String>>();
        matchAL = new ArrayList<String>();
        int n = jsonArray.length();
        for (int i = 0; i < n; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String item = obj.getString("name");
                if(!matchAL.contains(item)){
                    matchAL.add(item);
                }
            Log.d(matchAL.get(i),"**********");
        }

        for(int i = 0; i<matchAL.size(); i++){
            ArrayList<String> shops = new ArrayList<String>();
            for (int j = 0; j<n; j++){
                JSONObject obj = jsonArray.getJSONObject(j);
                if(obj.getString("name")==matchAL.get(i)){
                    shops.add(obj.getString("age"));
                    Log.d(obj.getString("age"),"**********");
                }

            }
            map.put(matchAL.get(i), shops);

        }
        List<String> a = new ArrayList<String>(map.keySet());
        for (int i =0; i<a.size();i++){
            Log.d(a.get(i),"*+*+*+*+*+*+*+*");
        }
        //Log.d(map.,"*+*+*+*+*+*+*+*");
    }

    private int howManyShops(){
        return 0;
    }

    private int howManyMatches(){       /*checks how many items maches from database*/
        int amount = 0;
        for(int i = 0; i<ViewListActivity.listItems.length; i++){
            for(int j = 0; j<heroes.length; j++){
                if(ViewListActivity.listItems[i].equals(heroes[j])){
                    amount = amount + 1;
                }
            }
        }
        return amount;
    }

    private int howManyMismatches(){       /*checks how many items maches from database*/
        int amount = 0;
        for(int i = 0; i<ViewListActivity.listItems.length; i++){
            int tmp = 0;
            for(int j = 0; j<heroes.length; j++){
                tmp = j;
                if(ViewListActivity.listItems[i].equals(heroes[j])){

                    //break;

                }

            }
            if(tmp == heroes.length){
                amount = amount + 1;
            }
        }
        return amount;
    }

    private void findMatches(){     /*adds maches to a array*/
        matches = new String[howManyMatches()];
        notMatches = new String[howManyMismatches()];
        for(int i = 0; i<ViewListActivity.listItems.length; i++){
            int temp = 0;
            for(int j = 0; j<heroes.length; j++){
                temp = j;
                if(ViewListActivity.listItems[i].equals(heroes[j])){
                    matches[i]= heroes[j];
                    //break;
                }



            }
            if(temp == heroes.length){
                notMatches[i]= ViewListActivity.listItems[i].toString();
            }
        }

    }

    public String misMatches(){
        String notFound = "";
        String temp = ", ";
        for(int i = 0; i<notMatches.length; i++){
            notFound = notMatches[i] + temp;
        }
        return  notFound;
    }

    public boolean isExistds(String item){
        return true;
    }




}
