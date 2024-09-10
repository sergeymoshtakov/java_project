package com.company.models;

import com.company.Race;
import com.company.util.TimeFormatter;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RaceCarRunnable extends Car implements Runnable{
    private double passed;
    private double distance;
    private boolean isFinish;
    private CountDownLatch latch;
    private long finishTime;

    public RaceCarRunnable(String name, double maxSpeed, int distance, CountDownLatch latch) {
        super(name, maxSpeed);
        this.distance = distance;
        this.isFinish = false;
        this.passed = 0;
        this.latch = latch;
    }

    public void setPassed(double passed) {
        this.passed = passed;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public void setFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }
    public double getPassed() {
        return passed;
    }
    public double getDistance() {
        return distance;
    }
    public boolean isFinish() {
        return isFinish;
    }

    public double getRandomSpeed() {
        Random random = new Random();
        return random.nextDouble(this.getMaxSpeed() - getMaxSpeed()/2) + this.getMaxSpeed()/2;
    }

    public void printInfo(){
        System.out.printf("%s => speed: %.2f; progress: %.2f/%.2f%n", this.getName(), this.getRandomSpeed(), this.getPassed(), this.getDistance());
    }

    public void printFinished(){
        System.out.println(this.getName() + " FINISHED ! Time: " + TimeFormatter.convertToTime(this.getFinishTime()));
    }

    public long getFinishTime() {
        return finishTime;
    }

    @Override
    public void run() {
        while(!isFinish){
            try {
                Thread.sleep(1000);
                passed += getRandomSpeed();
                this.printInfo();
                if (passed >= distance){
                    this.finishTime = System.currentTimeMillis() - Race.startRaceTime.get();
                    isFinish = true;
                    latch.countDown();
                    this.printFinished();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
