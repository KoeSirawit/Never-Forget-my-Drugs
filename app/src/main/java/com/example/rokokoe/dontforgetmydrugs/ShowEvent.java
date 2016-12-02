package com.example.rokokoe.dontforgetmydrugs;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RoKoKoe on 11/29/2016.
 */

@SuppressWarnings("ALL")
public class ShowEvent extends Activity{
    private PowerManager pm;
    private PowerManager.WakeLock wl;
    private KeyguardManager km;
    private KeyguardManager.KeyguardLock kl;
    private Ringtone r;

    private String name;
    private Button btnStop;
    private int eat;
    private TextView title,message;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        km=(KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE);
        kl=km.newKeyguardLock("ShowEvent");
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP|PowerManager.ON_AFTER_RELEASE, "ShowEvent");
        wl.acquire();
        kl.disableKeyguard();

        name = getIntent().getExtras().getString(Medicine.NAME);
        eat = getIntent().getExtras().getInt("Eat");

        setContentView(R.layout.sec);
        final DTBMedicine dtbMedicine = new DTBMedicine(this);
        final DTBHistory dtbHistory = new DTBHistory(this);
        btnStop = (Button)findViewById(R.id.btnStop);
        title = (TextView)findViewById(R.id.textView1) ;
        message = (TextView)findViewById(R.id.textView2) ;
        title.setText("ถึงเวลาทานยา");
        message.setText("กรุณาทานยา"+name+"  จำนวน: "+eat+" เม็ด");
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dtbMedicine.eatMedicine(name,eat);
                dtbHistory.addHistory(name+" : "+eat+"เม็ด เวลา: "+getIntent().getExtras().getString("Time"));
                finish();
            }
        });

    }

    @Override
    protected void onResume() {

        super.onResume();
        wl.acquire();
        Uri notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        if(notif==null){
            notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            if(notif==null){
                notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
        }
        r = RingtoneManager.getRingtone(getApplicationContext(), notif);
        r.play();


    }

    @Override
    public void onPause(){
        super.onPause();
        wl.release();
        if(r.isPlaying()){
            r.stop();
        }
    }
}
