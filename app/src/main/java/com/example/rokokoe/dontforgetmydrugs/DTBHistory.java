package com.example.rokokoe.dontforgetmydrugs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by RoKoKoe on 11/29/2016.
 */

public class DTBHistory extends SQLiteOpenHelper {
    private final String TAG = getClass().getSimpleName();
    public class Columm{
        public static final String TABLE = "MedicineHistoey";
        public static final String DATABASE_NAME = "databaseHistoeySE.db";
        public static final int DATABASE_VERSION = 1;
        public static final String HISTORY = "Histoey";
    }

    private SQLiteDatabase sqLiteDatabase;

    public DTBHistory(Context context) {
        super(context, Columm.DATABASE_NAME, null, Columm.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = String.format("CREATE TABLE  %s" +
                        "(%s String)"
                ,Columm.TABLE,Columm.HISTORY);

        Log.i(TAG, CREATE_USER_TABLE);
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + Columm.TABLE;

        db.execSQL(DROP_USER_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }

    public void addHistory(String s){
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Columm.HISTORY,s);

        sqLiteDatabase.insert(Columm.TABLE,null,values);

        sqLiteDatabase.close();
    }

    public ArrayList<String> getHistory(){
        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Columm.TABLE,null,null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        ArrayList<String> strings = new ArrayList<>();
        while(!cursor.isAfterLast()){
            strings.add(cursor.getString(0));
            cursor.moveToNext();
        }


        sqLiteDatabase.close();

        return strings;

    }

    public  void deleteHistory(){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Columm.TABLE,null,null);
        sqLiteDatabase.close();
    }
}
