package com.epatientenprotokoll.epatientenprotokoll.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.epatientenprotokoll.epatientenprotokoll.R;
import com.epatientenprotokoll.epatientenprotokoll.fragments.MasterDataFragment;
import com.epatientenprotokoll.epatientenprotokoll.fragments.MaterialFragment;
import com.epatientenprotokoll.epatientenprotokoll.fragments.StatusFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ToggleButton tbMasterData;
    ToggleButton tbStatus;
    ToggleButton tbMaterial;
    TextView tDate;
    TextView tWeekday;
    Button urgencyOne;
    Button urgencyTwo;
    ImageButton ibTime;
    TextClock actualTime;
    PopupWindow mpopup;
    Context mConext;
    LinearLayout vertLayout;

    TextView patientInformation;
    String name = "---";
    String lastname = "";
    String birthdate = "";

    //MeasuresGrid
    private MeasuresGrid measuresGrid;
    String[][] table = new String[15][10];
    int ventilationStart = 2;
    int ventilationEnd = 5;


    public void setPatientName(String name){
        this.name = name;
        setPatientInformation();
    }

    public void setPatientLastname(String lastname){
        this.lastname = lastname;
        setPatientInformation();
    }

    public void setPatientBirthdate(String birthdate){
        this.birthdate = birthdate;
        setPatientInformation();
    }

    public void setPatientInformation(){
        patientInformation = findViewById(R.id.patientInformation);
        patientInformation.setText(lastname + " " + name + ", " + birthdate);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPatientInformation();

        tbMasterData = findViewById(R.id.tbMasterData);
        tbStatus = findViewById(R.id.tbStatus);
        tbMaterial = findViewById(R.id.tbMaterial);
        tDate = findViewById(R.id.tDate);
        tWeekday = findViewById(R.id.tWeekday);
        urgencyOne = findViewById(R.id.bUrgency1);
        urgencyTwo = findViewById(R.id.bUrgency2);
        ibTime = findViewById(R.id.ibTime);
        vertLayout = findViewById(R.id.verticalLayout);
        actualTime = findViewById(R.id.textClock);

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
                //mpopup.showAsDropDown(vertLayout);gSf

                EditText timeAlarm = timeView.findViewById(R.id.timeAlarm);
                EditText timeAusruck = timeView.findViewById(R.id.timeAusruck);
                EditText timeEinsatzort = timeView.findViewById(R.id.timeEinsatzort);
                EditText timeAbfahrt = timeView.findViewById(R.id.timeAbfahrt);
                EditText timeSpital = timeView.findViewById(R.id.timeSpital);
                EditText timeEinsatzbereit = timeView.findViewById(R.id.timeEinsatzbereit);
                EditText timeEndzeit = timeView.findViewById(R.id.timeEndzeit);
                Button bAlarm = timeView.findViewById(R.id.bAlarm);
                Button bAusruck = timeView.findViewById(R.id.bAusruck);
                Button bEinsatzort = timeView.findViewById(R.id.bEinsatzort);
                Button bAbfahrt = timeView.findViewById(R.id.bAbfahrt);
                Button bSpital = timeView.findViewById(R.id.bSpital);
                Button bEinsatzebereit = timeView.findViewById(R.id.bEinsatzbereit);
                Button bEndzeit = timeView.findViewById(R.id.bEndzeit);

                bAlarm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeAlarm.setText(actualTime.getText().toString());
                    }
                });

                bAusruck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeAusruck.setText(actualTime.getText().toString());
                    }
                });

                bEinsatzort.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeEinsatzort.setText(actualTime.getText().toString());
                    }
                });

                bAbfahrt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeAbfahrt.setText(actualTime.getText().toString());
                    }
                });

                bSpital.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeSpital.setText(actualTime.getText().toString());
                    }
                });

                bEinsatzebereit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeEinsatzbereit.setText(actualTime.getText().toString());
                    }
                });

                bEndzeit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeEndzeit.setText(actualTime.getText().toString());
                    }
                });

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

        initMeasuresGrid();
    }

    private void initMeasuresGrid() {
        measuresGrid = findViewById(R.id.measuresGrid);
        measuresGrid.setOnClickListener(new MeasuresGrid.OnClickListener() {
            @Override
            void onClick(View view, int row, int column) {
                onMeasuresGridClick(row, column);
            }
        });

        measuresGrid.setOnDragListener(new MeasuresGrid.OnDragListener() {
            @Override
            void onDrag(View view, int columnStart, int columnEnd) {
                onMeasuresGridDrag(columnStart, columnEnd);
            }
        });

        setMeasuresGrid();
    }

    private void onMeasuresGridClick(int row, int column) {
        System.out.println("clicked: " + row + " " + column);

        table[row][column] = "X";

        setMeasuresGrid();
    }

    private void onMeasuresGridDrag(int columnStart, int columnEnd) {
        System.out.println("drag: " + columnStart + " " + columnEnd);

        ventilationStart = columnStart;
        ventilationEnd = columnEnd;

        setMeasuresGrid();
    }

    private void setMeasuresGrid() {
        measuresGrid.setTable(table);
        measuresGrid.setVentilation(ventilationStart, ventilationEnd);
    }
}
