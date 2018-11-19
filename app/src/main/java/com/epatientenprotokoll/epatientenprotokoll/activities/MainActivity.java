package com.epatientenprotokoll.epatientenprotokoll.activities;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
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
    TextView tDate;
    TextView tWeekday;
    Button urgencyOne;
    Button urgencyTwo;
    ImageButton ibTime;
    PopupWindow mpopup;
    Context mConext;
    LinearLayout vertLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbMasterData = findViewById(R.id.tbMasterData);
        tbStatus = findViewById(R.id.tbStatus);
        tbMaterial = findViewById(R.id.tbMaterial);
        tDate = findViewById(R.id.tDate);
        tWeekday = findViewById(R.id.tWeekday);
        urgencyOne = findViewById(R.id.bUrgency1);
        urgencyTwo = findViewById(R.id.bUrgency2);
        ibTime = findViewById(R.id.ibTime);
        vertLayout = findViewById(R.id.verticalLayout);

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

        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = (cal.get(Calendar.MONTH)) + 1;
        int year = cal.get(Calendar.YEAR) % 100;
        String dayOfMonthString = String.valueOf(dayOfMonth);
        String monthString = String.valueOf(month);
        String yearString = String.valueOf(year);
        tDate.setText(dayOfMonthString + "." + monthString + "." + yearString);

        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        String dayOfWeek = dayFormat.format(cal.getTime());
        tWeekday.setText(dayOfWeek);

        urgencyOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, urgencyOne);
                popup.getMenuInflater().inflate(R.menu.urgency_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        urgencyOne.setText(item.getTitle());
                        return true;
                    }
                });

                popup.show();
            }
        });

        urgencyTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, urgencyTwo);
                popup.getMenuInflater().inflate(R.menu.urgency_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        urgencyTwo.setText(item.getTitle());
                        return true;
                    }
                });

                popup.show();
            }
        });

        ibTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View timeView = inflater.inflate(R.layout.time_view,null, false);

                //mpopup = new PopupWindow(timeView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //mpopup.showAsDropDown(vertLayout);

                final TextView tAlarm = timeView.findViewById(R.id.tAlarm);
                final EditText timeAlarm = timeView.findViewById(R.id.timeAlarm);
                final Button bAlarm = timeView.findViewById(R.id.bAlarm);

                new AlertDialog.Builder(MainActivity.this).setView(timeView).setTitle("Zeiten").show();


                //AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //builder.setTitle("Zeiten");

                //TextView ziit = new TextView(MainActivity.this);
                //ziit.setText("Alarm");
                //builder.setView(ziit);

                //AlertDialog dialog = builder.create();
                //dialog.show();
            }
        });

    }

}
