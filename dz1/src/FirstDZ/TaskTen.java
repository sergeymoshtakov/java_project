package FirstDZ;

import java.util.Random;

public class TaskTen {
    public static void main(String[] args){
        Random rand = new Random();
        int length = 20;
        int[] array = new int[length];

        int numberOfEvens = 0;
        int numberOfOdds = 0;
        int numberOfPositives = 0;
        int numberOfNegatives = 0;

        for(int i = 0; i < length; i++){
            array[i] = rand.nextInt(100 + 10) - 10;
            System.out.print(array[i] + " ");
            if(array[i] % 2 == 0){
                numberOfEvens++;
            }
            if(array[i] % 2 != 0){
                numberOfOdds++;
            }
            if(array[i] > 0){
                numberOfPositives++;
            }
            if(array[i] < 0){
                numberOfNegatives++;
            }
        }

        System.out.println();

        int[] evens = new int[numberOfEvens];
        int[] odds = new int[numberOfOdds];
        int[] positives = new int[numberOfPositives];
        int[] negatives = new int[numberOfNegatives];

        int indexOfEvens = 0;
        int indexOfOdds = 0;
        int indexOfPositives = 0;
        int indexOfNegatives = 0;

        for(int i = 0; i < length; i++){
            if(array[i] % 2 == 0){
                evens[indexOfEvens] = array[i];
                indexOfEvens++;
            }
            if(array[i] % 2 != 0){
                odds[indexOfOdds] = array[i];
                indexOfOdds++;
            }
            if(array[i] > 0){
                positives[indexOfPositives] = array[i];
                indexOfPositives++;
            }
            if(array[i] < 0){
                negatives[indexOfNegatives] = array[i];
                indexOfNegatives++;
            }
        }

        System.out.println("Evens :");
        for(int i = 0; i < evens.length; i++){
            System.out.print(evens[i] + " ");
        }

        System.out.println("\nOdds :");
        for(int i = 0; i < odds.length; i++){
            System.out.print(odds[i] + " ");
        }

        System.out.println("\nPositives :");
        for(int i = 0; i < positives.length; i++){
            System.out.print(positives[i] + " ");
        }

        System.out.println("\nNegatives :");
        for(int i = 0; i < negatives.length; i++){
            System.out.print(negatives[i] + " ");
        }
    }
}
