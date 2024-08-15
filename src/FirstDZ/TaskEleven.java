package FirstDZ;

import java.util.Scanner;

public class TaskEleven {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int length;
        int direction;
        char symbol;

        do{
            System.out.print("Enter length (must be zero or positive): ");
            length = sc.nextInt();
        } while(length < 0);

        do{
            System.out.print("Horizontal - 1\n" +
                    "Vertical - 2\n" +
                    "Enter direction: ");
            direction = sc.nextInt();
        } while(direction < 1 || direction > 2);

        System.out.print("Enter symbol: ");
        symbol = sc.next().charAt(0);

        for(int i = 0; i < length; i++){
            if(direction == 1){
                System.out.print(symbol);
            } else {
                System.out.println(symbol);
            }
        }
    }
}
