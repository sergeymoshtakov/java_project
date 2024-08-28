package com.company.exceptions;

public class NotWalidNominalException extends ATMException{
    public NotWalidNominalException(){
        super("Not a walid nominal of banknote");
    }
}
