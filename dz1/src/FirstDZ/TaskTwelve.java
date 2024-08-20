package FirstDZ;

import java.util.Random;
import java.util.Scanner;

public class TaskTwelve {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = 20;
        Random rand = new Random();
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100 + 10) - 10;
        }
        System.out.println("Array before sorting:");
        printArray(array);

        int choice;
        boolean howToSort;

        do{
            System.out.print("Ascending - 1\n" +
                    "Descending - 2\n" +
                    "Please enter how to sort at array: ");
            choice = scanner.nextInt();
        } while(choice < 1 || choice > 2);

        howToSort = choice == 1;

        sort(array, howToSort);

        System.out.println("Array after sorting:");
        printArray(array);
    }

    public static void printArray(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void sort(int[] array, boolean isAscending){
        int i, j, temp;
        boolean isSwapped;
        for (i = 0; i < array.length - 1; i++) {
            isSwapped = false;
            for (j = 0; j < array.length  - i - 1; j++) {
                if(isAscending){
                    if (array[j] > array[j+1]){
                        temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        isSwapped = true;
                    }
                } else {
                    if (array[j] < array[j+1]){
                        temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        isSwapped = true;
                    }
                }
            }
            if (!isSwapped){
                break;
            }
        }
    }
}
