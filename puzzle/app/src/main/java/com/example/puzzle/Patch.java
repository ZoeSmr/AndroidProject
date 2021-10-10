package com.example.puzzle;

public class Patch {
    private boolean empty = false;
    private Point canvasPoint;
    private Point bitmapPoint;

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public Point getCanvasPoint() {
        return canvasPoint;
    }

    public void setCanvasPoint(Point canvasPoint) {
        this.canvasPoint = canvasPoint;
    }

    public Point getBitmapPoint() {
        return bitmapPoint;
    }

    public void setBitmapPoint(Point bitmapPoint) {
        this.bitmapPoint = bitmapPoint;
    }
}
