package com.example.rokokoe.dontforgetmydrugs;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ListMunu extends AppCompatActivity {
    private Button info,listBtn,addListBtn,historyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        info = (Button)findViewById(R.id.infoBtn) ;
        listBtn = (Button)findViewById(R.id.listBtn);
        addListBtn = (Button)findViewById(R.id.addListBtn);
        historyBtn = (Button)findViewById(R.id.historyBtn);

        if((new DTBUser(this)).getUser()==null){
            listBtn.setEnabled(false);
            addListBtn.setEnabled(false);
            historyBtn.setEnabled(false);
        }

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMunu.this,AddDataUser.class);
                startActivity(intent);
                finish();
                listBtn.setEnabled(true);
                addListBtn.setEnabled(true);
                historyBtn.setEnabled(true);
            }
        });
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMunu.this,ListDrugs.class);
                startActivity(intent);
            }
        });
        addListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMunu.this,AddList.class);
                startActivity(intent);
                finish();
            }
        });
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMunu.this,AddHistory.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
