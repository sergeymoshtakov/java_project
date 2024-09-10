package com.company;

import com.company.models.RaceCarRunnable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class Race {
    public static AtomicLong startRaceTime;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(4);

        List<RaceCarRunnable> cars = new ArrayList<>();
        cars.add(new RaceCarRunnable("Ferrari", 9, 100, latch));
        cars.add(new RaceCarRunnable("BMW", 10, 100, latch));
        cars.add(new RaceCarRunnable("Honda", 11, 100, latch));
        cars.add(new RaceCarRunnable("Audi", 12, 100, latch));

        List<Thread> threads = new ArrayList<>();
        for (RaceCarRunnable car : cars) {
            Thread thread = new Thread(car);
            threads.add(thread);
        }

        startRace(threads);
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        RaceCarRunnable winner = cars.stream().min(Comparator.comparing(RaceCarRunnable::getFinishTime)).get();
        System.out.println("The winner is " + winner.getName());
    }

    public static void startRace(List<Thread> cars){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 3;
                try {
                    while (i > 0) {
                        Thread.sleep(500);
                        System.out.println(i + "...");
                        i--;
                    }
                    Thread.sleep(500);
                    System.out.println("GO!!!");
                    startRaceTime = new AtomicLong(System.currentTimeMillis());
                    for (Thread thread : cars) {
                        thread.start();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }
}
