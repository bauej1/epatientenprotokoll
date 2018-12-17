package com.epatientenprotokoll.epatientenprotokoll.toolbox;

import android.drm.DrmStore;
import android.view.View;

import com.epatientenprotokoll.epatientenprotokoll.model.ActionMeasurement;
import com.epatientenprotokoll.epatientenprotokoll.model.Measurement;
import com.epatientenprotokoll.epatientenprotokoll.model.Tool;

public class Toolbox {

    public Toolbox(){

    }

    /**
     * Shows the toolbox for the user and forces the Android OS to show icons with reflections.
     * @param v
     */
    public void showToolbox(View v, int menu){}

    protected void setTool(int id){
        Measurement tool = Tool.getInstance().getToolById(id);
        boolean intubationStatus = Tool.getInstance().getIntubationStatus();
        boolean overStepToolSetting = false;

        if(tool.getId() == 2 && intubationStatus || tool.getId() == 3 && !intubationStatus) {
            overStepToolSetting = true;
        }
        if(!overStepToolSetting){
            Tool.getInstance().setCurrentTool(tool);
        }

        if(tool.getId() == 2){
            Tool.getInstance().setIntubationStatus(true);
        } else if (tool.getId() == 3){
            Tool.getInstance().setIntubationStatus(false);
        }
    }
}
