package com.epatientenprotokoll.epatientenprotokoll.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import android.widget.ToggleButton;
import com.epatientenprotokoll.epatientenprotokoll.R;
import com.epatientenprotokoll.epatientenprotokoll.fragments.MasterDataFragment;
import com.epatientenprotokoll.epatientenprotokoll.fragments.MaterialFragment;
import com.epatientenprotokoll.epatientenprotokoll.fragments.StatusFragment;
import com.epatientenprotokoll.epatientenprotokoll.model.ActionMeasurement;
import com.epatientenprotokoll.epatientenprotokoll.model.Measurement;
import com.epatientenprotokoll.epatientenprotokoll.model.ValueMeasurement;
import com.epatientenprotokoll.epatientenprotokoll.toolbox.ActionToolbox;
import com.epatientenprotokoll.epatientenprotokoll.model.Tool;
import com.epatientenprotokoll.epatientenprotokoll.toolbox.ValueToolBox;
import com.epatientenprotokoll.epatientenprotokoll.toolbox.Toolbox;
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
    private Tool tool;
    private Measurement[][] table = new Measurement[25][15];
    private int ventilationStart;
    private int ventilationEnd;
    private int row;

    //Toolbox
    Toolbox toolbox;

    //ActionToolbox
    private Toolbar toolChooser;

    //ValueToolBox
    private Toolbar valueChooser;

    //DrugToolBox

    /**
     * Sets the inserted patient name from MasterDataFragment.
     * @param name
     */
    public void setPatientName(String name){
        this.name = name;
        setPatientInformation();
    }

    /**
     * Sets the inserted patient lastname from MasterDataFragment.
     * @param lastname
     */
    public void setPatientLastname(String lastname){
        this.lastname = lastname;
        setPatientInformation();
    }

    /**
     * Sets the inserted patient birthdate from MasterDataFragment.
     * @param birthdate
     */
    public void setPatientBirthdate(String birthdate){
        this.birthdate = birthdate;
        setPatientInformation();
    }

    /**
     * Sets the patient information containing patient name, lastname and birthdate in title.
     */
    public void setPatientInformation(){
        patientInformation = findViewById(R.id.patientInformation);
        patientInformation.setText(lastname + " " + name + ", " + birthdate);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPatientInformation();

        // Assigns all variables to views
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

        // Opens MasterDataFragment if Patient button is clicked
        tbMasterData.setOnClickListener(i -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layout_placeholder, new MasterDataFragment());
            transaction.commit();
        });

        // Opens StatusFragment if Status button is clicked
        tbStatus.setOnClickListener(i -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layout_placeholder, new StatusFragment());
            transaction.commit();
        });

        // Opens MaterialFragment if Material button is clicked
        tbMaterial.setOnClickListener(i -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layout_placeholder, new MaterialFragment());
            transaction.commit();
        });

        // Sets the current date
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = (cal.get(Calendar.MONTH)) + 1;
        int year = cal.get(Calendar.YEAR) % 100;
        String dayOfMonthString = String.valueOf(dayOfMonth);
        String monthString = String.valueOf(month);
        String yearString = String.valueOf(year);
        tDate.setText(dayOfMonthString + "." + monthString + "." + yearString);

        // Sets the current weekday
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        String dayOfWeek = dayFormat.format(cal.getTime());
        tWeekday.setText(dayOfWeek);

        // Opens popup if urgency one is clicked
        urgencyOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initializes popup
                PopupMenu popup = new PopupMenu(MainActivity.this, urgencyOne);
                popup.getMenuInflater().inflate(R.menu.urgency_menu, popup.getMenu());

                // Sets text from popup to button
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        urgencyOne.setText(item.getTitle());
                        return true;
                    }
                });

                // Shows the popup
                popup.show();
            }
        });

        // Opens popup if urgency two is clicked
        urgencyTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initializes popup
                PopupMenu popup = new PopupMenu(MainActivity.this, urgencyTwo);
                popup.getMenuInflater().inflate(R.menu.urgency_menu, popup.getMenu());

                // Sets text from popup to button
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        urgencyTwo.setText(item.getTitle());
                        return true;
                    }
                });

                // Shows the popup
                popup.show();
            }
        });

        // Opens dialogBox if time button is clicked
        ibTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View timeView = inflater.inflate(R.layout.time_view,null, false);

                // Assigns all variables to timeView
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

                // Sets text to actual time if button is clicked
                bAlarm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeAlarm.setText(actualTime.getText().toString());
                    }
                });

                // Sets text to actual time if button is clicked
                bAusruck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeAusruck.setText(actualTime.getText().toString());
                    }
                });

                // Sets text to actual time if button is clicked
                bEinsatzort.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeEinsatzort.setText(actualTime.getText().toString());
                    }
                });

                // Sets text to actual time if button is clicked
                bAbfahrt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeAbfahrt.setText(actualTime.getText().toString());
                    }
                });

                // Sets text to actual time if button is clicked
                bSpital.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeSpital.setText(actualTime.getText().toString());
                    }
                });

                // Sets text to actual time if button is clicked
                bEinsatzebereit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeEinsatzbereit.setText(actualTime.getText().toString());
                    }
                });

                // Sets text to actual time if button is clicked
                bEndzeit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeEndzeit.setText(actualTime.getText().toString());
                    }
                });

                // Opens the diaglogBox
                new AlertDialog.Builder(MainActivity.this).setView(timeView).setTitle("Zeiten").show();

            }
        });

        //Initialize measurements and tool singleton
        tool = Tool.getInstance();
        tool.initMeasurements();

        initActionToolChooser();
        initValueToolChooser();
        initMeasuresGrid();
    }

    /**
     * Initializes the grid for measurements and handels the onclick and ondrag-Listener for further actions.
     */
    private void initMeasuresGrid() {
        measuresGrid = findViewById(R.id.measuresGrid);
        measuresGrid.setOnClickListener(new MeasuresGrid.OnClickListener() {
            @Override
            void onClick(View view, int row, int column) {
                if(tool.checkIfToolIsSelected()){
                    if(!tool.getCurrentTool().isMultiMeasure()){
                        onMeasuresGridClick(row, column);
                    }
                }
            }
        });

        measuresGrid.setOnDragListener(new MeasuresGrid.OnDragListener() {
            @Override
            void onDrag(View view, int columnStart, int columnEnd, int row) {
                if(tool.checkIfToolIsSelected()){
                    if(tool.getCurrentTool().isMultiMeasure()){
                        onMeasuresGridDrag(columnStart, columnEnd, row);
                    }
                }
            }
        });

        setMeasuresGrid();
    }

    /**
     * Handles the action if the user clicks on the grid. It gets the selected toolbox symbol and writes it into the two-dimensional table array to store the symbol.
     *
     * @param row - clicked row
     * @param column - clicked column
     */
    private void onMeasuresGridClick(int row, int column) {
        System.out.println("clicked: " + row + " " + column);

        table[row][column] = tool.getCurrentTool();
        setMeasuresGrid();
    }

    /**
     * Handles the action if the user drags on the grid. It gets the selected toolbox symbol and writes it into the two-dimensional table array to store the symbol.
     *
     * @param columnStart - first tap
     * @param columnEnd - last tap
     * @param row - row tapped
     */
    private void onMeasuresGridDrag(int columnStart, int columnEnd, int row) {
        System.out.println("drag: " + columnStart + " " + columnEnd);

        ventilationStart = columnStart;
        ventilationEnd = columnEnd;
        this.row = row;

        setMeasuresGrid();
    }

    /**
     * Actualizes the data table behind the grid.
     */
    private void setMeasuresGrid() {
        measuresGrid.setTable(table);
        measuresGrid.setVentilation(ventilationStart, ventilationEnd, row);
    }

    /**
     * Initializes the tool chooser element on top left screen corner. It allows the user to handle different action tools.
     */
    private void initActionToolChooser(){
        toolChooser = findViewById(R.id.toolbar);
        toolChooser.setOnClickListener(v -> {
            toolbox = new ActionToolbox();
            toolbox.showToolbox(v, R.menu.toolbox);
        });
    }

    /**
     * Initializes the tool chooser element on top left screen corner. It allows the user to handle different value tools.
     */
    private void initValueToolChooser(){
        valueChooser = findViewById(R.id.measure_values_toolbar);
        valueChooser.setOnClickListener(v -> {
            toolbox = new ValueToolBox(this);
            toolbox.showToolbox(v, R.menu.value_toolbox);
        });
    }

    /**
     * Initializes the tool chooser element on top left screen corner. It allows the user to handle different drug tools.
     */
    private void initDrugToolChooser(){
    }
}
