package com.example.rokokoe.dontforgetmydrugs;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 17/11/2559.
 */

public class DTBUser extends SQLiteOpenHelper{

    private final String TAG = getClass().getSimpleName();

    private SQLiteDatabase sqLiteDatabase;

    public DTBUser(Context context) {
        super(context, User.DATABASE_NAME, null, User.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
                String CREATE_USER_TABLE = String.format("CREATE TABLE  %s" +
                "(%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER)"
                ,User.TABLE,User.MO,User.LU,User.DI,User.SL);

        Log.i(TAG, CREATE_USER_TABLE);
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + User.TABLE;

        db.execSQL(DROP_USER_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }

    public void addUser(User m){
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

//        values.put(User.NAME,m.getMedName());
        values.put(User.MO,m.getMo());
        values.put(User.LU,m.getLu());
        values.put(User.DI,m.getDi());
        values.put(User.SL,m.getSl());

        sqLiteDatabase.insert(User.TABLE,null,values);

        sqLiteDatabase.close();
    }

    public  User getUser(){
        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(User.TABLE,null,null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        if (cursor.getCount()==0){
            return null;
        }

        int m = cursor.getInt(0),
                l  = cursor.getInt(1),
                d  = cursor.getInt(2),
                s  = cursor.getInt(3);

        sqLiteDatabase.close();

        return new User(m,l,d,s);

    }

    public  void deleteUser(){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(User.TABLE,null,null);
        sqLiteDatabase.close();
    }
}
