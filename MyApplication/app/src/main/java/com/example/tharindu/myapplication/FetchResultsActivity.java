package com.example.tharindu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FetchResultsActivity extends AppCompatActivity {
    public static TextView tvShowJason;
    private ListView lvJsonResults;
    private Button btnViewJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fetchresults_activity);


        init();
        clicViewJason();

    }

    private void clicViewJason(){
        btnViewJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadIntoListView(GetDataClass.matches);
                if(GetDataClass.notMatches.length!=0){
                    GetDataClass gdc = new GetDataClass();
                    toastMessage(gdc.misMatches());
                }

            }
        });
    }

    private void init(){
        tvShowJason = (TextView) findViewById(R.id.tvShowJason);
        lvJsonResults = (ListView)findViewById(R.id.lvJsonResults);
        btnViewJson = (Button)findViewById(R.id.btnViewJson);
    }

    private void loadIntoListView(String[] s){

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s);
        lvJsonResults.setAdapter(arrayAdapter);
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
