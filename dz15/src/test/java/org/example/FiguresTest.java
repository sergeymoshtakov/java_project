package org.example;

import org.example.models.figures.Rectangle;
import org.example.models.figures.Rhombus;
import org.example.models.figures.Square;
import org.example.models.figures.Triangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FiguresTest {
    @Test
    public void testTriangleArea() {
        Triangle triangle = new Triangle(5, 10);
        assertEquals(25, triangle.getArea(), 0.001);
    }

    @Test
    public void testRectangleArea() {
        Rectangle rectangle = new Rectangle(4, 5);
        assertEquals(20, rectangle.getArea(), 0.001);
    }

    @Test
    public void testSquareArea() {
        Square square = new Square(4);
        assertEquals(16, square.getArea(), 0.001);
    }

    @Test
    public void testRhombusArea() {
        Rhombus rhombus = new Rhombus(6, 8);
        assertEquals(24, rhombus.getArea(), 0.001);
    }
}
