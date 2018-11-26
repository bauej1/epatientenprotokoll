package com.epatientenprotokoll.epatientenprotokoll.model;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import com.epatientenprotokoll.epatientenprotokoll.R;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Toolbox {

    private ArrayList<Measurement> measurements;
    private Measurement actualTool;

    public Toolbox(){
        measurements = new ArrayList<>();
        initTools();
    }

    /**
     * Shows the toolbox for the user and forces the Android OS to show icons with reflections.
     * @param v
     */
    public void showToolbox(View v){
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.toolbox, popup.getMenu());

        Object menuHelper;
        Class[] argTypes;
        try {
            Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
            fMenuHelper.setAccessible(true);
            menuHelper = fMenuHelper.get(popup);
            argTypes = new Class[]{boolean.class};
            menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
        } catch (Exception e) {

        }
        popup.show();

        popup.setOnMenuItemClickListener(menuItem -> {
            getMeasureTool(menuItem);
            return true;
        });
    }

    /**
     * Passes the tool Id to evalActualTool()
     * @param m
     */
    private void getMeasureTool(MenuItem m){
        int toolId = -1;
        switch(m.getItemId()){
            case R.id.tool_ventilation:
                toolId = 1;
                break;
            case R.id.tool_intubation:
                toolId = 2;
                break;
            case R.id.tool_defibrilation:
                toolId = 3;
                break;
            case R.id.tool_pacing:
                toolId = 4;
                break;
            case R.id.tool_heart_massage:
                toolId = 5;
                break;
            case R.id.tool_blood_pressure:
                toolId = 6;
                break;
            case R.id.tool_pulse:
                toolId = 7;
                break;
        }
        evalActualTool(toolId);
    }

    /**
     * Evaluates which tool is actually selected.
     * @param toolId
     */
    private void evalActualTool(int toolId){
        actualTool = null;

        for(Measurement m : measurements){
            if(m.getId() == toolId) actualTool = m;
        }
    }

    /**
     * Returns the actual selected tool.
     * @return the selected tool.
     */
    public Measurement getActualTool(){
        return actualTool;
    }

    /**
     * Checks if a tool is selected before drawing on the grid.
     * @return true if a tool is selected
     */
    public boolean checkIfToolIsSelected(){
        return actualTool == null ? false : true;
    }

    /**
     * Initializes all possible tools with its params and adds them to a collection.
     */
    private void initTools(){
        Measurement ventilation = new Measurement(1, R.drawable.ventilation, true, 0, 0, 0, 0);
        Measurement inextubation = new Measurement(2, R.drawable.intubation_extubation, false, 0, 0, 0, 0);
        Measurement defibrilation = new Measurement(3, R.drawable.defibrilation, false, 0, 0, 0, 0);
        Measurement extPacing = new Measurement(4, R.drawable.pacing, false, 0, 0, 0, 0);
        Measurement heartMassage = new Measurement(5, R.drawable.heart_massage, false, 0, 0, 0, 0);
        Measurement bloodPressure = new Measurement(6, R.drawable.heart_massage, true, 0, 0, 0, 0);
        Measurement pulse = new Measurement(7, R.drawable.pulse, false, 0, 0, 0, 0);

        measurements.add(ventilation);
        measurements.add(inextubation);
        measurements.add(defibrilation);
        measurements.add(extPacing);
        measurements.add(heartMassage);
        measurements.add(bloodPressure);
        measurements.add(pulse);
    }
}
