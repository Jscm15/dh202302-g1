package com.dh.g1.apicard.exception;

public class CardException extends Exception{
    public CardException(MessageError messageError){super(messageError.message);}
}
