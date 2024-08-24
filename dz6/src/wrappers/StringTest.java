package wrappers;

import java.util.Scanner;

public class StringTest {
    public static void main(String[] args) {
        // 2
        String s1 = "Hello, World!";
        String s2 = new String("Hello, World!");
        char[] charArray = {'H', 'e', 'l', 'l', 'o', ',', ' ', 'W', 'o', 'r', 'l', 'd', '!'};
        String s3 = new String(charArray);
        byte[] byteArray = {72, 101, 108, 108, 111, 44, 32, 87, 111, 114, 108, 100, 33};
        String s4 = new String(byteArray);
        StringBuilder sb = new StringBuilder();
        sb.append("Hello, ").append("World!");
        String s5 = sb.toString();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);

        // 3
        String fruits = "Апельсин,Яблоко,Гранат,Груша";
        String[] fruitArray = fruits.split(",");

        String longestFruit = "";
        for (int i = 0; i < fruitArray.length; i++) {
            if(fruitArray[i].length() > longestFruit.length()) {
                longestFruit = fruitArray[i];
            }
        }
        System.out.println("Longest Fruit: " + longestFruit);
        for (int i = 0; i < fruitArray.length; i++) {
            String shortName = fruitArray[i].substring(0, 3);
            System.out.println("Сокращенное название: " + shortName);
        }

        String newString = "   Я_новая_строка      ";
        newString = newString.trim();
        newString = newString.replace('_', ' ');
        System.out.println(newString);

        String text;
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите текст: ");
        text = sc.nextLine();
        System.out.printf("Вы ввели: %s\n", text);

        if (text.startsWith("Запуск")) {
            System.out.println("Запускаем процесс");
        }

        if (text.endsWith("завершен")) {
            System.out.println("Процесс завершен");
        }

        if (text.toLowerCase().contains("ошибка")) {
            System.out.println("Произошла ошибка");
        }

        sc.close();

        // 4
        StringBuilder combinedStringBuilder = new StringBuilder();
        combinedStringBuilder.append(s1).append("\n")
                .append(s2).append("\n")
                .append(s3).append("\n")
                .append(s4).append("\n")
                .append(s5).append("\n")
                .append(fruits).append("\n")
                .append(longestFruit).append("\n")
                .append(newString).append("\n");
                combinedStringBuilder.reverse();

        String[] lines = combinedStringBuilder.toString().split("\n");
        combinedStringBuilder.setLength(0);

        for (int i = 0; i < lines.length; i++) {
            combinedStringBuilder.append(lines[i]);
            if (i % 3 == 0 && i != 0) {
                combinedStringBuilder.append("\n");
            }
        }

        String finalString = combinedStringBuilder.toString();
        System.out.println(finalString);
    }
}
