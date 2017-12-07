package com.example.tharindu.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewListActivity extends AppCompatActivity {
    private static final String TAG = "ViewListActivity";
    private Button btnDeleteList;
    private Button btnFetch;
    DatabaseHelper mDatabaseHeper;
    ListView lvShowList;
    static ArrayList<String> listData;
    static String[] listItems;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewlist_activity);

        init();
        populateList();
        clickDelete();
        clickFetch();

    }

    private void init(){
        lvShowList = (ListView)findViewById(R.id.lvShowList);
        mDatabaseHeper = new DatabaseHelper(this);
        btnDeleteList = (Button)findViewById(R.id.btnDeleteList);
        btnFetch = (Button)findViewById(R.id.btnFetch);
    }

    private void populateList(){
        Log.d(TAG, "populateList: Displaying the data in ListView.");
        Cursor data = mDatabaseHeper.getData(); //get the data and append to a list
        ArrayList<String> listData = new ArrayList<String>();
        while(data.moveToNext()) {
            listData.add(data.getString(1)); //get data from coloum 1 and add to arraylist

        }
        listItems = new String[listData.size()];
        for(int i = 0; i<listItems.length; i++){
            listItems[i] = listData.get(i);

        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData); //create the list adapter and set the adapter
        lvShowList.setAdapter(adapter);
    }

    public String[] getList(){
        Log.d(TAG, "populateList: Displaying the data in ListView.");
        Cursor data = mDatabaseHeper.getData(); //get the data and append to a list
        ArrayList<String> listData = new ArrayList<String>();
        while(data.moveToNext()){
            listData.add(data.getString(1)); //get data from coloum 1 and add to arraylist

        }
        String[] itemList = new String[listData.size()];
        for(int i = 0; i<listData.size(); i++){
            itemList[i] = listData.get(i);
        }

        return itemList;
    }

    private void fetchData(){       /*get the data from the database as a jason*/
        GetDataClass process = new GetDataClass();
        if(CheckNetworkClass.isInternetAvailable(ViewListActivity.this)){
            //process.execute();    //optional method
            process.getJSON("https://api.myjson.com/bins/19mp3b");
        }else{
            toastMessage("No Internet Connection");
        }
    }

    private void clickDelete(){
        btnDeleteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = mDatabaseHeper.deleteTable();
                if(result){
                    toastMessage("List Deletded Successfully!");
                    Intent i = new Intent(ViewListActivity.this, CartActivity.class);
                    startActivity(i);
                }else{
                    toastMessage("Nothing to delete");
                }
            }
        });
    }

    private void clickFetch(){
        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewListActivity.this, FetchResultsActivity.class);
                startActivity(i);
                fetchData();
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
