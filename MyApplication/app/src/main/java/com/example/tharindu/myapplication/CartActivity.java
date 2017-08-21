package com.example.tharindu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CartActivity extends AppCompatActivity {
    private Button btnCreateNewList, btnViewLists, btnEditList, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        init();
        clickCreateNewList();
        clickViewLists();
        clickEditList();
        clickBack();

    }


    private void init(){
        btnCreateNewList = (Button)findViewById(R.id.btnCreateNewList);
        btnViewLists = (Button)findViewById(R.id.btnViewLists);
        btnEditList = (Button)findViewById(R.id.btnEditList);
        btnBack = (Button)findViewById(R.id.btnBack);
    }

    private void clickCreateNewList(){
        btnCreateNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CartActivity.this, CreateNewListActivity.class);
                startActivity(i);
            }
        });
    }

    private void clickViewLists(){

    }

    private void clickEditList(){

    }

    private void clickBack(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CartActivity.this, MainMenuActivity.class);
                startActivity(i);
            }
        });
    }
}
