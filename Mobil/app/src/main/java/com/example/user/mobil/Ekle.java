package com.example.user.mobil;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Ekle extends AppCompatActivity {

    Spinner tur;
    Button ileri,geri,tarih,saatt;
    EditText yer;
    TextView s,t,y;
    String[] turr;
    String sh,st,gun,ay;
    public String gelen;
    ArrayAdapter<CharSequence> adapter;
    DatabaseHelper dbHelper;
    Date date= new Date();
    Intent intent;
    CustomOnItemSelectedListener coı=new CustomOnItemSelectedListener();

    EditText tarh,saat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekle);
        tur=(Spinner)findViewById(R.id.spinner);
        adapter=ArrayAdapter.createFromResource(this,R.array.tur,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        tur.setAdapter(adapter);
        /*saatt=(Button)findViewById(R.id.button3);
        tarih=(Button)findViewById(R.id.button);*/
        s=(TextView)findViewById(R.id.textView9);
        t=(TextView)findViewById(R.id.textView11);
       // y=(TextView)findViewById(R.id.textView6);
        yer=(EditText)findViewById(R.id.editText2);
        addListenerOnSpinnerItemSelection();

        tarh=(EditText)findViewById(R.id.editText);
        saat=(EditText)findViewById(R.id.editText3);
        // saatt.setText(date.toString());
        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        //   String a=coı.poss.toString();
        int c=0;
    }
    public void addListenerOnSpinnerItemSelection() {
        tur.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        // saat.setText(gelen);
    }
    public void saat(final View v1)
    {
        Calendar mcurrentTime = Calendar.getInstance();//
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
        int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
        TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk

        //TimePicker objemizi oluşturuyor ve click listener ekliyoruz
        timePicker = new TimePickerDialog(Ekle.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                if(selectedHour==1 || selectedHour==2 || selectedHour==3 ||selectedHour==4||selectedHour==5||selectedHour==6||selectedHour==7||selectedHour==8||selectedHour==9)
                    sh="0"+String.valueOf(selectedHour);
                else
                    sh=String .valueOf(selectedHour);

                if(selectedMinute==1 || selectedMinute==2 || selectedMinute==3 ||selectedMinute==4||selectedMinute==5||selectedMinute==6||selectedMinute==7||selectedMinute==8||selectedMinute==9)
                    st="0"+String.valueOf(selectedMinute);
                else
                    st=String.valueOf(selectedMinute);
                saat.setText(  "      "+sh + ":" + st+"       ");

                s.setText(sh+":"+st);
            }
        }, hour, minute, true);//true 24 saatli sistem için
        timePicker.setTitle("Saat Seçiniz");
        timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", timePicker);
        timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", timePicker);


        timePicker.show();
    }



    public void tarih(View v)
    {
        Calendar mcurrentTime = Calendar.getInstance();
        int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
        int month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
        int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz

        DatePickerDialog datePicker;//Datepicker objemiz
        datePicker = new DatePickerDialog(Ekle.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                if(dayOfMonth==1 ||dayOfMonth==2||dayOfMonth==3||dayOfMonth==4||dayOfMonth==5||dayOfMonth==6||dayOfMonth==7||dayOfMonth==8||dayOfMonth==9)
                    gun="0"+String.valueOf(dayOfMonth);
                else
                    gun=String.valueOf(dayOfMonth);

                if(monthOfYear==0|| monthOfYear==1 ||monthOfYear==2||monthOfYear==3||monthOfYear==4||monthOfYear==5||monthOfYear==6||monthOfYear==7||monthOfYear==8||monthOfYear==9)
                    ay="0"+String.valueOf(monthOfYear+1);
                else ay=String.valueOf(monthOfYear+1);

                tarh.setText( gun + "/" + ay+ "/"+year);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
                t.setText(gun+"/"+ay+"/"+year);

            }
        },year,month,day);//başlarken set edilcek değerlerimizi atıyoruz
        datePicker.setTitle("Tarih Seçiniz");
        datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);
        datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);
        datePicker.show();
    }

    public boolean geri(View v)
    {
        this.finish();
        return true;
    }

    public void ileri(View v)
    {
        String as= tur.getSelectedItem().toString();
        int a=tur.getSelectedItemPosition();
        String db="";
        if (as.equals("Yapılacaklar"))
        {
            db="Yapilacak";

        }
        else if (as.equals("Doğum Günü"))
        {
            db="Dogumgunu";

        }
        else if (as.equals("Sınav"))
        {
            db="Sinav";

        }
        else if (as.equals("Toplantı"))
        {
            db="Toplanti";

        }
     //   y.setText(db.toString());
        int aa=351;

        if(db.equals("Yapilacak") || db.equals("Toplanti"))
        {
            intent=new Intent(Ekle.this,Sayfa.class);
            intent.putExtra("db",db);
            intent.putExtra("yer",yer.getText().toString());
            intent.putExtra("t",t.getText().toString());
            intent.putExtra("s",s.getText().toString());


        }
        else if(db.equals("Dogumgunu"))
        {
            intent=new Intent(Ekle.this,Dogumgunu.class);
            intent.putExtra("db",db);
            intent.putExtra("yer",yer.getText().toString());
            intent.putExtra("t",t.getText().toString());
            intent.putExtra("s",s.getText().toString());
        }
        else if(db.equals("Sinav"))
        {
            intent=new Intent(Ekle.this,Sinav.class);
            intent.putExtra("db",db);
            intent.putExtra("yer",yer.getText().toString());
            intent.putExtra("t",t.getText().toString());
            intent.putExtra("s",s.getText().toString());
        }

        startActivity(intent);
    }

}
