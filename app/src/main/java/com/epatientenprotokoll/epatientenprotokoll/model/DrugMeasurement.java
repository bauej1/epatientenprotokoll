package com.epatientenprotokoll.epatientenprotokoll.model;

public class DrugMeasurement extends Measurement {

    private double value;
    private String unit;
    private String drugName;

    public DrugMeasurement(int id, int origin, boolean multimeasure, double value, String unit, String drugName, int x1, int y1, int x2, int y2){
        super(id, origin, multimeasure, x1, y1, x2, y2);
        this.unit = unit;
        this.drugName = drugName;
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

    @Override
    public String getDrugName() {
        return drugName;
    }

    @Override
    public void setDrugName(String name) {
        this.drugName = name;
    }
}
