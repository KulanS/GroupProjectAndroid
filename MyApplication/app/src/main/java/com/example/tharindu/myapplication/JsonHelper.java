package com.example.tharindu.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JsonHelper extends SQLiteOpenHelper{
    private static final String TAG = "JsonHelper";

    private static final String DATABASE_NAME = "itemandlist.db";
    private static final String TABLE_NAME = "itemandlist";
    private static final String COL1 = "ID";
    private static final String COL2 = "itemName";
    private static final String COL3 = "shopName";

    public JsonHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable =  "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 +  " TEXT, " + COL3 +" TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean deleteTable(){       //Delete all the rows in the table
        String query = "DELETE FROM " + TABLE_NAME;
        //String isEmpty = "SELECT COUNT(*) FROM " + TABLE_NAME_1;
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor result = db.rawQuery(isEmpty, null);
        //result.moveToFirst();
        //int value = result.getInt(0);   //If value = 0 means table is empty.

        if(!isTableEmpty()){
            db.execSQL(query);
            return true;
        }
        return false;
    }

    public boolean isTableEmpty(){      //Check the table is empty or not.
        String isEmpty = "SELECT COUNT(*) FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery(isEmpty, null);
        result.moveToFirst();
        int value = result.getInt(0);
        if(value == 0){
            return true;
        }else{
            return false;
        }
    }


}
