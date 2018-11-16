package com.epatientenprotokoll.epatientenprotokoll.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.epatientenprotokoll.epatientenprotokoll.R;
import com.epatientenprotokoll.epatientenprotokoll.fragments.MasterDataFragment;
import com.epatientenprotokoll.epatientenprotokoll.fragments.MaterialFragment;
import com.epatientenprotokoll.epatientenprotokoll.fragments.StatusFragment;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ToggleButton tbMasterData;
    ToggleButton tbStatus;
    ToggleButton tbMaterial;
    Button bCurrentTime;
    Button bDate;
    Button bWeekday;
    TextView tCurrentTime;
    TextView tDate;
    TextView tWeekday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbMasterData = findViewById(R.id.tbMasterData);
        tbStatus = findViewById(R.id.tbStatus);
        tbMaterial = findViewById(R.id.tbMaterial);
        //bCurrentTime = findViewById(R.id.bCurrentTime);
        //bDate = findViewById(R.id.bDate);
        //bWeekday = findViewById(R.id.bWeekday);
        //tCurrentTime = findViewById(R.id.tCurrentTime);
        tDate = findViewById(R.id.tDate);
        tWeekday = findViewById(R.id.tWeekday);

        tbMasterData.setOnClickListener(i -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layout_placeholder, new MasterDataFragment());
            transaction.commit();
        });

        tbStatus.setOnClickListener(i -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layout_placeholder, new StatusFragment());
            transaction.commit();
        });

        tbMaterial.setOnClickListener(i -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layout_placeholder, new MaterialFragment());
            transaction.commit();
        });


        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTimeOutput = timeFormat.format(currentTime.getTime());
        //bCurrentTime.setText(currentTimeOutput);
        //tCurrentTime.setText(currentTimeOutput);

        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = (cal.get(Calendar.MONTH)) + 1;
        int year = cal.get(Calendar.YEAR) % 100;
        String dayOfMonthString = String.valueOf(dayOfMonth);
        String monthString = String.valueOf(month);
        String yearString = String.valueOf(year);
        //bDate.setText(dayOfMonthString + "." + monthString + "." + yearString);
        tDate.setText(dayOfMonthString + "." + monthString + "." + yearString);

        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        String dayOfWeek = dayFormat.format(cal.getTime());
        //bWeekday.setText(dayOfWeek);
        tWeekday.setText(dayOfWeek);
    }

}
