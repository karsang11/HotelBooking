package com.karsang.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etname;
    Button btncheckin,btncheckout;
    TextView tvcountry, tvroom;
    Spinner spincountry,spinroom;
    EditText etkid,etadult,etroom;
    Button btncalc;
    TextView tvindate,tvoutdate,tverr,tvdayofstay,tvroomno,suitetotal,tvtotal,tvVat;
    int month3,month2;
    int day3,day2;
    int year3,year2;
    int no_of_room;
    int totasuite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname=findViewById(R.id.etname);
        btncheckin=findViewById(R.id.btncheckin);
        btncheckout=findViewById(R.id.btncheckout);
        tvcountry=findViewById(R.id.tvcountry);
        tvroom=findViewById(R.id.tvroom);
        spincountry=findViewById(R.id.spincountry);
        spinroom=findViewById(R.id.spinroom);
        etkid=findViewById(R.id.etkid);
        etadult=findViewById(R.id.etadult);
        etroom=findViewById(R.id.etroom);
        btncalc=findViewById(R.id.btncalc);
        tvindate=findViewById(R.id.tvindate);
        tvoutdate=findViewById(R.id.tvoutdate);
        tverr=findViewById(R.id.tverr);
        tvdayofstay=findViewById(R.id.tvdayofstay);
        tvroomno=findViewById(R.id.tvroomno);
        suitetotal=findViewById(R.id.totalsuite);
        tvtotal=findViewById(R.id.tvtotal);
        tvVat=findViewById(R.id.tvVat);

        String country[]={"Nepal","Japan","China","Bhutan","USA","Canada"};
        String Room[]={"Deluxe","Residential","Platinum"};
        ArrayAdapter array=new ArrayAdapter(this,android.R.layout.simple_list_item_1,country);
        ArrayAdapter auto=new ArrayAdapter(this,android.R.layout.simple_list_item_1,Room);

        spincountry.setAdapter(array);
        spinroom.setAdapter(auto);


        btncheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadcalendar1();
            }
        });

        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCalendar2();
            }
        });

        }

    private void loadCalendar2()
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "Checked Out At::" + month + "/" + dayOfMonth + "/" + year;
                month3 = month;
                day3 = dayOfMonth;
                year3 = year;
                tvoutdate.setText(date);
            }

        }, year, month, day);
        datePickerDialog.show();
    }

    private void loadcalendar1()
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "Checked In At::" + month + "/" + dayOfMonth + "/" + year;
                month2 = month;
                day2 = dayOfMonth;
                year2 = year;
                tvindate.setText(date);
            }

        }, year, month, day);
        datePickerDialog.show();


        btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (TextUtils.isEmpty(tvindate.getText())) {
                    tverr.setText("Please enter Checked in Date ");
                    return;
                }
                else if (TextUtils.isEmpty(etkid.getText())) {
                    tverr.setText("Please enter number of Kids ");
                    return;
                }
                else if (TextUtils.isEmpty(etadult.getText())) {
                    tverr.setText("Please enter number of adults ");
                    return;
                }
                else if (TextUtils.isEmpty(tvoutdate.getText())) {
                    tverr.setText("Please enter Checked out Date ");
                    return;
                }
               else if (TextUtils.isEmpty(etroom.getText())) {
                    tverr.setText("Please enter Number of Rooms ");
                    return;
                }


                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.set(year2, month2, day2);
                cal2.set(year3, month3, day3);
                long millis1 = cal1.getTimeInMillis();
                long millis2 = cal2.getTimeInMillis();
                long diff = millis2 - millis1;
                long diffDays = (diff / (86400000));

                no_of_room = Integer.parseInt(etroom.getText().toString());
                double price;
                double Total_Price;
                double Grand_Total;


                String suite = spinroom.getSelectedItem().toString();


                if (suite == "Deluxe")
                {

                    price = 2000;
                    Total_Price = price * no_of_room * diffDays;
                    Grand_Total = (0.13) * Total_Price + Total_Price;

                    tvdayofstay.setText("Total Days:" + diffDays);
                    tvroomno.setText(("Number of Room(s):" + no_of_room));
                    suitetotal.setText("Room Price Per Night:" + "2000");
                    tvtotal.setText("Total:" + Total_Price);
                    tvVat.setText("Grand Total:" + Grand_Total);
                    Toast.makeText(MainActivity.this, "Total price is" + Grand_Total, Toast.LENGTH_SHORT).show();
                }
                else if (suite == "Residential")
                {

                    price = 7000;
                    Total_Price = price * no_of_room * diffDays;
                    Grand_Total = (0.13) * Total_Price + Total_Price;
                    tvdayofstay.setText("Total Days:" + diffDays);
                    tvroomno.setText(("Number of Room(s):" + no_of_room));
                    suitetotal.setText("Room Price Per Night:" + "3000");
                    tvtotal.setText("Total:" + Total_Price);
                    tvVat.setText("Grand Total:" + Grand_Total);
                    Toast.makeText(MainActivity.this, "Total price is" + Grand_Total, Toast.LENGTH_SHORT).show();

                }
                else if (suite == "Platinum")
                {
                    price = 4000;
                    Total_Price = price * no_of_room * diffDays;
                    Grand_Total = (0.13) * Total_Price + Total_Price;
                    tvdayofstay.setText("Total Days:" + diffDays);
                    tvroomno.setText(("Number of Room(s):" + no_of_room));
                    suitetotal.setText("Room Price Per Night:" + "4000");
                    tvtotal.setText("Total:" + Total_Price);
                    tvVat.setText("Grand Total:" + Grand_Total);
                    Toast.makeText(MainActivity.this, "Total price is" + Grand_Total, Toast.LENGTH_SHORT).show();

                }


            }

        });

    }

}
