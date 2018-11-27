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
import android.widget.TextView;
import android.widget.ToggleButton;
import com.epatientenprotokoll.epatientenprotokoll.R;
import com.epatientenprotokoll.epatientenprotokoll.fragments.MasterDataFragment;
import com.epatientenprotokoll.epatientenprotokoll.fragments.MaterialFragment;
import com.epatientenprotokoll.epatientenprotokoll.fragments.StatusFragment;
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
    LinearLayout vertLayout;

    //MeasuresGrid
    private MeasuresGrid measuresGrid;
    private Tool tool;
    int[][] table = new int[25][15];
    int ventilationStart;
    int ventilationEnd;
    int row;

    //Toolbox
    Toolbox toolbox;

    //ActionToolbox
    private Toolbar toolChooser;

    //ValueToolBox
    private Toolbar valueChooser;

    //DrugToolBox

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

        table[row][column] = tool.getCurrentTool().getSymbol();
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
     * Initializes the tool chooser element on top left screen corner. It allows the user to handle different tools.
     */
    private void initActionToolChooser(){
        toolChooser = findViewById(R.id.toolbar);
        toolChooser.setOnClickListener(v -> {
            toolbox = new ActionToolbox();
            toolbox.showToolbox(v, R.menu.toolbox);
        });
    }

    private void initValueToolChooser(){
        valueChooser = findViewById(R.id.measure_values_toolbar);
        valueChooser.setOnClickListener(v -> {
            toolbox = new ValueToolBox(this);
            toolbox.showToolbox(v, R.menu.value_toolbox);
        });
    }

    private void initDrugToolChooser(){
    }
}
