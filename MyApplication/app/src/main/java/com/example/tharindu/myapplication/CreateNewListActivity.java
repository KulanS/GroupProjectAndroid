package com.example.tharindu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateNewListActivity extends AppCompatActivity {
    EditText etItemName;
    Button btnAdd, btnDelete, btnUpdate, btnClear, btnSave;
    ListView lvList;

    private static final String TAG = "CreateNewListActivity"; //For database operations
    DatabaseHelper mDatabaseHelper; //For database operations

    ArrayList<String> items = new ArrayList<String>();
    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createnewlist_activity);

        init();
        //Creates new array adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, items);
        lvList.setAdapter(adapter);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                etItemName.setText(items.get(position));

            }
        });
        mDatabaseHelper = new DatabaseHelper(this);
        clickAdd();
        clickUpdate();
        clickDelete();
        clickClear();
        clickSave();

    }

    private void init(){
        etItemName = (EditText)findViewById(R.id.etItemName);
        btnAdd = (Button)findViewById(R.id.btnAdds);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnClear = (Button)findViewById(R.id.btnClear);
        btnSave = (Button)findViewById(R.id.btnSave);
        lvList = (ListView)findViewById(R.id.lvList);
        //mDatabaseHelper = new DatabaseHelper(this);

    }

    private void add(){
        String itemName = etItemName.getText().toString();
        if(!itemName.isEmpty() && itemName.length()>0){
            adapter.add(itemName);
            adapter.notifyDataSetChanged();
            etItemName.setText("");
            toastMessage("Added " + itemName);
        }else{
            toastMessage("Nothing to add");

        }
    }

    private void update(){
        String itemName = etItemName.getText().toString();
        int pos = lvList.getCheckedItemPosition();

        if(!itemName.isEmpty() && itemName.length()>0){
            adapter.remove(items.get(pos));
            adapter.insert(itemName, pos);
            adapter.notifyDataSetChanged();
            toastMessage("Updated " + itemName);
        }else{
            toastMessage("Nothing to update");

        }
    }

    private void delete(){

        int pos = lvList.getCheckedItemPosition();
        if(pos > -1){
            adapter.remove(items.get(pos));
            adapter.notifyDataSetChanged();
            etItemName.setText("");
            toastMessage("Deleted");
        }else{
            toastMessage("Nothing to delete");
        }
    }

    private void clear(){
        adapter.clear();
        etItemName.setText("");
    }

    private void saveToDatabase(ArrayAdapter<String> list){
        int insertData = 0;
        for(int i=0; i<list.getCount(); i++){
            String value = list.getItem(i);
            insertData += mDatabaseHelper.addData(value);
        }

        if(insertData == list.getCount()){
            if(insertData != 0){

                toastMessage("List Saved Sucessfully!");
            }else{
                toastMessage("No List To Save!");
            }

        }
        adapter.clear();
    }



    private void clickAdd(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
    }

    private void clickUpdate(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }

    private void clickDelete(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

    private void clickClear(){
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
    }

    private void clickSave(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { saveToDatabase(adapter);}
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
