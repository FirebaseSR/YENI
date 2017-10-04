package com.example.user.mobil;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tum extends AppCompatActivity  {

    List<String> categories;
    ArrayAdapter<String> dataAdapter;
    List<String> abone0,abone1,abone2,abone3,abone4,abone5,abone6,abone7,abone8,abone9,abone10,abone11,abone12,abone13,abone14,abone15,abone16,abone17,abone18,abone19,abone20;
    HashMap<String, List<String>> childContent;
    String borcc,id,sfr,sfr1;
    private ExpandableListView expandableListView;
    DatabaseHelper dbHelper;
    String icerik="";
    //String icer[]=null;
    String ic="";
    String abon[][]=null;
    List<String> a[]=null;

    private List<String>parentHeaderInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tum);

        categories = new ArrayList<String>();
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,categories);

        parentHeaderInformation = new ArrayList<String>();

        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        new GetBilgilerim().execute();
        HashMap<String, List<String>> allChildItems = returnGroupedChildItems();
        expandableListView = (ExpandableListView)findViewById(R.id.expandableListView);



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
        int as=Integer.valueOf(select_Data[1]);

        for(int i=1;i<=as;i++)
        {
            try
            {
               String select_Data1[] = null;
                SQLiteDatabase db1;
                db1 = dbHelper.getReadableDatabase();

                // maili=mail.getText().toString();
                String sql = "SELECT * FROM Tum Where id='"+i+"';";
                // String sql = "SELECT * FROM Tum Where id=5;";
                assert db1 != null;
                Cursor cursor1 = db1.rawQuery(sql, null);
                if(cursor1 != null)
                {
                    if (cursor1.moveToFirst())
                    {
                        select_Data1 = new String[cursor1.getColumnCount()];
                        select_Data1[0] = cursor1.getString(0); // ID
                        select_Data1[1] = cursor1.getString(1); // AdayID
                        select_Data1[2] = cursor1.getString(2); // Name
                        select_Data1[3] = cursor1.getString(3); // Tel
                        select_Data1[4] = cursor1.getString(4);
                        select_Data1[5] = cursor1.getString(5);
                        select_Data1[6] = cursor1.getString(6);
                        select_Data1[7] = cursor1.getString(7);
                        select_Data1[8] = cursor1.getString(8);
                        select_Data1[9] = cursor1.getString(9);
                    }
                }

                id=select_Data1[1].toString();
                sfr=select_Data1[3].toString();
                sfr1=select_Data1[4].toString();
                icerik=select_Data1[5].toString();
                int iii=icerik.length();
                parentHeaderInformation.add(id + "   " + sfr);
                //if(i==1)
         //  childContent.put(parentHeaderInformation.get(i), abone0);
                int c=0;
                String alinan="";
                ic=icerik;
                String icer[] = new String[iii+1];
              //  String abon[][]=new String[Integer.valueOf(select_Data1[7])][c];
//if(icerik.length()<1)

                for(int j=0;j<=iii;j++)
                {


                    if(iii==0||j==iii||ic.substring(j,j+1).equals(",") )
                    {
                        if(iii==0 || j==iii)
                        {
                            if(j==iii)
                                icer[c]=alinan;
                            if(iii==0)
                            icer[c]="Eleman bulunmamaktadır";
                        }
                        else
                        icer[c]=alinan;
                        //abon[i][j]=icer[c];
                        //a[i].add(abon[i][j]);


                        if(i==1)
                        {   abone0.add(icer[c]);
                            childContent.put(parentHeaderInformation.get(0), abone0);
                        }

                        if(i==2)
                        {
                            abone1.add(icer[c]);
                            childContent.put(parentHeaderInformation.get(1), abone1);
                        }
                        if(i==3)
                        {
                            abone2.add(icer[c]);
                            childContent.put(parentHeaderInformation.get(2), abone2);
                        }
                        if(i==4)
                        {
                            abone3.add(icer[c]);
                            childContent.put(parentHeaderInformation.get(3), abone3);
                        }
                        if(i==5)
                        {
                            abone4.add(icer[c]);
                            childContent.put(parentHeaderInformation.get(4), abone4);
                        }
                        if(i==6)
                        {
                            abone5.add(icer[c]);
                            childContent.put(parentHeaderInformation.get(5), abone5);
                        }
                        if(i==7)
                        {
                            abone6.add(icer[c]);
                            childContent.put(parentHeaderInformation.get(6), abone6);
                        }if(i==8)
                    {
                        abone7.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(7), abone7);
                    }if(i==9)
                    {
                        abone8.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(8), abone8);
                    }if(i==10)
                    {
                        abone9.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(9), abone9);
                    }if(i==11)
                    {
                        abone10.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(10), abone10);
                    }if(i==12)
                    {
                        abone11.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(11), abone11);
                    }if(i==13)
                    {
                        abone12.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(12), abone12);
                    }if(i==14)
                    {
                        abone13.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(13), abone13);
                    }if(i==15)
                    {
                        abone14.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(14), abone14);
                    }if(i==16)
                    {
                        abone15.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(15), abone15);
                    }if(i==17)
                    {
                        abone16.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(16), abone16);
                    }if(i==18)
                    {
                        abone17.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(17), abone17);
                    }if(i==19)
                    {
                        abone18.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(18), abone18);
                    }if(i==20)
                    {
                        abone19.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(19), abone19);
                    }if(i==21)
                    {
                        abone20.add(icer[c]);
                        childContent.put(parentHeaderInformation.get(20), abone20);
                    }
                        c++;
                        alinan="";
                    }
                    else
                        alinan+=ic.substring(j,j+1);
                }


                dbHelper.close();
                db.close();


                // mail.setText(select_Data[1]);
            }
            catch (Exception e)
            {

            }



           // parentHeaderInformation.add(id + "   " + sfr);
            //if(i==1)
        /*    childContent.put(parentHeaderInformation.get(i), abone0);
           // if(i==2)
                childContent.put(parentHeaderInformation.get(i), abone1);
           // if(i==5)
                childContent.put(parentHeaderInformation.get(i), abone2);*/

        }


        ExpandableListViewAdapter expandableListViewAdapter = new ExpandableListViewAdapter(getApplicationContext(), parentHeaderInformation, allChildItems);
        expandableListView.setAdapter(expandableListViewAdapter);
    }

    private class GetBilgilerim extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String Url = "http://192.168.50.8:801/api/Abone/AboneSorgulama?sorgutur=tckimlik&tckimlik="+/*tcno+*/"&sayacno=0&adsoyad=0";
            JSONObject jsonObject = null;

                      //  categories.add("dfghg");





            return null;
        }

        @Override
        protected void onPostExecute(Void args) {


        }
    }

    public HashMap<String, List<String>> returnGroupedChildItems() {

        childContent = new HashMap<String, List<String>>();
        abone0 = new ArrayList<String>();
       /* abone0.add("df");
        abone0.add("gfghh");
        abone0.add(parentHeaderInformation.get(0));*/
        abone1 = new ArrayList<String>();
        abone2 = new ArrayList<String>();
        abone3 = new ArrayList<String>();
        abone4 = new ArrayList<String>();
        abone5 = new ArrayList<String>();
        abone6 = new ArrayList<String>();
        abone7 = new ArrayList<String>();
        abone8 = new ArrayList<String>();
        abone9 = new ArrayList<String>();
        abone10 = new ArrayList<String>();
        abone11 = new ArrayList<String>();
        abone12= new ArrayList<String>();
        abone13= new ArrayList<String>();
        abone14 = new ArrayList<String>();
        abone15 = new ArrayList<String>();
        abone16 = new ArrayList<String>();
        abone17 = new ArrayList<String>();
        abone18 = new ArrayList<String>();
        abone19 = new ArrayList<String>();
        abone20 = new ArrayList<String>();
        //abone21 = new ArrayList<String>();









        return childContent;
    }
}
