package org.example.models.figures;

import org.example.interfaces.IFigure;

public class Triangle implements IFigure {
    double height;
    double width;
    public Triangle(double height, double width) {
        this.height = height;
        this.width = width;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    @Override
    public double getArea() {
        return this.width * this.height / 2;
    }
}
