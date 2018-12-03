package com.epatientenprotokoll.epatientenprotokoll.toolbox;

import android.app.Activity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.epatientenprotokoll.epatientenprotokoll.R;
import com.epatientenprotokoll.epatientenprotokoll.dialogs.BreathFrequencyDialog;
import com.epatientenprotokoll.epatientenprotokoll.dialogs.Co2Dialog;
import com.epatientenprotokoll.epatientenprotokoll.dialogs.HgtDialog;
import com.epatientenprotokoll.epatientenprotokoll.dialogs.MotherDialog;
import com.epatientenprotokoll.epatientenprotokoll.dialogs.PainDialog;
import com.epatientenprotokoll.epatientenprotokoll.dialogs.PulsoxDialog;
import com.epatientenprotokoll.epatientenprotokoll.dialogs.TemperatureDialog;

import java.lang.reflect.Field;

public class ValueToolBox extends Toolbox {

    Activity activity;

    public ValueToolBox(Activity a){
        this.activity = a;
    }

    /**
     * Shows the toolbox for the user and forces the Android OS to show icons with reflections.
     * @param v
     */
    @Override
    public void showToolbox(View v, int menu){
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(menu, popup.getMenu());

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

    protected void getMeasureTool(MenuItem m){
        int toolId = -1;
        switch(m.getItemId()){
            case R.id.tool_breath_frequency:
                toolId = 8;
                new BreathFrequencyDialog(activity, R.layout.dialog_breath_frequency);
                break;
            case R.id.tool_pulsox:
                toolId = 9;
                new PulsoxDialog(activity, R.layout.dialog_pulsox);
                break;
            case R.id.tool_co2:
                toolId = 10;
                new Co2Dialog(activity, R.layout.dialog_co2);
                break;
            case R.id.tool_hgt:
                toolId = 11;
                new HgtDialog(activity, R.layout.dialog_hgt);
                break;
            case R.id.tool_pain:
                toolId = 12;
                new PainDialog(activity, R.layout.dialog_pain);
                break;
            case R.id.tool_ekg:
                toolId = 13;
                break;
            case R.id.tool_venous_canula:
                toolId = 14;
                break;
            case R.id.tool_nacl:
                toolId = 15;
                break;
            case R.id.tool_temperature:
                toolId = 16;
                new TemperatureDialog(activity, R.layout.dialog_temperature);
                break;
        }
        setTool(toolId);
    }
}
