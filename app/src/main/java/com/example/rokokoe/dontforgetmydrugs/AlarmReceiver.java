package com.example.rokokoe.dontforgetmydrugs;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by RoKoKoe on 11/29/2016.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {
        Intent i = new Intent(context, ShowEvent.class);
        i.putExtra(Medicine.NAME, arg1.getExtras().getString(Medicine.NAME));
        i.putExtra("Eat",arg1.getExtras().getInt("Eat"));
        i.putExtra("Time",arg1.getExtras().getString("Time"));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

}
