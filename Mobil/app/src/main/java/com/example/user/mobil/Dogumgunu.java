package com.example.user.mobil;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dogumgunu extends AppCompatActivity {

    EditText adi,num,msj;
    RadioButton ara,mesaj;
    Spinner sure;
    ArrayAdapter<CharSequence> adapter;
    DatabaseHelper dbHelper;
    String islem="";
    int sayisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogumgunu);

        adi=(EditText)findViewById(R.id.editText4);
        num=(EditText)findViewById(R.id.editText5);
        msj=(EditText)findViewById(R.id.editText9);
        mesaj=(RadioButton)findViewById(R.id.radioButton3);
        ara=(RadioButton)findViewById(R.id.radioButton4);
        sure=(Spinner)findViewById(R.id.spinner3);
        adapter= ArrayAdapter.createFromResource(this, R.array.sure, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sure.setAdapter(adapter);
        //boolean a=true;


        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try
        {
            String select_Data[] = null;
            SQLiteDatabase db;
            db = dbHelper.getReadableDatabase();

            // maili=mail.getText().toString();
            String strSQL = "SELECT * FROM Tum Where id=0;";

            assert db != null;
            Cursor cursor = db.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst())
                {
                    select_Data = new String[cursor.getColumnCount()];
                    select_Data[0] = cursor.getString(0); // ID
                    select_Data[1] = cursor.getString(1); // AdayID
                    select_Data[2] = cursor.getString(2); // Name
                    select_Data[3] = cursor.getString(3); // Tel
                    select_Data[4] = cursor.getString(4);
                    select_Data[5] = cursor.getString(5);
                    select_Data[6] = cursor.getString(6);
                    select_Data[7] = cursor.getString(7);
                    select_Data[8] = cursor.getString(8);
                    select_Data[9] = cursor.getString(9);
                }
            }

            sayisi=Integer.valueOf(select_Data[1]);




            dbHelper.close();
            db.close();


            // mail.setText(select_Data[1]);
        }
        catch (Exception e)
        {

        }

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


        // String ss=getIntent().getExtras().getString("id");
        //  cv.put("tetikleyici", secilen);

        /*
            <item>5 dk</item>
        <item>15 dk</item>
        <item>30 dk</item>
        <item>1 Saat</item>
        <item>2 Saat</item>
        <item>5 Saat</item>
        <item>Her gün</item>
         */
        String suree="";
        if (sure.getSelectedItem().equals("5 dk"))
        {
            suree="5";
        }
        else if (sure.getSelectedItem().equals("15 dk"))
        {
            suree="15";
        }
        else if (sure.getSelectedItem().equals("30 dk"))
        {
            suree="30";
        }
        else if (sure.getSelectedItem().equals("1 Saat"))
        {
            suree="60";
        }
        else if (sure.getSelectedItem().equals("2 Saat"))
        {
            suree="120";
        }
        else if (sure.getSelectedItem().equals("5 Saat"))
        {
            suree="300";
        }
        else if (sure.getSelectedItem().equals("Her gün"))
        {
            suree="1440";
        }
        else
            suree="5";

        if(ara.isChecked())
        {
            islem="Çağrı Gönderme";
        }
        else if(mesaj.isChecked())
        {
            islem="Mesaj Gönderme";
        }
        else
        {
            islem="Çağrı Gönderme";
        }


        ContentValues cv1=new ContentValues();
        cv1.put("adi",adi.getText().toString());
        cv1.put("turu", db);
        cv1.put("tarih",t);
        cv1.put("saat", s);
        cv1.put("hsure",suree);
        cv1.put("icerik", msj.getText().toString());
        cv1.put("secilen",num.getText().toString());


        // cv1.put("siddet",seek.getText().toString());
        // db1.update("LOG", cv, "bassaat" + "=?", new String[]{"17:00"});
        db1.insert("Tum", null, cv1);
        //cv=cv1;
        cv1.put("islem",islem);
        cv1.put("telefon", num.getText().toString());
        cv1.remove("turu");
        cv1.put("yer",yer);
        cv1.remove("secilen");
        db1.insert("Dogumgunu", null, cv1);


        ContentValues d=new ContentValues();
        d.put("adi",sayisi+1);
        db1.update("Tum", d, "adi" + "=?", new String[]{String.valueOf(sayisi)});

        Toast.makeText(this,"Veritabanına Hatırlatıcınız Eklendi!",Toast.LENGTH_SHORT).show();
        dbHelper.close();
        db1.close();
        Intent intent=new Intent(Dogumgunu.this,AnaSayfa.class);
        startActivity(intent);

    }

    public boolean geri(View v)
    {
        this.finish();
        return true;
    }

    public void sec(View v)
    {
        int s=0;
        if(v.getId()==ara.getId())
        {
            msj.setEnabled(false);

        }
        else
            msj.setEnabled(true);
    }


}
