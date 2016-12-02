package com.example.rokokoe.dontforgetmydrugs;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class AddList extends AppCompatActivity {

    private RadioButton mo1,mo2,lu1,lu2,di1,di2,sl;
    private EditText name,total,mo,lu,di,slTxt;
    private Button btn;
    private DTBMedicine dtbMedicine;
    private DTBUser dtbUser;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        dtbMedicine = new DTBMedicine(this);
        dtbUser = new DTBUser(this);
        mo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mo1.setChecked(true);
                mo2.setChecked(false);
                mo.setEnabled(true);
            }
        });
        mo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mo2.setChecked(true);
                mo1.setChecked(false);
                mo.setEnabled(true);
            }
        });
        lu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lu1.setChecked(true);
                lu2.setChecked(false);
                lu.setEnabled(true);
            }
        });
        lu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lu2.setChecked(true);
                lu1.setChecked(false);
                lu.setEnabled(true);
            }
        });
        di1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                di1.setChecked(true);
                di2.setChecked(false);
                di.setEnabled(true);
            }
        });
        di2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                di2.setChecked(true);
                di1.setChecked(false);
                di.setEnabled(true);
            }
        });
        sl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sl.setChecked(true);
                slTxt.setEnabled(true);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddList.this);
                builder.setTitle("Add this medicine?");
                builder.setMessage("Are you sure to add this medicine?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User user = dtbUser.getUser();
                        String medName = name.getText().toString();
                        if (dtbMedicine.find(medName)!=null||medName.equals("test")){
                            textView.setText("ยานี้มีอยู่ในรายการยาแล้ว");
                            return;
                        }
                        int t = 0, m = 0, l = 0, d = 0, s = 0;
                        int userMo = user.getMo(), userLu = user.getLu(), userDi = user.getDi(), userSl = user.getSl() - 1;
                        try {
                            t = Integer.valueOf(total.getText().toString());

                            if (mo.isEnabled() && user.getMo() != -1) {
                                m = Integer.valueOf(mo.getText().toString());
                                if (mo1.isChecked()) {
                                    userMo--;
                                }
                            }
                            if (lu.isEnabled() && user.getMo() != -1) {
                                l = Integer.valueOf(lu.getText().toString());
                                if (lu1.isChecked()) {
                                    userLu--;
                                }
                            }
                            if (di.isEnabled() && user.getMo() != -1) {
                                d = Integer.valueOf(di.getText().toString());
                                if (di1.isChecked()) {
                                    userDi--;
                                }
                            }
                            if (slTxt.isEnabled() && user.getMo() != -1) {
                                s = Integer.valueOf(slTxt.getText().toString());
                            }
                        }catch(Exception e){
                            textView.setText("กรุณาใส่ข้อมูลจำนวนด้วยตัวเลข");
                            return;
                        }
                        if(!(mo.isEnabled()||lu.isEnabled()||di.isEnabled()||slTxt.isEnabled())){
                            textView.setText("กรุณาระบุเวลาทานยา");
                            return;
                        }
                        Medicine medicine = new Medicine(medName,t,userMo,userLu,userDi,userSl,m,l,d,s);
                        addMedicineToAlarm(medicine);
                        dtbMedicine.addMedicine(medicine);
                        textView.setText("");
                        finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    private void init(){
        mo1 = (RadioButton)findViewById(R.id.radioButton2);
        mo2 = (RadioButton)findViewById(R.id.radioButton4);
        lu1 = (RadioButton)findViewById(R.id.radioButton6);
        lu2 = (RadioButton)findViewById(R.id.radioButton8);
        di1 = (RadioButton)findViewById(R.id.radioButton5);
        di2 = (RadioButton)findViewById(R.id.radioButton7);
        sl = (RadioButton)findViewById(R.id.radioButton9);

        name = (EditText)findViewById(R.id.editText2);
        total = (EditText)findViewById(R.id.editText4);
        mo = (EditText)findViewById(R.id.editText7);
        lu = (EditText)findViewById(R.id.editText8);
        di = (EditText)findViewById(R.id.editText9);
        slTxt = (EditText)findViewById(R.id.editText15);
        mo.setEnabled(false);
        lu.setEnabled(false);
        di.setEnabled(false);
        slTxt.setEnabled(false);

        btn = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textError);
    }

    private void addMedicineToAlarm(Medicine medicine){
        if(medicine.getMoEat()!=0){
            int hour = medicine.getMo();
            int minute = 30;
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            setAlarm(cal,medicine.getMedName(),medicine.getMoEat(),""+hour+":30");
        }
        if(medicine.getLuEat()!=0){
            int hour = medicine.getLu();
            int minute = 30;
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            setAlarm(cal,medicine.getMedName(),medicine.getLuEat(),""+hour+":30");
        }
        if(medicine.getDiEat()!=0){
            int hour = medicine.getDi();
            int minute = 30;
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            setAlarm(cal,medicine.getMedName(),medicine.getDiEat(),""+hour+":30");
        }
        if(medicine.getSlEat()!=0){
//            int hour = medicine.getSl();
            int hour = 16;
            int minute = 45;
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            setAlarm(cal,medicine.getMedName(),medicine.getSlEat(),""+hour+":30");
        }
    }

    private void setAlarm(Calendar targetCal,String name,int eat,String time){
        targetCal.set(Calendar.SECOND,0);
        Calendar calendar = Calendar.getInstance();
        if(calendar.getTime().equals(targetCal.getTime())){
            targetCal.set(Calendar.DATE,targetCal.get(Calendar.DATE)+1);
        }
        final int _id = (int) System.currentTimeMillis();
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        intent.putExtra(Medicine.NAME,name);
        intent.putExtra("Eat",eat);
        intent.putExtra("Time",time);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), _id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }
}
