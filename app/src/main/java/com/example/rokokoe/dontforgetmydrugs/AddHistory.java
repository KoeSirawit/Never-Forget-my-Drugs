package com.example.rokokoe.dontforgetmydrugs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AddHistory extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history);
        DTBHistory dtbHistory = new DTBHistory(this);
        ListView list = (ListView)findViewById(R.id.list_history);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dtbHistory.getHistory());
        list.setAdapter(adapter);
    }

}
