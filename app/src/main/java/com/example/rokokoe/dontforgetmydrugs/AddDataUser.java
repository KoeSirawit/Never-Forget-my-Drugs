package com.example.rokokoe.dontforgetmydrugs;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddDataUser extends AppCompatActivity {

    private EditText mo,lu,di,sl;
    private Button btn;
    private DTBUser dtbUser;
    private DTBMedicine dtbMedicine;
    private DTBHistory dtbHistory;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_user);

        init();

        dtbUser = new DTBUser(this );
        dtbMedicine = new DTBMedicine(this);
        dtbHistory = new DTBHistory(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddDataUser.this);
                builder.setTitle("Are you sure?");
                builder.setMessage("All Infomation has to deleted!!!");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int m=0,l=0,d=0,s=0;
                        try {
                            m = Integer.valueOf(mo.getText().toString());
                            l = Integer.valueOf(lu.getText().toString());
                            d = Integer.valueOf(di.getText().toString());
                            s = Integer.valueOf(sl.getText().toString());
                        }catch (Exception e){
                            textView.setText("กรุณาใส่ข้อมูลเป็นตัวเลข");
                            return;
                        }
                        User u = new User(m,l,d,s);
                        dtbUser.addUser(u);

                        dtbMedicine.deleteMedicineAll();
                        dtbUser.deleteUser();
                        dtbHistory.deleteHistory();

                        dtbUser.addUser(u);
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
        mo = (EditText)findViewById(R.id.editText10);
        lu = (EditText)findViewById(R.id.editText3);
        di = (EditText)findViewById(R.id.editText5);
        sl = (EditText)findViewById(R.id.editText6);

        btn = (Button)findViewById(R.id.button2);
        textView = (TextView)findViewById(R.id.textView19);
    }
}
