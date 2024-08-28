package com.company.factories;

import com.company.models.ATM;
import com.company.models.BanknoteCarrier;
import com.company.resources.Nominals;

import java.util.Random;

public class ATMFactory {
    // метод инициализации банкомата
    public static ATM getATM() {
        Random random = new Random();
        BanknoteCarrier[] banknoteCarriers = new BanknoteCarrier[9];
        for (int i = 0; i < Nominals.nominals.length; i++) {
            banknoteCarriers[i] = new BanknoteCarrier(Nominals.nominals[i]);
        }
        ATM atm = new ATM(random.nextInt(100), random.nextInt(100), banknoteCarriers);
        return atm;
    }
}
