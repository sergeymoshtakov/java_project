package org.example;

import org.example.interfaces.IFigure;
import org.example.models.figures.Rectangle;
import org.example.models.figures.Rhombus;
import org.example.models.figures.Square;
import org.example.models.figures.Triangle;

import java.util.Scanner;

public class TaskOne {
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        IFigure figure;
        int choice;
        do {
            System.out.println("1 - Rectangle");
            System.out.println("2 - Rhombus");
            System.out.println("3 - Triangle");
            System.out.println("4 - Square");
            System.out.println("Choose figure: ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 4);

        double height, width, diagonal1, diagonal2, side;
        switch (choice) {
            case 1:
                System.out.println("Enter height: ");
                height = scanner.nextDouble();
                System.out.println("Enter width: ");
                width = scanner.nextDouble();
                figure = new Rectangle(height, width);
                break;
            case 2:
                System.out.println("Enter diagonal1: ");
                diagonal1 = scanner.nextDouble();
                System.out.println("Enter diagonal2: ");
                diagonal2 = scanner.nextDouble();
                figure = new Rhombus(diagonal1, diagonal2);
                break;
            case 3:
                System.out.println("Enter height: ");
                height = scanner.nextDouble();
                System.out.println("Enter width: ");
                width = scanner.nextDouble();
                figure = new Triangle(height, width);
                break;
            default:
                System.out.println("Enter side: ");
                side = scanner.nextDouble();
                figure = new Square(side);
        }

        System.out.println("The area of figure is: " + figure.getArea());
    }
}
