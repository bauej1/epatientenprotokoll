package com.epatientenprotokoll.epatientenprotokoll.model;

import com.epatientenprotokoll.epatientenprotokoll.R;

import java.util.ArrayList;

public class Tool {

    private static Tool instance = new Tool();
    ArrayList<Measurement> measurements = new ArrayList<>();
    private Measurement currentTool;

    private Tool(){
    }

    public static Tool getInstance(){
        return instance;
    }

    public Measurement getCurrentTool(){
        return currentTool;
    }

    public void setCurrentTool(Measurement m){
        currentTool = m;
    }

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
        Measurement ventilation = new Measurement(1, R.drawable.ventilation, true, 0, 0, 0, 0);
        Measurement inextubation = new Measurement(2, R.drawable.intubation_extubation, false, 0, 0, 0, 0);
        Measurement defibrilation = new Measurement(3, R.drawable.defibrilation, false, 0, 0, 0, 0);
        Measurement extPacing = new Measurement(4, R.drawable.pacing, false, 0, 0, 0, 0);
        Measurement heartMassage = new Measurement(5, R.drawable.heart_massage, false, 0, 0, 0, 0);
        Measurement bloodPressure = new Measurement(6, R.drawable.heart_massage, true, 0, 0, 0, 0);
        Measurement pulse = new Measurement(7, R.drawable.pulse, false, 0, 0, 0, 0);
        Measurement breath_frequency = new Measurement(8, R.drawable.measurevalues, false, 0, 0, 0, 0);
        Measurement pulsox = new Measurement(9, R.drawable.measurevalues, false, 0, 0, 0, 0);
        Measurement co2 = new Measurement(10, R.drawable.measurevalues, false, 0, 0, 0, 0);
        Measurement hgt = new Measurement(11, R.drawable.measurevalues, false, 0, 0, 0, 0);
        Measurement pain = new Measurement(12, R.drawable.measurevalues, false, 0, 0, 0, 0);
        Measurement ekg = new Measurement(13, R.drawable.measurevalues, false, 0, 0, 0, 0);
        Measurement venous_canule = new Measurement(14, R.drawable.measurevalues, false, 0, 0, 0, 0);
        Measurement nacl = new Measurement(15, R.drawable.measurevalues, false, 0, 0, 0, 0);
        Measurement temp = new Measurement(16, R.drawable.measurevalues, false, 0, 0, 0, 0);

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
