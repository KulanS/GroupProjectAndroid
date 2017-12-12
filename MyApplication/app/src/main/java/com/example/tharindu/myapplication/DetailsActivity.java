package com.example.tharindu.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {
    private ListView lvDetails;
    List<ShopClass> shopClassItems;
    DatabaseHelper mDatabaseHelper;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        init();
        populateFromTable();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void init(){

        lvDetails = (ListView)findViewById(R.id.lvDetails);
    }



    private void populateFromTable(){
        mDatabaseHelper = new DatabaseHelper(this);
        Cursor data = mDatabaseHelper.getItemShopData();
        //Cursor data = mDatabaseHelper.getItemShopDataInnerJoin();
        shopClassItems = new ArrayList<ShopClass>();

        while(data.moveToNext()) {

            String item = data.getString(1);
            String items = data.getString(5);
            address = data.getString(7);
            ShopClass itemss = new ShopClass(item, items);
            shopClassItems.add(itemss); //get data from coloum 1 and add to arraylist
        }
        Log.d(data.getColumnName(1),"***1***");
        Log.d(data.getColumnName(2),"****2**");
        Log.d(data.getColumnName(3),"*****3*");
        Log.d(data.getColumnName(4),"*****4*");
        Log.d(data.getColumnName(5),"*****5*");
        Log.d(data.getColumnName(6),"*****6*");
        Log.d(data.getColumnName(7),"*****7*");


        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_item, shopClassItems);
        lvDetails.setAdapter(adapter);
        //lvDetails.getOnItemClickListener();
        lvDetails.setOnItemClickListener(this);

        /*shopClassItems = new ArrayList<ShopClass>();
        for(int i = 0; i<GetShopClass.shopID.size(); i++){
            String item1 = GetShopClass.shopID.get(0);
            String item2 = GetShopClass.shopName.get(0);
            ShopClass shopClass = new ShopClass(item1, item2);
            shopClassItems.add(shopClass);
            CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                    R.layout.list_item, shopClassItems);
            lvDetails.setAdapter(adapter);*/
        }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "adress :" +address,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    }

