package FirstDZ;

import java.util.Scanner;

public class TaskThree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numbers = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            numbers[i] = sc.nextInt();
        }
        int result = 0;
        for (int i = 0; i < 3; i++) {
            result += numbers[i] * (int) Math.pow(10, 2-i);
        }
        System.out.println("The result is : " + result);
    }
}
