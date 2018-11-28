package com.epatientenprotokoll.epatientenprotokoll.model;

public class ActionMeasurement extends Measurement {

    private int symbol;

    public ActionMeasurement(int id, int origin, boolean multimeasure, double value, int symbol, int x1, int y1, int x2, int y2) {
        super(id, origin, multimeasure, value, x1, y1, x2, y2);
        this.symbol = symbol;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }
}
