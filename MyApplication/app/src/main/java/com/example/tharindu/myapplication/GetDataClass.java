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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GetDataClass extends AsyncTask<Void, Void, Void> {       /*this class is used to perform jason operations*/
    String data;
    String jsonString;
    static String[] heroes;
    static String[] matches;
    static String[] notMatches;
    static ArrayList<String> itemKeys;
    static HashMap<String,ArrayList<String>> map;
    static ArrayList<String> shopKeys;
    static ArrayList<String> exactShop;
    static ArrayList<String> itemForTable;
    static ArrayList<String> shopForTable;
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
                //FetchResultsActivity.tvShowJason.setText(s);
                jsonString = s;
                try {
                    loadIntoListView(s);

                    findShop(s);
                    fillDb(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i<itemForTable.size(); i++){
                    Log.d(itemForTable.get(i), "***********");
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
            heroes[i] = obj.getString("item_name");

            }

        findMatches();
        String notFound = "";
        String temp = ", ";
        /*for(int i = 0; i<shopKeys.size(); i++){
            notFound = shopKeys.get(i);
            Log.d(notFound, "***********");
        }*/


    }

    public void findShop(String json) throws JSONException{
        JSONArray jsonArray = new JSONArray(json);
        map = new HashMap<String,ArrayList<String>>();
        itemKeys = new ArrayList<String>();
        shopKeys = new ArrayList<String>();
        int n = jsonArray.length();
        for (int i = 0; i < n; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String item = obj.getString("item_name");
                if(!itemKeys.contains(item)){
                    itemKeys.add(item);  //getting item key
                }
            //Log.d(itemKeys.get(i),"**********");
        }

        for(int i = 0; i< itemKeys.size(); i++){
            ArrayList<String> shops = new ArrayList<String>();
            for (int j = 0; j<n; j++){
                JSONObject obj = jsonArray.getJSONObject(j);
                if(obj.getString("item_name")== itemKeys.get(i)){
                    shops.add(obj.getString("shop_shop_id"));   //getting arraylist
                    if(!shopKeys.contains(obj.getString("shop_shop_id"))){
                        shopKeys.add(obj.getString("shop_shop_id"));  //getting shop key
                    }
                    //Log.d(shopKeys.get(i),"**********");
                }


            }
            map.put(itemKeys.get(i), shops);     //adding array list for each key

        }
        /*List<String> a = new ArrayList<String>(map.keySet());
        for (int i =0; i<a.size();i++){
            //Log.d(a.get(i),"*+*+*+*+*+*+*+*");
        }*/
        //Log.d(map.,"*+*+*+*+*+*+*+*");
    }

    private void commonShops(){
        exactShop = new ArrayList<String>();
        for(String shop: shopKeys){
            if(isExists(shop)){
                exactShop.add(shop);
            }
        }
    }

    private boolean isExists(String s){    /*check each arraylist in hashmap wheather shop exists*/
        Iterator iter = (Iterator) map.keySet().iterator();

        while(iter.hasNext()) {

            Map.Entry entry = (Map.Entry) iter.next();
            ArrayList<String> result = (ArrayList<String>) entry.getValue();
            if(!result.contains(s)){
                return false;
            }
        }
        return true;
    }

    private void fillDb(String json)throws JSONException{   //this methods add json data to sqlite databse without repitions

        itemForTable = new ArrayList<String>();
        shopForTable = new ArrayList<String>();
        JSONArray jsonArray = new JSONArray(json);
        int n = jsonArray.length();
        int m = itemForTable.size();
        for(int i = 0; i<n; i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            String itemName = obj.getString("item_name");
            String shopName = obj.getString("shop_shop_id");
            itemForTable.add(itemName);
            shopForTable.add(shopName);
            if(m!=0){
                for(int j = 0; j<=m; j++) {
                    if (itemName == itemForTable.get(j) & shopName == shopForTable.get(j)) {
                    itemForTable.remove(m);
                    shopForTable.remove(m);
                    }
                }
            }
        }
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
