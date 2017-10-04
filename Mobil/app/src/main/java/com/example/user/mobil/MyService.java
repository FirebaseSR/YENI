package com.example.user.mobil;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by user on 3.12.2016.
 */
public class MyService extends Service {

    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String sql;
    String  dayy,monthh;
    Cursor cursor1;
    String select_Data[][] = null;
    String select_Data1[] = null;
    Calendar mcurrentTime = Calendar.getInstance();
    int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
    int month = mcurrentTime.get(Calendar.MONTH)+1;//Güncel Ayı alıyoruz
    int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz
   // Calendar mcurrentTime = Calendar.getInstance();//
    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
    int minute = mcurrentTime.get(Calendar.MINUTE);
    int i1=1;
    public MyService() {
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

    private static final String TAG =
            "com.example.user.servis";

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "Service onStartCommand");

        db = dbHelper.getReadableDatabase();

        // maili=mail.getText().toString();
        String strSQL = "SELECT * FROM Tum Where id=0;";

        assert db != null;

        Cursor cursor = db.rawQuery(strSQL, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                select_Data1 = new String[cursor.getColumnCount()];
                select_Data1[0] = cursor.getString(0); // ID
                select_Data1[1] = cursor.getString(1); // AdayID
                select_Data1[2] = cursor.getString(2); // Name
                select_Data1[3] = cursor.getString(3); // Tel
                select_Data1[4] = cursor.getString(4);
                select_Data1[5] = cursor.getString(5);
                select_Data1[6] = cursor.getString(6);
                select_Data1[7] = cursor.getString(7);
                select_Data1[8] = cursor.getString(8);
                select_Data1[9] = cursor.getString(9);
            }
        }
        int c=Integer.valueOf(select_Data1[1]);
        select_Data = new String[c][cursor.getColumnCount()];
        for (int i=i1; i <= c; i++) {

            try {


                sql = "SELECT * FROM Tum Where id='"+ i +"';";
               // sql = "SELECT * FROM Tum Where id=1;";
                assert db != null;
                cursor1 = db.rawQuery(sql, null);


                if (cursor1 != null) {
                    if (cursor1.moveToFirst()) {

                        select_Data[i-1][0] = cursor1.getString(0); // ID
                        select_Data[i-1][1] = cursor1.getString(1); // AdayID
                        select_Data[i-1][2] = cursor1.getString(2); // Name
                        select_Data[i-1][3] = cursor1.getString(3); // Tel
                        select_Data[i-1][4] = cursor1.getString(4);
                        select_Data[i-1][5] = cursor1.getString(5);
                        select_Data[i-1][6] = cursor1.getString(6);
                        select_Data[i-1][7] = cursor1.getString(7);
                        select_Data[i-1][8] = cursor1.getString(8);
                        select_Data[i-1][9] = cursor1.getString(9);
                    }
                }

                if(day==0 ||day==1||day==2||day==3||day==4||day==5||day==6||day==7||day==8||day==9)
                    dayy="0"+day;
                else
                dayy=String.valueOf(day);
                if(month==0 ||month==1||month==2||month==3||month==4||month==5||month==6||month==7||month==8||month==9)
                    monthh="0"+month;
                else
                monthh=String.valueOf(month);
                if(select_Data[i-1][3].equals(dayy+"/"+monthh+"/"+String.valueOf(year))/* && select_Data[i-1][4].equals(hour+":"+minute))*/|| select_Data[i-1][6].equals("1440"))
                {
                    long endTime = System.currentTimeMillis() +
                            5 * 1000;
                    while (System.currentTimeMillis() < endTime) {
                        synchronized (this) {
                            try {
                                wait(endTime - System.currentTimeMillis());
                            } catch (Exception e) {
                            }
                        }
                    }
                    //Toast.makeText(this,"fdgdgdh",Toast.LENGTH_SHORT);
         /*   NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this)

                            .setContentTitle("sdfg")
                            .setDefaults(Notification.DEFAULT_ALL)

                            .setContentText("Something interesting happened");
            int NOTIFICATION_ID = 12345;

            Intent targetIntent = new Intent(this,MyService.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);*/

                    String s = select_Data[i-1][2];
                    Intent intentt ;//= new Int
                    // ent(this, AnaSayfa.class);
                    if(s.equals("Dogumgunu"))
                    {

                        intentt=new Intent(this,Ara_mesaj.class);
                        intentt.putExtra("tel",select_Data[i-1][8]);
                        intentt.putExtra("icerik",select_Data[i-1][5]);
                        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intentt, 0);
                        Notification myNotify = new Notification.Builder(this)
                                .setContentTitle(s)
                                .setContentText(select_Data[i - 1][1])
                                .setContentIntent(pIntent)
                                .setSmallIcon(R.drawable.dg)
                                .setAutoCancel(true)

                                        //.setOnlyAlertOnce(true)

                                .build();
                        // myNotify.wait(5);

                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify((int)System.currentTimeMillis(), myNotify);


                    }
                    else {
                        intentt = new Intent(this, AnaSayfa.class);
                        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intentt, 0);
                        Notification myNotify = new Notification.Builder(this)
                                .setContentTitle(s)
                                .setContentText(select_Data[i - 1][1])
                                .setContentIntent(pIntent)
                                .setSmallIcon(R.drawable.e)
                                .setAutoCancel(true)

                                        //.setOnlyAlertOnce(true)

                                .build();
                        // myNotify.wait(5);

                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify((int) System.currentTimeMillis(), myNotify);
                    }
                    Log.i(TAG, "Service running");
                }
                i1++;
         /*   id=select_Data[1].toString();
            sfr=select_Data[3].toString();
            sfr1=select_Data[4].toString();*/







            } catch (Exception e) {

            }


            // mail.setText(select_Data[1]);
           // return Service.START_STICKY;

        }
        dbHelper.close();
        db.close();

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "Service onDestroy");
    }
}