package com.epatientenprotokoll.epatientenprotokoll.toolbox;

import android.app.Activity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.epatientenprotokoll.epatientenprotokoll.R;
import com.epatientenprotokoll.epatientenprotokoll.dialogs.AdrenalinDialog;
import com.epatientenprotokoll.epatientenprotokoll.dialogs.FentanylDialog;
import com.epatientenprotokoll.epatientenprotokoll.dialogs.GlucoseDialog;

import java.lang.reflect.Field;

public class DrugToolBox extends Toolbox {

    Activity activity;

    public DrugToolBox(Activity a){
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
            case R.id.tool_fentanyl:
                toolId = 18;
                new FentanylDialog(activity, R.layout.dialog_fentanyl);
                break;
            case R.id.tool_adrenalin:
                toolId = 19;
                new AdrenalinDialog(activity, R.layout.dialog_adrenalin);
                break;
            case R.id.tool_glucose:
                toolId = 20;
                new GlucoseDialog(activity, R.layout.dialog_glucose);
                break;
        }
        setTool(toolId);
    }
}
