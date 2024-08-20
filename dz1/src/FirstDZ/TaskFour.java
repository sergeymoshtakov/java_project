package FirstDZ;

import java.util.Scanner;

public class TaskFour {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number;
        do{
            System.out.print("Enter 6 digit number: ");
            number = sc.nextInt();
        }while(number < 99999 || number > 999999);
        int lastNumber = number%10;
        int secondLastNumber = (number%100)/10;
        int firstNumber = number/100000;
        int secondNumber = (number/10000)%10;
        number -= firstNumber * 100000;
        number += lastNumber * 100000;
        number -= secondNumber * 10000;
        number += secondLastNumber * 10000;
        number -= secondLastNumber * 10;
        number += secondNumber * 10;
        number -= lastNumber;
        number += firstNumber;
        System.out.println("Result: " + number);
    }
}
