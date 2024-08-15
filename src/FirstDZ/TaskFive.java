package FirstDZ;

import java.util.Scanner;

public class TaskFive {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int numberOfMonth;
        do{
            System.out.print("Enter the month number : ");
            numberOfMonth = sc.nextInt();
            if(numberOfMonth < 0 || numberOfMonth > 12){
                System.out.println("Invalid month number");
            }
        }while(numberOfMonth < 0 || numberOfMonth > 12);

        if(numberOfMonth < 3 || numberOfMonth == 12){
            System.out.println("Winter");
        } else if(numberOfMonth < 6){
            System.out.println("Spring");
        } else if(numberOfMonth < 9){
            System.out.println("Summer");
        } else {
            System.out.println("Autumn");
        }
    }
}
