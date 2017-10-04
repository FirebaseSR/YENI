package com.example.user.mobil;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by user on 24.11.2016.
 */
public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    String poss;

    AdapterView<?> pa;
    View v;
    int p;
    long i;

  //  String poss;
    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        pa=parent;
        v=view;
        p=pos;
        i=id;
        //Ekle e=new Ekle();
//        e.tarih.setText(parent.getItemAtPosition(pos).toString());
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
       poss=parent.getItemAtPosition(pos).toString();
    }
    public String posss=poss;
int sd=5;
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
