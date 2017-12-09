package com.example.tharindu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CorrectionActivity extends AppCompatActivity {
    private Button btnCorrect;
    private ListView lvWrongList;
    private TextView tvWrongWord;
    private AutoCompleteTextView etCorrectWord;
    private static final String[] suggestions = new String[GetDataClass.matchAL.size()];

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correction_activity);



        init();
        clickSave();
        populateList(GetDataClass.matchAL);
        autoComplete();
    }

    private void init(){
        btnCorrect = (Button)findViewById(R.id.btnCorrect);
        lvWrongList = (ListView)findViewById(R.id.lvWrongList);
        tvWrongWord = (TextView)findViewById(R.id.tvWrongWord);
        etCorrectWord = (AutoCompleteTextView) findViewById(R.id.etCorrectWord);
    }

    private void populateList(final ArrayList<String> list){
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list);
        lvWrongList.setAdapter(adapter);

        lvWrongList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                etCorrectWord.setText(list.get(position));
                tvWrongWord.setText("Instead of "+list.get(position));

            }
        });
        adapter.notifyDataSetChanged();
    }

    private void autoComplete(){

        for(int i = 0; i<GetDataClass.matchAL.size(); i++){
            suggestions[i] = GetDataClass.matchAL.get(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, suggestions);
        etCorrectWord.setAdapter(adapter);
    }


    private void clickSave(){

    }
}
