package com.epatientenprotokoll.epatientenprotokoll.model;

/**
 * This Class represents the different Measurements which can used in the grid.
 *
 */
public class Measurement {

    /**
     * id - the identification of the measurement method
     * origin - the origin of the mesurement {1 = Action, 2 = Value, 3 = Drug}
     * multiMeasure - if the measurement is more than a point in the grid -> drag
     * x1 - the x position of the element
     * y1 - the y position of the element
     * x2 - (only when multiMeasure = true) --> x Position of the end
     * y2 - (only when multiMeasure = true) --> y Position of the end
     */
    private int id;
    private int origin;
    private boolean multiMeasure;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Measurement(int id, int origin, boolean multiMeasure, int x1, int y1, int x2, int y2){
        this.id = id;
        this.origin = origin;
        this.multiMeasure = multiMeasure;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public boolean isMultiMeasure() {
        return multiMeasure;
    }

    public void setMultiMeasure(boolean multiMeasure) {
        this.multiMeasure = multiMeasure;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Object getStoredValue(){
        return "";
    }

    public void setStoredValue(Object o){
    }
}


