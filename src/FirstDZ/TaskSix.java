package FirstDZ;

import java.util.Scanner;

public class TaskSix {
    public static final double METERS_TO_MILES = 0.000621371;
    public static final double METERS_TO_INCHES = 39.3701;
    public static final double METERS_TO_YARDS = 1.09361;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double meters;
        do{
            System.out.print("Enter number of meters (must be zero or positive): ");
            meters = scanner.nextDouble();
        }while(meters < 0);
        int choice;
        do{
            System.out.print("Convert to miles - 1\n" +
                    "Convert to inches - 2\n" +
                    "Convert to yards - 3\n" +
                    "Exit - 0\n" +
                    "Enter choice: ");
            choice = scanner.nextInt();
            if(choice == 1){
                System.out.println("Result: " + METERS_TO_MILES * meters + " miles");
            } else if(choice == 2){
                System.out.println("Result: " + METERS_TO_INCHES * meters + " inches");
            } else if(choice == 3){
                System.out.println("Result: " + METERS_TO_YARDS * meters + " yards");
            } else if(choice == 0){
                System.out.println("Goodbye!");
            } else {
                System.out.println("Wrong input");
            }
        }while(choice != 0);
    }
}
