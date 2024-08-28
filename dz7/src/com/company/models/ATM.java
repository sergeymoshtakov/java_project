package com.company.models;

import com.company.exceptions.ExceedsMaximumBanknotesException;
import com.company.exceptions.InsufficientFundsException;
import com.company.exceptions.MinimumSumException;
import com.company.exceptions.NotWalidNominalException;
import com.company.factories.ATMFactory;
import com.company.resources.Nominals;

import java.util.Scanner;

public class ATM {
    private BanknoteCarrier[] banknoteCarriers;
    private int minimumSum;
    private int maximumQuantity;

    public ATM(int minimumSum, int maximumQuantity, BanknoteCarrier[] banknoteCarriers) {
        this.setMinimumSum(minimumSum);
        this.setMaximumQuantity(maximumQuantity);
        this.setBanknoteCarriers(banknoteCarriers);
    }
    public ATM(ATM atm) {
        this.setMinimumSum(atm.getMinimumSum());
        this.setMaximumQuantity(atm.getMaximumQuantity());
        this.setBanknoteCarriers(atm.getBanknoteCarriers());
    }
    public ATM(int minimumSum, int maximumQuantity) {
        this(ATMFactory.getATM());
        this.setMinimumSum(minimumSum);
        this.setMaximumQuantity(maximumQuantity);
    }
    public ATM() {
        this(ATMFactory.getATM());
    }

    public int getTotalAmount() {
        int total = 0;
        for (int i = 0; i < banknoteCarriers.length; i++) {
            total += banknoteCarriers[i].getNominal() * banknoteCarriers[i].getQuantity();
        }
        return total;
    }

    public void addMoney(BanknoteCarrier[] addingBanknoteCarriers) throws Exception {
        if(addingBanknoteCarriers.length > this.maximumQuantity){
            throw new ExceedsMaximumBanknotesException();
        }
        for(int i = 0; i < banknoteCarriers.length; i++){
            for(int j = 0; j < addingBanknoteCarriers.length; j++){
                if(banknoteCarriers[i].getNominal() == addingBanknoteCarriers[j].getNominal()){
                    banknoteCarriers[i].setQuantity(banknoteCarriers[i].getQuantity() + addingBanknoteCarriers[j].getQuantity());
                }
            }
        }
    }

    public void addMoney(BanknoteCarrier addingBanknoteCarrier) throws Exception {
        if(addingBanknoteCarrier.getQuantity() > this.getMaximumQuantity()){
            throw new ExceedsMaximumBanknotesException();
        }
        for(int i = 0; i < banknoteCarriers.length; i++){
            if(addingBanknoteCarrier.getNominal() == banknoteCarriers[i].getNominal()){
                banknoteCarriers[i].setQuantity(banknoteCarriers[i].getQuantity() + addingBanknoteCarrier.getQuantity());
                break;
            }
        }
    }

    // метод ручного ввода какой-то суммы через купюро приемник
    public void depositMoney(Scanner scanner) {
        try {
            int depositAmount;
            do {
                System.out.printf("Enter number of banknotes you would like to deposit (max %d): ", getMaximumQuantity());
                depositAmount = scanner.nextInt();
            } while (depositAmount < 1 || depositAmount > getMaximumQuantity());

            while (depositAmount > 0) {
                int nominal;
                do {
                    System.out.print("Enter nominal of the banknote you would like to deposit: ");
                    nominal = scanner.nextInt();
                } while (!BanknoteCarrier.isBanknote(nominal));

                int quantity;
                do {
                    System.out.printf("Enter quantity of the %d UAH banknotes you would like to deposit: ", nominal);
                    quantity = scanner.nextInt();
                } while (quantity < 0 || quantity > depositAmount);

                depositAmount -= quantity;
                this.addMoney(new BanknoteCarrier(nominal, quantity));
            }
            System.out.println("Thank you for depositing money!");
        } catch (ExceedsMaximumBanknotesException e) {
            System.out.println(e.getMessage() + " while depositing money");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            System.out.println("Deposit finished");
        }
    }

    public void foolWithMoney(int sum) throws Exception {
        int numberOfBanknotes = 0;
        int index = Nominals.nominals.length - 1;

        while (sum > 0 && index >= 0) {
            int nominal = Nominals.nominals[index];
            if (BanknoteCarrier.isBanknote(nominal)) {
                while (sum >= nominal && numberOfBanknotes < this.getMaximumQuantity()) {
                    sum -= nominal;
                    this.setBanknoteCarrier(nominal, this.getBanknoteCarrier(nominal).getQuantity() + 1);
                    numberOfBanknotes++;
                }
            }
            index--;
        }

        // Проверка, осталась ли часть суммы, которую нельзя разложить
        if (sum > 0) {
            throw new InsufficientFundsException("Insufficient funds in the ATM. Remaining sum: " + sum);
        }

        if (numberOfBanknotes > this.getMaximumQuantity()) {
            throw new ExceedsMaximumBanknotesException();
        }
    }

    // метод снятия денег в банкомате
    public void withdrawMoney(Scanner scanner) throws Exception {
        try {
            int withdrawSum;
            do {
                System.out.printf("Enter the amount of money you would like to withdraw (Minimum %d): ", this.getMinimumSum());
                withdrawSum = scanner.nextInt();
            } while (withdrawSum <= 0);

            if (withdrawSum < this.getMinimumSum()) {
                throw new MinimumSumException();
            }

            if (withdrawSum > this.getTotalAmount()) {
                throw new InsufficientFundsException();
            }

            int quantityOfBanknotes = 0;
            for (int i = Nominals.nominals.length - 1; i >= 0; i--) {
                while (withdrawSum >= Nominals.nominals[i] && this.getBanknoteCarrier(Nominals.nominals[i]).getQuantity() > 0) {
                    withdrawSum -= Nominals.nominals[i];
                    BanknoteCarrier carrier = this.getBanknoteCarrier(Nominals.nominals[i]);
                    carrier.setQuantity(carrier.getQuantity() - 1);
                    quantityOfBanknotes++;
                    if (quantityOfBanknotes > this.getMaximumQuantity()) {
                        throw new ExceedsMaximumBanknotesException();
                    }
                }
            }
        } catch (MinimumSumException | InsufficientFundsException | ExceedsMaximumBanknotesException e) {
            System.out.println("Error during withdrawal: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error during withdrawal: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("Withdrawal finished");
        }
    }

    public BanknoteCarrier getBanknoteCarrier(int nominal) {
        for(int i = 0; i < banknoteCarriers.length; i++){
            if(banknoteCarriers[i].getNominal() == nominal){
                return banknoteCarriers[i];
            }
        }
        return null;
    }
    public void setBanknoteCarrier(int nominal, int quantity) {
        this.getBanknoteCarrier(nominal).setQuantity(quantity);
    }

    public BanknoteCarrier[] getBanknoteCarriers() {
        return banknoteCarriers;
    }
    public void setBanknoteCarriers(BanknoteCarrier[] banknoteCarriers) {
        this.banknoteCarriers = banknoteCarriers;
    }
    public int getMinimumSum() {
        return minimumSum;
    }
    public void setMinimumSum(int minimumSum) {
        this.minimumSum = minimumSum;
    }
    public int getMaximumQuantity() {
        return maximumQuantity;
    }
    public void setMaximumQuantity(int maximumQuantity) {
        this.maximumQuantity = maximumQuantity;
    }
}
