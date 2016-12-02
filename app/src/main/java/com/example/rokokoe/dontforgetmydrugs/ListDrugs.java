package com.example.rokokoe.dontforgetmydrugs;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListDrugs extends ListActivity {

    DTBMedicine dtbMedicine;
    List<Medicine> medicines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dtbMedicine = new DTBMedicine(this);
        medicines = dtbMedicine.getMedicines();
        ArrayList<String> as = new ArrayList<>();
        for (Medicine m: medicines) {
            as.add(m.getMedName() + " จำนวน: " +m.getTotal() + "เม็ด\nเวลาทาน: " + (m.getMoEat()!=0? m.getMo()+":30 "+m.getMoEat()+"เม็ด ":"")
                    + (m.getLuEat()!=0? m.getLu()+":30 "+m.getLuEat()+"เม็ด ":"") + (m.getDiEat()!=0? m.getDi()+":30 "+m.getDiEat()+"เม็ด ":"") + (m.getSlEat()!=0? m.getSl()+":30 "+m.getSlEat()+"เม็ด ":""));
        }
        if (as.isEmpty()){
            as.add("Empty");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,as);
        setListAdapter(adapter);
    }
}
