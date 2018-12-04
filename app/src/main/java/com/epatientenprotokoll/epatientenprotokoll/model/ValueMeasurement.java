package com.epatientenprotokoll.epatientenprotokoll.model;

public class ValueMeasurement extends Measurement {

    private double value;
    private String unit;

    public ValueMeasurement(int id, int origin, boolean multimeasure, double value, String unit, int x1, int y1, int x2, int y2){
        super(id, origin, multimeasure, x1, y1, x2, y2);
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public Object getStoredValue(){
        return value;
    }

    @Override
    public void setStoredValue(Object o){
        this.value = (double) o;
    }

    @Override
    public String getUnit(){
        return unit;
    }

    @Override
    public void setUnit(String unit){
        this.unit = unit;
    }
}
