package com.example.user.mobil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.io.IOException;


public class AnaSayfa extends AppCompatActivity {

    Button hekle,heg,yaklasan,tum;
    DatabaseHelper dbHelper;
    CheckBox bildirim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        /*Intent intent = new Intent(this, MyService.class);
        startService(intent);*/

        hekle=(Button)findViewById(R.id.hekle);
        heg=(Button)findViewById(R.id.hgecmis);
        yaklasan=(Button)findViewById(R.id.yetkinlik);
        tum=(Button)findViewById(R.id.thatirlat);
        bildirim=(CheckBox)findViewById(R.id.checkBox);
    /*   if( bildirim.isChecked())
       {
           aktif();
       }*/



        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    /*public void a(View v)
    {
        Intent intent=new Intent(AnaSayfa.this,Ara_mesaj.class);
        startActivity(intent);
    }*/
    public void ekle(View v)
    {
        Intent intent=new Intent(AnaSayfa.this,Ekle.class);
        startActivity(intent);

    }
    public void tum(View v)
    {
        Intent intent=new Intent(AnaSayfa.this,Tum.class);
        startActivity(intent);
    }
    public void aktif(View v)
    {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }
}
