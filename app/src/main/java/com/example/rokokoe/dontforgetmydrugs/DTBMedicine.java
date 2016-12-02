package com.example.rokokoe.dontforgetmydrugs;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by User on 17/11/2559.
 */

public class DTBMedicine extends SQLiteOpenHelper{

    private final String TAG = getClass().getSimpleName();

    private SQLiteDatabase sqLiteDatabase;

    public DTBMedicine(Context context) {
        super(context, Medicine.DATABASE_NAME, null, Medicine.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = String.format("CREATE TABLE  %s" +
                "(%s TEXT, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER)"
                ,Medicine.TABLE,Medicine.NAME,Medicine.TOTAL,Medicine.MO,Medicine.LU,Medicine.DI,Medicine.SL,Medicine.MO_EAT,Medicine.LU_EAT,Medicine.DI_EAT,Medicine.SL_EAT);

        Log.i(TAG, CREATE_USER_TABLE);
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + Medicine.TABLE;

        db.execSQL(DROP_USER_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }

    public void addMedicine(Medicine m){
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Medicine.NAME,m.getMedName());
        values.put(Medicine.TOTAL,m.getTotal());
        values.put(Medicine.MO,m.getMo());
        values.put(Medicine.LU,m.getLu());
        values.put(Medicine.DI,m.getDi());
        values.put(Medicine.SL,m.getSl());
        values.put(Medicine.MO_EAT,m.getMoEat());
        values.put(Medicine.LU_EAT,m.getLuEat());
        values.put(Medicine.DI_EAT,m.getDiEat());
        values.put(Medicine.SL_EAT,m.getSlEat());

        sqLiteDatabase.insert(Medicine.TABLE,null,values);

        sqLiteDatabase.close();
    }

    public ArrayList<Medicine> getMedicines(){
        ArrayList<Medicine> am = new ArrayList<>();

        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(Medicine.TABLE,null,null,null,null,null,null);
        if (cursor!=null){
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()){
            String medName = cursor.getString(0);
            int total = cursor.getInt(1);
            int mo = cursor.getInt(2),
                    lu  = cursor.getInt(3),
                    di  = cursor.getInt(4),
                    sl  = cursor.getInt(5);
            int moEat = cursor.getInt(6),
                    luEat = cursor.getInt(7),
                    diEat = cursor.getInt(8),
                    slEat = cursor.getInt(9);
            am.add(new Medicine(medName,total,mo,lu,di,sl,moEat,luEat,diEat,slEat));
            cursor.moveToNext();
        }

        sqLiteDatabase.close();
        return am;
    }

    public  void deleteMedicine(String name){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Medicine.TABLE,Medicine.NAME + " =?",new String[]{name});
        sqLiteDatabase.close();
    }

    public  void deleteMedicineAll(){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Medicine.TABLE,null,null);
        sqLiteDatabase.close();
    }

    public Medicine find(String name){
        ArrayList<Medicine> am = getMedicines();
        for (Medicine m: am ) {
            if(m.getMedName().equalsIgnoreCase(name))
                return m;
        }
        return null;
    }

    public void eatMedicine(String name,int eat){
        Medicine m = find(name);
        if (m != null){
            if(m.getTotal()- eat <= 0){
                deleteMedicine(name);
                return;
            }
            sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Medicine.NAME,m.getMedName());
            values.put(Medicine.TOTAL,m.getTotal()-eat);
            values.put(Medicine.MO,m.getMo());
            values.put(Medicine.LU,m.getLu());
            values.put(Medicine.DI,m.getDi());
            values.put(Medicine.SL,m.getSl());
            values.put(Medicine.MO_EAT,m.getMoEat());
            values.put(Medicine.LU_EAT,m.getLuEat());
            values.put(Medicine.DI_EAT,m.getDiEat());
            values.put(Medicine.SL_EAT,m.getSlEat());

            sqLiteDatabase.update(Medicine.TABLE,values,Medicine.NAME+"=?",new String[]{name});
            sqLiteDatabase.close();
        }
    }
}
