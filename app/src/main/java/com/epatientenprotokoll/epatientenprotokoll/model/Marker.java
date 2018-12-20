package com.epatientenprotokoll.epatientenprotokoll.model;

import android.graphics.drawable.Drawable;

public class Marker {

    private int id;
    private int x;
    private int y;
    private Drawable marker;

    public Marker(int id, int x, int y, Drawable marker) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.marker = marker;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Drawable getMarker() {
        return marker;
    }

    public void setMarker(Drawable marker) {
        this.marker = marker;
    }
}
