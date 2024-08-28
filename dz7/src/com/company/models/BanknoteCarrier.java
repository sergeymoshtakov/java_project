package com.company.models;

import com.company.factories.BanknoteCarrierFactory;
import com.company.resources.Nominals;

public class BanknoteCarrier {
    private int nominal;
    private int quantity;
    public BanknoteCarrier(int nominal, int quantity) {
        this.setNominal(nominal);
        this.setQuantity(quantity);
    }
    public BanknoteCarrier(BanknoteCarrier banknoteCarrier) {
        this(banknoteCarrier.getNominal(), banknoteCarrier.getQuantity());
    }
    public BanknoteCarrier(int nominal) {
        this(BanknoteCarrierFactory.getBanknoteCarrier(nominal));
    }
    public BanknoteCarrier(){
        this(BanknoteCarrierFactory.getBanknoteCarrier());
    }

    public static boolean isBanknote(int nominal) {
        for(int i = 0; i < Nominals.nominals.length; i++){
            if(Nominals.nominals[i] == nominal){
                return true;
            }
        }
        return false;
    }

    public int getNominal() {
        return nominal;
    }
    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
