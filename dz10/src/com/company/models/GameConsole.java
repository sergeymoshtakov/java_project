package com.company.models;

import com.company.enums.Color;
import com.company.enums.Producer;
import com.company.exceptions.ConsoleShutdownException;
import com.company.interfaces.Powered;

public class GameConsole implements Powered {
    private final Producer brand;
    private String model;
    private final String serial;
    private GamePad firstGamePad;
    private GamePad secondGamePad;
    private boolean isOn;
    private Game activeGame;
    private int waitingCounter = 0;

    public GameConsole(Producer brand, String serial){
        this.brand = brand;
        this.serial = serial;
        this.firstGamePad = new GamePad(brand, 1);
        this.secondGamePad = new GamePad(brand, 2);
    }

    public void loadGame(Game game){
        this.activeGame = game;
        System.out.printf("Игра %s загружается\n", game.getName());
    }

    public void playGame() {
        try {
            checkStatus();

            if (this.activeGame == null) {
                System.out.println("Нет загруженной игры.");
                return;
            }

            System.out.printf("Играем в %s%n", this.activeGame.getName());

            if (this.firstGamePad != null && this.firstGamePad.isOn()) {
                double newChargeLevel = this.firstGamePad.getChargeLevel() - 10.0;
                this.firstGamePad.setChargeLevel(newChargeLevel);
                System.out.printf("Заряд первого джойстика: %.2f%%\n", this.firstGamePad.getChargeLevel());

                if (newChargeLevel <= 0) {
                    this.firstGamePad.powerOff();
                    System.out.println("Первый джойстик выключен из-за низкого заряда.");
                }
            }

            if (this.secondGamePad != null && this.secondGamePad.isOn()) {
                double newChargeLevel = this.secondGamePad.getChargeLevel() - 10.0;
                this.secondGamePad.setChargeLevel(newChargeLevel);
                System.out.printf("Заряд второго джойстика: %.2f%%\n", this.secondGamePad.getChargeLevel());

                if (newChargeLevel <= 0) {
                    this.secondGamePad.powerOff();
                    System.out.println("Второй джойстик выключен из-за низкого заряда.");
                }
            }
        } catch (ConsoleShutdownException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkStatus() throws ConsoleShutdownException {
        if ((this.firstGamePad == null || !this.firstGamePad.isOn()) &&
                (this.secondGamePad == null || !this.secondGamePad.isOn())) {
            waitingCounter++;
            System.out.println("Подключите джойстик. Ожидание: " + waitingCounter);

            if (waitingCounter > 5) {
                this.powerOff();
                throw new ConsoleShutdownException("Приставка завершает работу из-за отсутствия активности");
            }
        } else {
            waitingCounter = 0;
        }
    }

    @Override
    public void powerOn() {
        if (!this.isOn) {
            this.isOn = true;
            System.out.println("Console powered on.");
        }
    }

    @Override
    public void powerOff() {
        if (this.isOn) {
            this.isOn = false;
            System.out.println("Console powered off.");
        }
    }

    class GamePad implements Powered {
        private final Producer brand;
        private final String consoleSerial;
        private int connectedNumber;
        private Color color;
        private double chargeLevel = 100.0;
        private boolean isOn;

        public GamePad(Producer brand, int connectedNumber){
            this.brand = brand;
            this.connectedNumber = connectedNumber;
            this.consoleSerial = serial;
        }

        public Producer getBrand(){
            return this.brand;
        }
        public String getConsoleSerial(){
            return this.consoleSerial;
        }
        public int getConnectedNumber(){
            return this.connectedNumber;
        }
        public void setConnectedNumber(int connectedNumber){
            this.connectedNumber = connectedNumber;
        }
        public Color getColor(){
            return this.color;
        }
        public void setColor(Color color){
            this.color = color;
        }
        public double getChargeLevel(){
            return this.chargeLevel;
        }
        public void setChargeLevel(double chargeLevel){
            this.chargeLevel = chargeLevel;
        }
        public boolean isOn(){
            return this.isOn;
        }
        public void setOn(boolean on){
            this.isOn = on;
        }

        @Override
        public void powerOn() {
            if (!this.isOn) {
                this.isOn = true;
                System.out.println("Gamepad " + this.connectedNumber + " powered on.");
                if (!GameConsole.this.isOn()) {
                    GameConsole.this.powerOn();
                }
            }
        }

        @Override
        public void powerOff() {
            if (this.isOn) {
                this.isOn = false;
                System.out.println("Gamepad " + this.connectedNumber + " powered off.");

                if (this.connectedNumber == 1 && GameConsole.this.secondGamePad != null) {
                    System.out.println("Making second gamepad the first.");
                    GameConsole.this.secondGamePad.setConnectedNumber(1);
                    GameConsole.this.firstGamePad = GameConsole.this.secondGamePad;
                    GameConsole.this.secondGamePad = null;
                }
            }
        }
    }

    public Producer getBrand(){
        return this.brand;
    }
    public String getModel(){
        return this.model;
    }
    public void setModel(String model){
        this.model = model;
    }
    public String getSerial(){
        return this.serial;
    }
    public GamePad getFirstGamePad(){
        return this.firstGamePad;
    }
    public void setFirstGamePad(GamePad firstGamePad){
        this.firstGamePad = firstGamePad;
    }
    public GamePad getSecondGamePad(){
        return this.secondGamePad;
    }
    public void setSecondGamePad(GamePad secondGamePad){
        this.secondGamePad = secondGamePad;
    }
    public boolean isOn(){
        return this.isOn;
    }
    public void setOn(boolean on){
        this.isOn = on;
    }

}
