package com.company;

import com.company.enums.Category;
import com.company.factories.ProductFactory;
import com.company.models.Product;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class TaskTwo {
    public static void main(String[] args) {
        List<Product> products = Stream.generate(ProductFactory::createProduct)
                .limit(10).toList();
        products.stream().forEach(System.out::println);
        System.out.println("Names longer than five: ");
        products.stream().filter(n -> n.getName().length() < 5).forEach(System.out::println);

        try(Scanner scanner = new Scanner(System.in)) {
            String name;
            System.out.print("Enter the name of the product: ");
            name = scanner.nextLine();
            long numberOfProducts = products.stream().filter(n -> n.getName().equalsIgnoreCase(name)).count();
            System.out.printf("Number of products %s: %d\n", name, numberOfProducts);
            char letter;
            System.out.print("Enter your letter: ");
            letter = scanner.next().charAt(0);
            products.stream().filter(n -> n.getName().charAt(0) == letter).forEach(System.out::println);
        }
        System.out.println("All products with milk category: ");
        products.stream().filter(n -> n.getCategory().equals(Category.MILK)).forEach(System.out::println);
    }
}
