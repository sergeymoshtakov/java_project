package FirstDZ;

import java.util.Random;

public class TaskNine {
    public static void main(String[] args) {
        Random rand = new Random();
        int length = 20;
        int[] array = new int[length];
        int numberOfPositives = 0;
        int numberOfNegatives = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100 + 10) - 10;
            System.out.print(array[i] + " ");
            if(array[i] < min){
                min = array[i];
            }
            if(array[i] > max){
                max = array[i];
            }
            if (array[i] > 0) {
                numberOfPositives++;
            }
            if (array[i] < 0) {
                numberOfNegatives++;
            }
        }
        System.out.println();
        int numberOfZeros = length - (numberOfPositives + numberOfNegatives);
        System.out.printf("Max number: %d\n", max);
        System.out.printf("Min number: %d\n", min);
        System.out.printf("Number of zeros: %d\n", numberOfZeros);
        System.out.printf("Number of positives: %d\n", numberOfPositives);
        System.out.printf("Number of negatives: %d\n", numberOfNegatives);
    }
}
