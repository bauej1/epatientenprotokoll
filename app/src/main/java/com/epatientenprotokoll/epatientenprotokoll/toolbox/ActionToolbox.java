package com.epatientenprotokoll.epatientenprotokoll.toolbox;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import com.epatientenprotokoll.epatientenprotokoll.R;
import java.lang.reflect.Field;

public class ActionToolbox extends Toolbox {

    public ActionToolbox(){

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
        setTool(toolId);
    }
}
