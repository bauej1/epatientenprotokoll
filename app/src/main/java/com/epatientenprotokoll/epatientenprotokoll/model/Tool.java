package com.epatientenprotokoll.epatientenprotokoll.model;

import com.epatientenprotokoll.epatientenprotokoll.R;
import java.util.ArrayList;

/**
 * This class represents a singleton. It handles all interactions with the measurement model and which tool is currently selected.
 */
public class Tool {

    private static Tool instance = new Tool();
    ArrayList<Measurement> measurements = new ArrayList<>();
    private Measurement currentTool;

    private Tool(){
    }

    /**
     * Returns the only instance of Tool Class.
     * @return Tool
     */
    public static Tool getInstance(){
        return instance;
    }

    /**
     * Returns the currently selected Tool.
     * @return Measurement - the currently selected Tool
     */
    public Measurement getCurrentTool(){
        return currentTool;
    }

    /**
     * Sets the new selected Tool.
     * @param m - the selected tool
     */
    public void setCurrentTool(Measurement m){
        currentTool = m;
    }

    /**
     * Returns the measurement by its id.
     * @param id
     * @return
     */
    public Measurement getToolById(int id){
        for(Measurement m : measurements){
            if(m.getId() == id) return m;
        }
        return null;
    }

    /**
     * Checks if a tool is selected before drawing on the grid.
     * @return true if a tool is selected
     */
    public boolean checkIfToolIsSelected(){
        return currentTool == null ? false : true;
    }

    public void initMeasurements(){
        Measurement ventilation = new ActionMeasurement(1, 1, true, R.drawable.ventilation, 0, 0, 0, 0);
        Measurement inextubation = new ActionMeasurement(2, 1, false, R.drawable.intubation_extubation, 0, 0, 0, 0);
        Measurement defibrilation = new ActionMeasurement(3, 1, false, R.drawable.defibrilation, 0, 0, 0, 0);
        Measurement extPacing = new ActionMeasurement(4, 1, false, R.drawable.pacing, 0, 0, 0, 0);
        Measurement heartMassage = new ActionMeasurement(5, 1, true, R.drawable.heart_massage, 0, 0, 0, 0);
        Measurement bloodPressure = new ActionMeasurement(6, 1, true, R.drawable.heart_massage, 0, 0, 0, 0);
        Measurement pulse = new ActionMeasurement(7, 1, false, R.drawable.pulse, 0, 0, 0, 0);

        Measurement breath_frequency = new ValueMeasurement(8, 2, false, 0.0, "/min", 0,0,0,0);
        Measurement pulsox = new ValueMeasurement(9, 2, false, 0.0, "%", 0,0,0,0);
        Measurement co2 = new ValueMeasurement(10, 2, false, 0.0, "mmHg", 0,0,0,0);
        Measurement hgt = new ValueMeasurement(11, 2, false, 0.0, "mmol/l", 0,0,0,0);
        Measurement pain = new ValueMeasurement(12, 2, false, 0.0, "", 0,0,0,0);
        Measurement ekg = new ValueMeasurement(13, 2, false, 0.0, "", 0,0,0,0);
        Measurement venous_canule = new ValueMeasurement(14, 2, false, 0.0, "", 0,0,0,0);
        Measurement nacl = new ValueMeasurement(15, 2, false, 0.0, "", 0,0,0,0);
        Measurement temp = new ValueMeasurement(16, 2, false, 0.0, "°C", 0,0,0,0);

        measurements.add(ventilation);
        measurements.add(inextubation);
        measurements.add(defibrilation);
        measurements.add(extPacing);
        measurements.add(heartMassage);
        measurements.add(bloodPressure);
        measurements.add(pulse);
        measurements.add(temp);
        measurements.add(breath_frequency);
        measurements.add(pulsox);
        measurements.add(co2);
        measurements.add(hgt);
        measurements.add(pain);
        measurements.add(ekg);
        measurements.add(venous_canule);
        measurements.add(nacl);
    }
}
