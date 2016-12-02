package com.example.rokokoe.dontforgetmydrugs;

import java.util.Calendar;

/**
 * Created by User on 17/11/2559.
 */

public class Medicine {
    public static final String DATABASE_NAME = "databaseMedicineSE.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE = "Medicine";

    private String medName;
    private int total;
    private int mo,lu,di,sl;
    private int moEat,luEat,diEat,slEat;

    public static final String NAME="Name";
    public static final String TOTAL="Total";
    public static final String MO="Morning";
    public static final String LU="Lunch";
    public static final String DI="Dinner";
    public static final String SL="Sleep";
    public static final String MO_EAT="MorningEatAmount";
    public static final String LU_EAT="LunchEatAmount";
    public static final String DI_EAT="DinnerEatAmount";
    public static final String SL_EAT="SleepEatAmount";

    public Medicine(){}

    public Medicine(String medName,int total,int mo, int lu,int di,int sl,int moEat,int luEat,int diEat,int slEat){
        this.medName = medName;
        this.total = total;
        this.mo = mo;
        this.lu = lu;
        this.di = di;
        this.sl = sl;
        this.moEat = moEat;
        this.luEat = luEat;
        this.diEat = diEat;
        this.slEat = slEat;
    }

    public String getMedName() {
        return medName;
    }

    public int getTotal() { return total; }

    public int getMo() { return mo; }

    public int getLu() { return lu; }

    public int getDi() { return di; }

    public int getSl() { return sl; }

    public int getMoEat() { return moEat; }

    public int getLuEat() { return luEat; }

    public int getDiEat() { return diEat; }

    public int getSlEat() { return slEat; }
}
