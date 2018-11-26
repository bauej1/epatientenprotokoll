package com.epatientenprotokoll.epatientenprotokoll.model;

/**
 * This Class represents the different Measurements which can used in the grid.
 *
 */
public class Measurement {

    private int id;
    private int symbol;
    private boolean multiMeasure;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    /**
     *
     * @param id - Identification of the Measurement
     * @param symbol - Symbol used in the grid as an integer from drawables folder
     * @param multiMeasure - is the Measurement just a point or does it need a drag action
     * @param x1 - x-value of the first click
     * @param y1 - y-value of the second click
     * @param x2 - x-value of the second click (only if multiMeasure is true)
     * @param y2 - y-value of the second click (only if multiMeasure is true)
     */
    public Measurement(int id, int symbol, boolean multiMeasure, int x1, int y1, int x2, int y2) {
        this.id = id;
        this.symbol = symbol;
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

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
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
}
