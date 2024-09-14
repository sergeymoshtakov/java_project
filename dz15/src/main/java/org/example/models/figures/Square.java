package org.example.models.figures;

import org.example.interfaces.IFigure;

public class Square implements IFigure {
    double side;
    public Square(double side) {
        this.side = side;
    }
    public double getSide() {
        return side;
    }
    public void setSide(double side) {
        this.side = side;
    }
    @Override
    public double getArea() {
        return this.side * this.side;
    }
}
