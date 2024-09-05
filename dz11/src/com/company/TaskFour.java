package com.company;

import com.company.factories.ProjectorFactory;
import com.company.models.Projector;
import com.company.util.RandomElements;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class TaskFour {
    public static void main(String[] args) {
        List<Projector> projectors = Stream.generate(ProjectorFactory::getProjector).limit(10).toList();
        projectors.forEach(System.out::println);

        String producer = RandomElements.getRandomElement(ProjectorFactory.producers);
        System.out.println("All projectors of " + producer + ": ");
        projectors.stream().filter(projector -> projector.getProducer().equals(producer)).forEach(System.out::println);

        int thisYear = LocalDate.now().getYear();
        System.out.println("All projectors of " + thisYear + ": ");
        projectors.stream().filter(projector -> projector.getYearOfProduction() == thisYear).forEach(System.out::println);

        try (Scanner scanner = new Scanner(System.in)) {
            int price;
            do {
                System.out.print("Enter the price of the projector: ");
                price = scanner.nextInt();
            } while(price < 0);
            int finalPrice = price;
            projectors.stream().filter(projector -> projector.getPrice() > finalPrice).forEach(System.out::println);
        }

        System.out.println("Projectors sort ascending by price: ");
        projectors.stream()
                .sorted(Comparator.comparingDouble(Projector::getPrice))
                .forEach(System.out::println);

        System.out.println("Projectors sort descending by price: ");
        projectors.stream()
                .sorted(Comparator.comparingDouble(Projector::getPrice).reversed())
                .forEach(System.out::println);

        System.out.println("Projectors sort ascending by production year: ");
        projectors.stream()
                .sorted(Comparator.comparingDouble(Projector::getYearOfProduction))
                .forEach(System.out::println);

        System.out.println("Projectors sort descending by production year: ");
        projectors.stream()
                .sorted(Comparator.comparingDouble(Projector::getYearOfProduction).reversed())
                .forEach(System.out::println);
    }
}
