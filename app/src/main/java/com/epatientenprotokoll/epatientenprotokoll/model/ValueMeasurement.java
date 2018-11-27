package com.epatientenprotokoll.epatientenprotokoll.model;

public class ValueMeasurement extends Measurement {

    public ValueMeasurement(int id, int origin, boolean multimeasure, double value, int x1, int y1, int x2, int y2){
        super(id, origin, multimeasure, value, x1, y1, x2, y2);
    }
}
