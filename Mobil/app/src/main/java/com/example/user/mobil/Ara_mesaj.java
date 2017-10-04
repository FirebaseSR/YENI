package com.example.user.mobil;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ara_mesaj extends AppCompatActivity {

    private String telno,icerik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ara_mesaj);
        telno =getIntent().getExtras().get("tel").toString();
        icerik=getIntent().getExtras().get("icerik").toString();
        if(icerik.equals(""))
            aramaFonk2(telno);
        else
        msj(telno,icerik);

    }
    private void aramaFonk1(String telephoneNumber){
        try{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + telephoneNumber));
         //   startActivity(intent);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void  msj(String tel,String icerigi)
    {
        Uri uri = Uri.parse("smsto:" + tel);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", icerigi);
        startActivity(intent);
    }

    private void aramaFonk2(String telephoneNumber){
        try{

            Intent myIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+telephoneNumber));
            startActivity(myIntent);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
