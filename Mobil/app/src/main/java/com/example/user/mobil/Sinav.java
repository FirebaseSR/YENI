package com.example.user.mobil;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;

public class Sinav extends AppCompatActivity {

    DatabaseHelper dbHelper;
    EditText ad,icerik,kapsam;
    TextView text15,text14;
    Spinner sure;
    WebView v;
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinav);

        ad=(EditText)findViewById(R.id.editText9);
        icerik=(EditText)findViewById(R.id.editText5);
        kapsam=(EditText)findViewById(R.id.editText7);

        sure=(Spinner)findViewById(R.id.spinner3);
        adapter= ArrayAdapter.createFromResource(this, R.array.sure, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sure.setAdapter(adapter);
        text15=(TextView)findViewById(R.id.textView15);
        text14=(TextView)findViewById(R.id.textView14);

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

    public boolean geri(View v)
    {
        this.finish();
        return true;
    }
    public void tamam(View v)
    {
        Bundle extras= getIntent().getExtras();
        String db=extras.getString("db");
        String s=extras.getString("s");
        String yer=extras.getString("yer");
        String t=extras.getString("t");

        int a=5;

        String select_Data[] = null;
        SQLiteDatabase db1;
        db1 = dbHelper.getWritableDatabase();

        try {
            dbHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db1.isOpen();

        String suree="";
        if (sure.equals("5 dk"))
        {
            suree="5";
        }
        else if (sure.equals("15 dk"))
        {
            suree="15";
        }
        else if (sure.equals("30 dk"))
        {
            suree="30";
        }
        else if (sure.equals("1 Saat"))
        {
            suree="60";
        }
        else if (sure.equals("2 Saat"))
        {
            suree="120";
        }
        else if (sure.equals("5 Saat"))
        {
            suree="300";
        }
        else if (sure.equals("Her gün"))
        {
            suree="1440";
        }
        else
            suree="5";

        ContentValues cv=new ContentValues();
        // String ss=getIntent().getExtras().getString("id");
        //  cv.put("tetikleyici", secilen);
        ContentValues cv1=new ContentValues();
        cv1.put("adi",ad.getText().toString());
        cv1.put("turu", db);
        cv1.put("tarih",t);
        cv1.put("saat", s);
        cv1.put("hsure",suree);
        cv1.put("icerik",icerik.getText().toString());

        // cv1.put("siddet",seek.getText().toString());
        // db1.update("LOG", cv, "bassaat" + "=?", new String[]{"17:00"});
        db1.insert("Tum", null, cv1);
        cv1.put("kapsam", kapsam.getText().toString());
        cv1.remove("turu");
        cv1.put("yer",yer);
        db1.insert("Sinav", null, cv1);

        Toast.makeText(this, "Veritabanına Hatırlatıcınız Eklendi!", Toast.LENGTH_SHORT).show();
        dbHelper.close();
        db1.close();
        Intent intent=new Intent(Sinav.this,AnaSayfa.class);
        startActivity(intent);

    }
}
