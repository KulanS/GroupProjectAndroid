package com.example.tharindu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FetchResultsActivity extends AppCompatActivity {
    public static TextView tvShowJason;
    private ListView lvJsonResults;
    private Button btnViewJson;
    private Button btnJsonDelete;
    private Button btnCheck;
    DatabaseHelper mDatabaseHelper;
    JsonHelper mJsonHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fetchresults_activity);

        init();
        clicViewJason();
        clickDelete();
        clickCheck();

    }

    private void clicViewJason(){
        btnViewJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadIntoListView(GetDataClass.matches);
                /*if(GetDataClass.notMatches.length!=0){
                    GetDataClass gdc = new GetDataClass();
                    toastMessage(gdc.misMatches());
                }*/
                addJsonToLocal(GetDataClass.itemForTable, GetDataClass.shopForTable);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void init(){
        tvShowJason = (TextView) findViewById(R.id.tvShowJason);
        lvJsonResults = (ListView)findViewById(R.id.lvJsonResults);
        btnViewJson = (Button)findViewById(R.id.btnViewJson);
        btnJsonDelete = (Button)findViewById(R.id.btnJsonDelete);
        btnCheck = (Button)findViewById(R.id.btnCheck);
        mDatabaseHelper = new DatabaseHelper(this);
        mJsonHelper = new JsonHelper(this);
    }

    private void loadIntoListView(String[] s){

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s);
        lvJsonResults.setAdapter(arrayAdapter);
    }

    private void clickDelete(){
        btnJsonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result1 = mDatabaseHelper.deleteJsonTable();
                boolean result2 = mDatabaseHelper.deleteShopTable();
                if(result1 | result2){
                    toastMessage("Json Deleted");
                }else{
                    toastMessage("Nothing to delete");
                }
            }
        });
    }

    private void clickCheck(){
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FetchResultsActivity.this, DetailsActivity.class);
                startActivity(i);
                addToDatabase(GetShopClass.shopID, GetShopClass.shopName, GetShopClass.phone, GetShopClass.address, GetShopClass.descreption);
            }
        });
    }

    private void addJsonToLocal(ArrayList<String> list1, ArrayList<String> list2){
        int insertData = 0;
        for(int i=0; i<list1.size(); i++){
            String value1 = list1.get(i);
            String value2 = list2.get(i);

            insertData += mDatabaseHelper.addDatatoJsonTable(value1, value2);
        }


    }

    private void addToDatabase(ArrayList<String> shopID, ArrayList<String> shopName, ArrayList<String> phone, ArrayList<String> address, ArrayList<String> description) {
        int insertData = 0;
        for (int i = 0; i < shopID.size(); i++) {
            String item1 = shopID.get(i);
            String item2 = shopName.get(i);
            String item3 = phone.get(i);
            String item4 = address.get(i);
            String item5 = description.get(i);
            insertData += mDatabaseHelper.addDatatoShopTable(item1, item2, item3, item4, item5);
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
