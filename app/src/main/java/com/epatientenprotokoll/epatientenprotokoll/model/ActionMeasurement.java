package com.epatientenprotokoll.epatientenprotokoll.model;

public class ActionMeasurement extends Measurement {

    private Integer symbol;

    public ActionMeasurement(int id, int origin, boolean multimeasure, Integer symbol, int x1, int y1, int x2, int y2) {
        super(id, origin, multimeasure, x1, y1, x2, y2);
        this.symbol = symbol;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    @Override
    public Integer getStoredValue(){
        return symbol;
    }

    @Override
    public void setStoredValue(Object o){
        this.symbol = (Integer) o;
    }
}
