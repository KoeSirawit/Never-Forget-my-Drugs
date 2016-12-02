package com.example.rokokoe.dontforgetmydrugs;

import android.media.MediaCodecInfo;

import java.util.ArrayList;

/**
 * Created by User on 17/11/2559.
 */

public class User {

    public static final String DATABASE_NAME = "databaseUserSE.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE = "User";

//    private String name;
    private int mo,lu,di,sl;
//    public static final String NAME="Name";
    public static final String MO="Morning";
    public static final String LU="Lunch";
    public static final String DI="Dinner";
    public static final String SL="Sleep";

    public User(){

    }

    public User(int mo, int lu,int di,int sl){
//        this.name = name;
        this.mo = mo;
        this.lu = lu;
        this.di = di;
        this.sl = sl;
    }

//    public String getMedName() { return name; }

    public int getMo() { return mo; }

    public int getLu() { return lu; }

    public int getDi() { return di; }

    public int getSl() { return sl; }

}
