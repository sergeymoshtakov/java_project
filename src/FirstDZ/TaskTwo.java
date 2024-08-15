package FirstDZ;

import java.util.Scanner;

public class TaskTwo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        double a = sc.nextDouble();
        double b;
        do{
            System.out.print("Enter percent: ");
            b = sc.nextDouble();
        }while(b < 0 || b > 100);
        System.out.println("The result is: " + (a*b/100));
    }
}
