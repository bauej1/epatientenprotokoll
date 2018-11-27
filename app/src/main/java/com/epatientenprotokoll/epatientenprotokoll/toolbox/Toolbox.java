package com.epatientenprotokoll.epatientenprotokoll.toolbox;

import android.view.View;

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
        Tool.getInstance().setCurrentTool(tool);
    }
}
