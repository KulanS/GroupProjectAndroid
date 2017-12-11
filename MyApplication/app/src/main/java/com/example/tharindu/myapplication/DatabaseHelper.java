package com.example.tharindu.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "list.db";
    private static final String TABLE_NAME_1 = "mylist";
    private static final String TABLE_NAME_2 = "jsonTable";
    private static final String TABLE_NAME_3 = "shop";
    private static final String COL1 = "ID";
    private static final String COL2 = "items";
    private static final String COL3 = "itemName";
    private static final String COL4 = "shopName";
    private static final String COL5 = "shopId";
    private static final String COL6 = "shopNames";
    private static final String COL7 = "phone";
    private static final String COL8 = "address";
    private static final String COL9 = "description";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable1 =  "CREATE TABLE " + TABLE_NAME_1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 +  " TEXT)";
        String createTable2 =  "CREATE TABLE " + TABLE_NAME_2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL3 +  " TEXT, " + COL4 +" TEXT)";
        String createTable3 =  "CREATE TABLE " + TABLE_NAME_3 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL5 +  " TEXT, " + COL6 +" TEXT, " + COL7 + " TEXT, " + COL8 + " TEXT, " + COL9 + " TEXT)";
        db.execSQL(createTable1);
        db.execSQL(createTable2);
        db.execSQL(createTable3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_3);
        onCreate(db);
    }

    public int addData(String item){        //This method adds data to the table created
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        System.out.println(item);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME_1);

        long result = db.insert(TABLE_NAME_1, null, contentValues);
        if (result == -1){      //Ensures wheather the data is inserted to the table correctly or not.
            return 0;
        }else{
            return 1;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_1;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean deleteTable(){       //Delete all the rows in the table
        String query = "DELETE FROM " + TABLE_NAME_1;
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

    public boolean deleteJsonTable(){       //Delete all the rows in the table
        String query = "DELETE FROM " + TABLE_NAME_2;
        //String isEmpty = "SELECT COUNT(*) FROM " + TABLE_NAME_1;
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor result = db.rawQuery(isEmpty, null);
        //result.moveToFirst();
        //int value = result.getInt(0);   //If value = 0 means table is empty.

        if(!isJsonTableEmpty()){
            db.execSQL(query);
            return true;
        }
        return false;
    }

    public boolean deleteShopTable(){       //Delete all the rows in the table
        String query = "DELETE FROM " + TABLE_NAME_3;
        //String isEmpty = "SELECT COUNT(*) FROM " + TABLE_NAME_1;
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor result = db.rawQuery(isEmpty, null);
        //result.moveToFirst();
        //int value = result.getInt(0);   //If value = 0 means table is empty.

        if(!isShopTableEmpty()){
            db.execSQL(query);
            return true;
        }
        return false;
    }

    public boolean isTableEmpty(){      //Check the table is empty or not.
        String isEmpty = "SELECT COUNT(*) FROM " + TABLE_NAME_1;
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

    public boolean isJsonTableEmpty(){      //Check the table is empty or not.
        String isEmpty = "SELECT COUNT(*) FROM " + TABLE_NAME_2;
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

    public boolean isShopTableEmpty(){      //Check the table is empty or not.
        String isEmpty = "SELECT COUNT(*) FROM " + TABLE_NAME_3;
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

    public Cursor getNotification(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " +COL9+ " FROM " + TABLE_NAME_3 + " WHERE " +COL5+ " = 1";
        //String query = "SELECT * FROM " + TABLE_NAME_3;
        Cursor data = db.rawQuery(query, null);
        return data;
    }



    public int addDatatoJsonTable(String item, String shop) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL3, item);
        contentValues.put(COL4, shop);
        //System.out.println(item);

        Log.d(TAG, "addData: Adding " + item +" & "+ shop + " to " + TABLE_NAME_2);

        long result = db.insert(TABLE_NAME_2, null, contentValues);
        if (result == -1){      //Ensures wheather the data is inserted to the table correctly or not.
            return 0;
        }else{
            return 1;
        }


    }       //This method adds data to the json table from jason array

    public int addDatatoShopTable(String shopID, String shopName, String phone, String address, String description) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL5, shopID);
        contentValues.put(COL6, shopName);
        contentValues.put(COL7, phone);
        contentValues.put(COL8, address);
        contentValues.put(COL9, description);

        //System.out.println(item);

        Log.d(TAG, "addData: Adding " + shopID +" & "+ shopName + " to " + TABLE_NAME_3);

        long result = db.insert(TABLE_NAME_3, null, contentValues);
        if (result == -1){      //Ensures wheather the data is inserted to the table correctly or not.
            Log.d("helllo", "from database helper");
            return 0;
        }else{
            return 1;
        }


    }       //This method adds data to the json table from jason array

    public Cursor getItemShopData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_2 + ", " + TABLE_NAME_3 + " WHERE " + TABLE_NAME_2 + "." + COL4 + " = " + TABLE_NAME_3 + "." + COL5 + " GROUP BY " + TABLE_NAME_3 + "." + COL6;
        //String query = "SELECT * FROM " + TABLE_NAME_3;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


}
