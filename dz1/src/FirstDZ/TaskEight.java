package FirstDZ;

import java.util.Scanner;

public class TaskEight {
    public static void main(String[] args){
        int firstNumber;
        int secondNumber;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first number: ");
        firstNumber = scanner.nextInt();
        System.out.print("Enter second number: ");
        secondNumber = scanner.nextInt();
        if(firstNumber > secondNumber){
            int temp = firstNumber;
            firstNumber = secondNumber;
            secondNumber = temp;
        }
        for(int i = firstNumber; i <= secondNumber; i++){
            for(int j = 1; j <= 10; j++){
                System.out.printf("%d*%d = %d ", i, j, i*j);
            }
            System.out.println();
        }
    }
}
